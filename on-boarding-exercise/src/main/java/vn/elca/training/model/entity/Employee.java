package vn.elca.training.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.List;

@Entity
@NoArgsConstructor @Setter @Getter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 3)
    private String visa;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, columnDefinition = "bigint DEFAULT 0")
    @Version
    private Long version;

    @OneToMany(mappedBy = "leader", fetch = FetchType.LAZY)
    private Set<Group> leaderGroup;

    @ManyToMany(mappedBy = "employees")
    private List<Project> projects;
}