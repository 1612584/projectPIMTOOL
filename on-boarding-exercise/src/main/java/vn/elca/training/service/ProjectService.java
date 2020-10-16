package vn.elca.training.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.ResponseDto;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.exception.ConcurrentUpdateException;
import vn.elca.training.model.exception.DeletedProjectStatusCanNotBeNew;

import java.util.List;


/**
 * @author vlp
 *
 */
public interface ProjectService {
    List<Project> findByNameIgnoreCaseContaining(String name);

    ProjectDto findById(Long id);

    void save(Project project);

    ResponseDto createProject(ProjectDto project) throws ConcurrentUpdateException;

    boolean checkProjectNumberExist(Integer number);

    List<ProjectDto> findAllByNameAndStatus(String name, String status);
    Page<ProjectDto> findAllByNameAndStatus(String name, String status, Pageable pageable);

    void removeProjectById(Long id);

    long deleteByListId(List<Long> listId) throws DeletedProjectStatusCanNotBeNew;

    ResponseDto updateProject(Long id, ProjectDto projectDto) throws ConcurrentUpdateException;
}
