package pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.dto;

import lombok.Builder;
import lombok.Data;
import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.enumerated.UserGroup;

import java.util.UUID;

@Data
@Builder
public class UserDTO {
    private UUID id;
    private UserGroup group;
    private String firstName;
    private String lastName;
    private String email;
    private boolean emailVerified;
}
