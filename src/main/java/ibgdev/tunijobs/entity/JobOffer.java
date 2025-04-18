package ibgdev.tunijobs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobOffer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private Double salaire;
    @Temporal(TemporalType.DATE)
    private Date datePublication;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Company entreprise;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobOffer")
    private Set<Application> applications;
}
