package pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.enumerated;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserGroup {
    DEFAULT("DEFAULT",0),
    USER("USER",1),
    SYSTEM("SYSTEM", 2);
    private final String name;
    private final Integer accessLevel;
}
