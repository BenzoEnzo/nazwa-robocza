package pl.bartus.jakub.master.thesis.transactionsystem.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.dto.UserDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.enumerated.UserGroup;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    public UUID getUserId(){
        return UUID.fromString(getClaim(getCurrentAuthentication(),"sub"));
    }

    public UserDTO getUserInfo(){
        JwtAuthenticationToken token = getCurrentAuthentication();

        return UserDTO.builder()
                .id(UUID.fromString(getClaim(token, "sub")))
                .group(getUserGroup(token))
                .firstName(getClaim(token,"given_name"))
                .lastName(getClaim(token, "family_name"))
                .email(getClaim(token,"email"))
                .emailVerified(getClaim(token, "email_verified"))
                .build();
    }

    private JwtAuthenticationToken getCurrentAuthentication(){
        return (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    private UserGroup getUserGroup(JwtAuthenticationToken token){
        GrantedAuthority authority = token.getAuthorities().stream()
                .findFirst()
                .orElseThrow();

        return UserGroup.valueOf(authority.getAuthority());
    }

    private <T> T getClaim(JwtAuthenticationToken auth, String claim){
        return auth.getToken().getClaim(claim);
    }
}
