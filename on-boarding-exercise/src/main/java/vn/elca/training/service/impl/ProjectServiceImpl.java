package vn.elca.training.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.ResponseDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ConcurrentUpdateException;
import vn.elca.training.model.exception.DeletedProjectStatusCanNotBeNew;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.GroupService;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.Mapper;
import org.apache.commons.collections4.CollectionUtils;
import javax.transaction.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author vlp
 *
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    private final EmployeeRepository employeeRepository;

    private final GroupService groupService;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository, GroupService groupService) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.groupService = groupService;
    }


    @Override
    public List<Project> findByNameIgnoreCaseContaining(String name) {
        return projectRepository.findByNameIgnoreCaseContaining(name);
    }

    @Override
    public ProjectDto findById(Long id) {
        return Mapper.projectToProjectDto(projectRepository.findById(id).get());
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto createProject(ProjectDto projectDto) throws ConcurrentUpdateException {
        ResponseDto response = new ResponseDto();

        if (projectRepository.existsByProjectNumberEquals(projectDto.getProjectNumber())) {
            response.setConflictProjectNumber(true);
            return response;
        }
        final Group group = groupService.getGroupById(projectDto.getGroupId());
        String visas = projectDto.getVisaString();
        // employee list existed
        List<Employee> employees = new ArrayList<>();
        if (StringUtils.isNotBlank(visas)){
            Set<String> visaSet = new HashSet<>();
            visaSet = Arrays.stream(visas.split(","))
                                    .filter(StringUtils::isNotBlank)
                                    .map(String::trim)
                                    .collect(Collectors.toSet());
            employees = employeeRepository.existsByVisaSet(visaSet);
            List<String> notExistedEmployees =
                    (List<String>) CollectionUtils.subtract(visaSet,
                        employees
                                .stream()
                                .map(Employee::getVisa)
                                .collect(Collectors.toList()));
            if (!notExistedEmployees.isEmpty()) {
                response.setVisaList(notExistedEmployees);
                return response;
            }
        }
        if (projectDto.getEndDate() != null &&
                projectDto.getStartDate().compareTo(projectDto.getEndDate()) > 0) {
            response.setInvalidDates(true);
            return response;
        }
        // save project
        saveProject(group, employees, projectDto);
        // update status
        response.setSuccess(true);
        return response;
    }

    @Override
    public boolean checkProjectNumberExist(Integer number) {
        return projectRepository.existsByProjectNumberEquals(number);
    }

    @Override
    public Page<ProjectDto> findAllByNameAndStatus(String name, String status, Pageable pageable) {
        Page<Project> projectPage = projectRepository.findProjectPageByCriteriaNameAndStatus(name, status, pageable);
        return Mapper.pageProjectToPageProjectDto(projectPage);
    }

    @Override
    public List<ProjectDto> findAllByNameAndStatus(String name, String status) {
        return projectRepository
                .findAllByCriteriaNameAndStatus(name, status)
                .stream()
                .map(Mapper::projectToProjectDtoNotIncludeGroupAndVisaString)
                .collect(Collectors.toList());
    }

    @Override
    public void removeProjectById(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public long deleteByListId(List<Long> listId) throws DeletedProjectStatusCanNotBeNew {
        if (projectRepository.checkProjectsAreAbleToBeDeleted(listId) < listId.size()) {
            throw new DeletedProjectStatusCanNotBeNew("Project could not be removed because its status is new");
        }
        return projectRepository.deleteAllByIdIn(listId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ResponseDto updateProject(Long id, ProjectDto projectDto) throws ConcurrentUpdateException {
        final Group group = groupService.getGroupById(projectDto.getGroupId());
        String visas = projectDto.getVisaString();
        Set<String> visaSet = new HashSet<>();
        if (StringUtils.isNotBlank(visas)){
            visaSet = Arrays.stream(visas.split(","))
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .collect(Collectors.toSet());
        }

        List<Employee> employees = employeeRepository.existsByVisaSet(visaSet);
        List<String> notExistedEmployees = (List<String>) CollectionUtils.subtract(visaSet,
                employees.stream().map(Employee::getVisa).collect(Collectors.toList()));

        ResponseDto response = new ResponseDto();
        response.setConflictProjectNumber(false);
        if (!notExistedEmployees.isEmpty()) {
            response.setVisaList(notExistedEmployees);
            return response;
        }
        if (projectDto.getEndDate() != null &&
                projectDto.getStartDate().compareTo(projectDto.getEndDate()) > 0) {
            response.setInvalidDates(true);
        }
        // save project
        saveProject(group, employees, projectDto);
        // update status
        response.setSuccess(true);
        return response;
    }

    private void saveProject(Group group, List<Employee> employees, ProjectDto projectDto) throws ConcurrentUpdateException {
        Project temp = Mapper.projectDtoToProject(projectDto);
        temp.setGroup(group);
        // save employee list with project
        temp.setEmployees(employees);
        try {
            projectRepository.save(temp);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new ConcurrentUpdateException("Project has changed, refresh to get the latest version to update", e);
        }
    }
}
