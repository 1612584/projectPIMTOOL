package vn.elca.training.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter @NoArgsConstructor
public class EmployeeDto {
    private Long id;

    private String visa;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Long version;
}
