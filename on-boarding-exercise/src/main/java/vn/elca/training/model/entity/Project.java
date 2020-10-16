package vn.elca.training.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.elca.training.model.enums.ProjectStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.time.LocalDate;
import java.util.List;


/**
 * @author vlp
 *
 */
@Entity
@Getter @Setter @NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Integer projectNumber;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String customer;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column(nullable = false, columnDefinition = "bigint DEFAULT 0")
    @Version
    private Long version;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_ID", nullable = false)
	private Group group;

	@ManyToMany
    @JoinTable(
            name = "PROJECT_EMPLOYEE",
            joinColumns = @JoinColumn(name = "PROJECT_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID")
    )
    private List<Employee> employees;

    public Project(String name, LocalDate endDate) {
        this.name = name;
        this.endDate = endDate;
    }

    public Project(Long id, String name, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.endDate = endDate;
    }
}