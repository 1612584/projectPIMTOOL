package vn.elca.training.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import org.hibernate.validator.constraints.NotBlank;
import vn.elca.training.util.LocalDateDeserializer;
import vn.elca.training.util.LocalDateSerializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author gtn
 *
 */
@Setter @Getter @NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank(message = "this field is mandatory")
    private String name;
    @NotNull(message = "this field is mandatory")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;
    @NotBlank
    private String customer;
    @NotNull(message = "this field is mandatory")
    private Long groupId;
    private Long version;
    @NotNull(message = "this field is mandatory")
    private Integer projectNumber;
    @NotBlank
    private String status;
    // array of visa member
    private String visaString;
}
