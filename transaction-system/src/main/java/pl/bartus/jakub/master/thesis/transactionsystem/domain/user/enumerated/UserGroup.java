package pl.bartus.jakub.master.thesis.transactionsystem.domain.user.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGroup {
    USER("DEFAULT",0),
    ADMIN("ADMIN", 1),
    SYSTEM("SYSTEM", 2);
    private final String role;
    private final Integer accessLevel;
}
