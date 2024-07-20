package group40.newsapp.DTO.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRoleDTO {
    @NotNull
    private String role;
    @NotNull
    @Min(1)
    private Integer userId;
}