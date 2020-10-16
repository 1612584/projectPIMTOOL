package vn.elca.training.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GroupDto {
    private Long id;
    private Long version;
    private EmployeeDto employeeDto;
}
