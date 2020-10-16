package vn.elca.training.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import vn.elca.training.model.entity.Group;
import vn.elca.training.repository.custom.GroupRepositoryCustom;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long>, QuerydslPredicateExecutor<Group>,
        GroupRepositoryCustom {
    Optional<Group> findById(Long id);
}
