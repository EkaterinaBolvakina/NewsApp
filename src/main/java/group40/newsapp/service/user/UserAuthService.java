package group40.newsapp.service.user;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.user.UserNewDTO;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.user.Role;
import group40.newsapp.models.user.State;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.user.RoleRepository;
import group40.newsapp.repository.user.UserRepository;
import group40.newsapp.security.dto.AuthResponse;
import group40.newsapp.security.service.JwtTokenProvider;
import group40.newsapp.service.mail.MailCreateUtil;
import group40.newsapp.service.mail.UserMailSender;
import group40.newsapp.service.util.UserConvert;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserConvert userConvert;
    private final UserFindService userFindService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final MailCreateUtil mailCreateUtil;
    private final UserMailSender userMailSender;

    @Transactional
    public void addUser(UserNewDTO user) {
        userFindService.findUserForAuth(user.getEmail());
        Role role = roleRepository.findByRole("USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String codeValue = UUID.randomUUID().toString();
        User newUser = userConvert.fromDTOtoUser(user, role, State.NOT_CONFIRMED);
        newUser.setCode(codeValue);
        repository.save(newUser);
        sendEmail(newUser, codeValue);
    }


    public AuthResponse authentication(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication.getName());

        return new AuthResponse(jwt);
    }
    public ResponseEntity<StandardResponseDto> confirm(String id, String code){
        Long userId= decodeUserId(id);
        User user=userFindService.findUserById(userId);
        if(code.equals(user.getCode())){
            user.setCode("");
            user.setState(State.CONFIRMED);
            repository.save(user);
            return ResponseEntity.ok(new StandardResponseDto("User confirmed successfully"));
        }
            return ResponseEntity.badRequest().body(new StandardResponseDto("Invalid confirmation code"));
    }


    private void sendEmail(User user, String code) {
        String link = "?data=" + (codeUserId(Long.toString(user.getId()))) + "&code=" + code;
        String html = mailCreateUtil.createConfirmationMail(user.getName(), link);
        userMailSender.send(user.getEmail(), "Registration", html);
    }


    private Long decodeUserId(String code){
        byte[] byteArray=code.getBytes();
        StringBuilder buffer =new StringBuilder();
        for(int i=0;i<byteArray.length;i++){
            if (byteArray[i] < 97 || byteArray[i] > 106){
                throw new RestException(HttpStatus.NOT_FOUND, "User not found");
            }
            if (i > 1 && i < byteArray.length - 3) {
                buffer.append((char) (byteArray[i] - 49));
            }
        }
        return Long.parseLong(buffer.toString());
    }
    private String codeUserId(String id){
        String code=randomString(5);
        StringBuilder result= new StringBuilder();
        for (int i=0;i<code.length();i++){
            result.append(code.charAt(i));
            if(i==1) {
                result.append(numberToCodeString(id));
            }
        }
        return result.toString();
    }
    private String numberToCodeString(String number){
        byte[] byteArray=number.getBytes();
        StringBuilder buffer =new StringBuilder();;
        for(int i=0;i<byteArray.length;i++){
           buffer.append((char)(byteArray[i]+49));
        }
        return buffer.toString();
    }

    private String randomString(int length){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 106; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
