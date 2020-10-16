package vn.elca.training.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor @Getter @Setter
@Table(name = "\"group\"")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, columnDefinition = "bigint DEFAULT 0")
    @Version
    private Long version;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "GROUP_LEADER_ID")
    private Employee leader;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private Set<Project> projects;
}
