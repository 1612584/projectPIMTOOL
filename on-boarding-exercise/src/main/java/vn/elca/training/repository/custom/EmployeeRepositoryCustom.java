package vn.elca.training.repository.custom;

import vn.elca.training.model.entity.Employee;

import java.util.List;
import java.util.Set;

public interface EmployeeRepositoryCustom {
    List<Employee> existsByVisaSet(Set<String> visaSet);
}
