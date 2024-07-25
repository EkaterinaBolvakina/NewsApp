package group40.newsapp.controller.start;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.user.UserNewDTO;
import group40.newsapp.controller.api.AuthApi;
import group40.newsapp.security.dto.AuthRequest;
import group40.newsapp.security.dto.AuthResponse;
import group40.newsapp.service.user.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthorizationController implements AuthApi {
    private final UserAuthService userAuthService;

    @Override
    public ResponseEntity<AuthResponse> auth(AuthRequest request) {
        return new ResponseEntity<>(userAuthService.authentication(request.getEmail(), request.getPassword())
                , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<StandardResponseDto> registration(UserNewDTO user) {
        userAuthService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new StandardResponseDto("Confirmation sanded to your email"));
    }

    @Override
    public ResponseEntity<StandardResponseDto> confirmation(String data, String code) {
       return userAuthService.confirm(data, code);
    }


}
