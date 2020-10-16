package vn.elca.training.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.repository.custom.EmployeeRepositoryCustom;



public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee>, EmployeeRepositoryCustom {
    boolean existsByVisa(String visa);

}
