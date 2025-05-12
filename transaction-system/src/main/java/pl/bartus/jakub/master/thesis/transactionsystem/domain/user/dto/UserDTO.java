package pl.bartus.jakub.master.thesis.transactionsystem.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.enumerated.UserGroup;

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
