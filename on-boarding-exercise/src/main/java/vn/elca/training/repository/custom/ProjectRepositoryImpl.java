package vn.elca.training.repository.custom;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.QProject;
import vn.elca.training.model.enums.ProjectStatus;
import vn.elca.training.repository.ProjectRepository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Component
public class ProjectRepositoryImpl implements ProjectRepositoryCustom {
    @Autowired
    private EntityManager em;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project findFistProjectByNameAndFinishingDate(String name, LocalDate finishingDate) {
        return new JPAQuery<Project>(em)
                            .from(QProject.project)
                            .where(QProject.project.name.eq(name).and(QProject.project.endDate.eq(finishingDate)))
                            .fetchFirst();

    }

    @Override
    public List<Project> findAllByCriteriaNameAndStatus(String name, String status) {
        BooleanExpression expression = getCriteriaQuery(name, status);
//        return (List<Project>) projectRepository.findAll(expression);
        return new JPAQuery<Project> (em)
                .from(QProject.project)
                .where(expression)
                .orderBy(QProject.project.projectNumber.asc())
                .fetch();
    }

    @Override
    public Page<Project> findProjectPageByCriteriaNameAndStatus(String name, String status, Pageable pageable) {
       BooleanExpression expression = getCriteriaQuery(name, status);
       return projectRepository.findAll(expression, pageable);
    }

    @Override
    public long deleteByListId(List<Long> listId) {
         JPADeleteClause deleteClause =
                 new JPADeleteClause(em, QProject.project)
                    .where(QProject.project.id.in(listId));
         return deleteClause.execute();
    }

    public long checkProjectsAreAbleToBeDeleted(List<Long> listId) {
        return new JPAQuery<Integer>(em)
                    .from(QProject.project)
                    .where(QProject.project.id.in(listId).and(QProject.project.status.eq(ProjectStatus.NEW)))
                    .fetchCount();
    }

    private BooleanExpression getCriteriaQuery(String name, String status) {
        BooleanExpression expression = QProject.project.id.isNotNull();
        if (StringUtils.isNotEmpty(name)) {
            expression = QProject.project.name.containsIgnoreCase(name)
                    .or(QProject.project.customer.containsIgnoreCase(name))
                    .or(QProject.project.projectNumber.stringValue().contains(name));
        }
        if (StringUtils.isNotEmpty(status)) {
            ProjectStatus projectStatus = ProjectStatus.valueOf(status);
            expression = expression.and(QProject.project.status.eq(projectStatus));
        }
        return expression;
    }
}
