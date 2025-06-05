package ibgdev.tunijobs.dto;

import lombok.Data;

@Data
public class RegistrationDto {
    private String nom;
    private String email;
    private String password;
    private String role = "CANDIDATE";
}






