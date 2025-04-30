package ibgdev.tunijobs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Application> applications;
}
