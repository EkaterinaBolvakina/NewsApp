package group40.newsapp.DTO.newsComment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Schema(name = "Response: Comment on news")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCommentResponseDTO {
    @Schema(description = "id of the comment", example = "5")
    private Long id;

    @Schema(description = "text of the comment", example = "That's great news!")
    private String comment;

    @Schema(description = "id of the news", example = "2")
    private Long newsId;

    @Schema(description = "date of comment publishing", example = "2024-07-26T13:30:00")
    private LocalDateTime commentDate;

    @Schema(description = "Author`s name", example = "Tom")
    private String authorName;

    @Schema(description = "Is comment published by current user", example = "true")
    Boolean isPublishedByCurrentUser;
}
