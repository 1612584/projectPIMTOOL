package vn.elca.training.repository.custom;

import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.QEmployee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

public class EmployeeRepositoryImpl implements EmployeeRepositoryCustom {
    private final EntityManager em;

    public EmployeeRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> existsByVisaSet(Set<String> visaSet) {
        return new JPAQuery<Employee>(em)
                    .from(QEmployee.employee)
                    .where(QEmployee.employee.visa.in(visaSet))
                    .fetch();
    }
}
