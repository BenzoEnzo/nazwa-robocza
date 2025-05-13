package pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.security;

import pl.bartus.jakub.master.thesis.transactionsystem.external.keycloak.user.enumerated.UserGroup;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecuredByGroup {
    UserGroup value();
}
