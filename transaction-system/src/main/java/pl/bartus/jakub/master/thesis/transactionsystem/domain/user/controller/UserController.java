package pl.bartus.jakub.master.thesis.transactionsystem.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.dto.UserDTO;
import pl.bartus.jakub.master.thesis.transactionsystem.domain.user.service.UserAuthService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserAuthService userAuthService;

    @GetMapping
    public ResponseEntity<UserDTO> whoAmI(){
        return ResponseEntity.status(HttpStatus.OK).body(userAuthService.getUserInfo());
    }
}
