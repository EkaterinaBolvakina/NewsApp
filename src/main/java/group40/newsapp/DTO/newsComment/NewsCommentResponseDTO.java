package group40.newsapp.DTO.newsComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsCommentResponseDTO {
    private Long id;
    private String comment;
    private LocalDateTime commentDate;
    private Long newsId;
    private Long userId;
    private String authorName;
}
