package vn.elca.training.util;

import org.springframework.data.domain.Page;
import vn.elca.protobuf.Date;
import vn.elca.protobuf.GrpcGroup;
import vn.elca.protobuf.GrpcGroupList;
import vn.elca.protobuf.GrpcProjectDto;
import vn.elca.protobuf.ResponseUpdate;
import vn.elca.protobuf.SearchResponse;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.ResponseDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.enums.ProjectStatus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author gtn
 *
 */

@Transactional(Transactional.TxType.NOT_SUPPORTED)
public class Mapper {
    public Mapper() {
        // Mapper utility class
    }

    public static ProjectDto projectToProjectDto(Project entity) {
        ProjectDto projectDto = projectToProjectDtoNotIncludeGroupAndVisaString(entity);
        projectDto.setGroupId(entity.getGroup().getId());
        projectDto.setVisaString(entity
                                    .getEmployees()
                                    .stream()
                                    .map(employee -> employee.getVisa())
                                    .collect(Collectors.joining(",")));
        return projectDto;
    }

    public static Project projectDtoToProject(ProjectDto dto) {
        Project entity = new Project();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        entity.setCustomer(dto.getCustomer());
        entity.setProjectNumber(dto.getProjectNumber());
        entity.setVersion(dto.getVersion());
        entity.setStatus(ProjectStatus.valueOf(dto.getStatus()));
        return entity;
    }

    /**
     * receive Employee as entity, return EmployeeDto
     *
     * @param employee
     * @return EmployeeDto
     */
    public static EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setBirthDate(employee.getBirthDate());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setVisa(employee.getVisa());
        employeeDto.setVersion(employee.getVersion());
        return employeeDto;
    }

    /**
     * receive Group as entity, return GroupDto
     *
     * @param group
     * @return GroupDto
     */
    public static GroupDto groupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setVersion(group.getVersion());
        groupDto.setEmployeeDto(Mapper.employeeToEmployeeDto(group.getLeader()));
        return groupDto;
    }

    /**
     * receive List<Group>, return List<GroupDto>
     *
     * @param groups as List<Group>
     * @return
     */
    public static List<GroupDto> groupListToGroupDtoList(List<Group> groups) {
        return groups
                .stream()
                .map(Mapper::groupToGroupDto)
                .collect(Collectors.toList());
    }

    public static Page<ProjectDto> pageProjectToPageProjectDto(Page<Project> projectPage) {
        Page<ProjectDto> projectDtoPage = projectPage.map(Mapper::projectToProjectDto);
        return projectDtoPage;
    }

    public static List<ProjectDto> projectListToProjectDtoList(List<Project> projects) {
        return projects.stream()
                        .map(Mapper::projectToProjectDto)
                        .collect(Collectors.toList());
    }

    public static SearchResponse toGrpcSearchResponse(List<ProjectDto> projectDtoList) {

        SearchResponse searchResponse = SearchResponse
                                        .newBuilder()
                                        .addAllProjects(Mapper.toIterableGrpcProjectDto(projectDtoList))
                                        .build();
        return searchResponse;
    }
    public static Iterable<GrpcProjectDto> toIterableGrpcProjectDto(List<ProjectDto> projectDtoList) {
        return projectDtoList.stream()
                        .map(Mapper::toGrpcProjectDtoNotIncludeGroupAndVisaString)
                        .collect(Collectors.toList());
    }

    public static GrpcProjectDto toGrpcProjectDto(ProjectDto dto) {
        return toGrpcProjectDtoNotIncludeGroupAndVisaString(dto)
                .toBuilder()
                .setGroupId(dto.getGroupId())
                .setVisaString(dto.getVisaString())
                .build();
//       return GrpcProjectDto.newBuilder()
//                .setId(dto.getId())
//                .setCustomer(dto.getCustomer())
//                .setProjectNumber(dto.getProjectNumber())
//                .setName(dto.getName())
//                .setGroupId(dto.getGroupId())
//                .setStatus(dto.getStatus())
//                .setVersion(dto.getVersion())
//                .setStartDate(toGrpcDate(dto.getStartDate()))
//                .setEndDate(toGrpcDate(dto.getEndDate()))
//                .setVisaString(dto.getVisaString())
//                .build();
    }

    public static GrpcProjectDto toGrpcProjectDtoNotIncludeGroupAndVisaString(ProjectDto dto) {
        GrpcProjectDto projectDto =
                GrpcProjectDto.newBuilder().setId(dto.getId())
                .setCustomer(dto.getCustomer())
                .setProjectNumber(dto.getProjectNumber())
                .setName(dto.getName())
                .setStatus(dto.getStatus())
                .setVersion(dto.getVersion())
                .setStartDate(toGrpcDate(dto.getStartDate()))
                        .buildPartial();
         if (dto.getEndDate() != null) {
             projectDto = projectDto.toBuilder()
                        .setEndDate(toGrpcDate(dto.getEndDate()))
                        .build();
         }
         return projectDto;
    }

    public static Date toGrpcDate(LocalDate date) {
        return Date.newBuilder()
                .setDay(date.getDayOfMonth())
                .setMonth(date.getMonthValue())
                .setYear(date.getYear())
                .build();
    }

    public static LocalDate toLocalDate(Date date) {
        return LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
    }

    public static ProjectDto projectToProjectDtoNotIncludeGroupAndVisaString(Project entity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(entity.getId());
        projectDto.setName(entity.getName());
        projectDto.setCustomer(entity.getCustomer());
        projectDto.setEndDate(entity.getEndDate());
        projectDto.setStartDate(entity.getStartDate());
        projectDto.setProjectNumber(entity.getProjectNumber());
        projectDto.setVersion(entity.getVersion());
        projectDto.setStatus(entity.getStatus().name());
        return projectDto;
    }

    public static GrpcGroupList toGrpcGroupList(List<GroupDto> groups) {
       return GrpcGroupList.newBuilder().addAllGroups(Mapper.toListGrpcGroup(groups)).build();
    }

    private static List<GrpcGroup> toListGrpcGroup(List<GroupDto> groups) {
        return groups
                .stream()
                .map(Mapper::toGrpcGroup)
                .collect(Collectors.toList());
    }

    public static GrpcGroup toGrpcGroup(GroupDto dto) {
        return GrpcGroup
                .newBuilder()
                .setId(dto.getId())
                .setVersion(dto.getVersion())
                .build();
    }

    public static ProjectDto toProjectDto(GrpcProjectDto request) {
        ProjectDto dto = new ProjectDto();
        dto.setProjectNumber(request.getProjectNumber());
        dto.setId(request.getId());
        dto.setName(request.getName());
        dto.setCustomer(request.getCustomer());
        dto.setStartDate(Mapper.toLocalDate(request.getStartDate()));
        if (request.hasEndDate()) {
            dto.setEndDate(Mapper.toLocalDate(request.getEndDate()));
        }
        dto.setStatus(request.getStatus());
        dto.setVisaString(request.getVisaString());
        dto.setGroupId(request.getGroupId());
        dto.setVersion(request.getVersion());
        return dto;
    }

    public static ResponseUpdate toResponseUpdate(ResponseDto project) {
        return ResponseUpdate
                .newBuilder()
                .setMessage(project.getMessage())
                .setSuccess(project.isSuccess())
                .setConflictProjectNumber(project.isConflictProjectNumber())
                .setIsConcurrentUpdate(project.isConcurrentUpdate())
                .setInvalidDates(project.isInvalidDates())
                .addAllVisaList(project.getVisaList())
                .build();
    }
}
