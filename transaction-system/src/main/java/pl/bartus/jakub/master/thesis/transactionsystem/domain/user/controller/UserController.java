package pl.bartus.jakub.master.thesis.transactionsystem.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.dto.UserDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.enumerated.UserGroup;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.service.UserAuthService;
import pl.bartus.jakub.master.thesis.transactionsystem.security.SecuredByGroup;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserAuthService userAuthService;

    @GetMapping
    @SecuredByGroup(UserGroup.DEFAULT)
    public ResponseEntity<UserDTO> whoAmI(){
        return ResponseEntity.status(HttpStatus.OK).body(userAuthService.getUserInfo());
    }
}
