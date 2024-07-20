package group40.newsapp.DTO.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserNewDTO {
    @NotBlank
    @NotNull
    @Size(min = 3, max = 15)
    private String name;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!,.|?><~/])(?=\\S+$).{8,}$")
    private String password;
    @Email
    @NotNull
    private String email;
}