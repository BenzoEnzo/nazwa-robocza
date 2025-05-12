package pl.bartus.jakub.master.thesis.transactionsystem.domain.user.enumerated;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGroup {
    DEFAULT("DEFAULT",0),
    USER("USER",1),
    SYSTEM("SYSTEM", 2);
    private final String name;
    private final Integer accessLevel;
}
