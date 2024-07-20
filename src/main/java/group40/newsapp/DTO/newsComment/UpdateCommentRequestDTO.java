package group40.newsapp.DTO.newsComment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommentRequestDTO {
    @NotBlank
    @Size(min = 2)
    private String comment;
}
