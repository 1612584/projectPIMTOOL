package vn.elca.training.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.elca.training.model.entity.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectRepositoryCustom {
    Project findFistProjectByNameAndFinishingDate(String name, LocalDate finishingDate);
    List<Project> findAllByCriteriaNameAndStatus(String name, String status);
    Page<Project> findProjectPageByCriteriaNameAndStatus(String name, String status, Pageable pageable);
    long deleteByListId(List<Long> listId);
    long checkProjectsAreAbleToBeDeleted(List<Long> listId);
}
