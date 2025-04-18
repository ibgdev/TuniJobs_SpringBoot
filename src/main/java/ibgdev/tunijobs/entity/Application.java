package ibgdev.tunijobs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String cv;
    private String lettreMotivation;
    @Enumerated(EnumType.STRING)
    private Statut statut;
    @Temporal(TemporalType.DATE)
    @Column(name = "dateApplication")
    private Date createdAt;
    
    @ManyToOne
    @JoinColumn(name = "job_offer_id")
    private JobOffer jobOffer;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
