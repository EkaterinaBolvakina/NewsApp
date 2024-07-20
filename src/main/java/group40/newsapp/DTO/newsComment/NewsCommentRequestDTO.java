package group40.newsapp.DTO.newsComment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewsCommentRequestDTO {
    @NotNull
    @NotBlank
    @Size(min=2)
    private String comment;

    @NotNull
    @Min(1)
    private Long authorId;

    @NotNull
    @Min(1)
    private Long newsId;
}
