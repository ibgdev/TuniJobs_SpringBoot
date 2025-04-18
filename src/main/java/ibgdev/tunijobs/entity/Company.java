package ibgdev.tunijobs.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String matriquleFiscale;
    private String secteur;
    private String adresse;
    private String telephone;
    private String siteWeb;
    @Column(name = "StatutValidation")
    private Boolean validation;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User responsable;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private Set<JobOffer> Joboffers;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "entreprise")
    private Set<Review> reviews;
}
