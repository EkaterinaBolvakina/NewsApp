package group40.newsapp.controller.newsController.newsCommentController;

import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.DTO.newsComment.UpdateCommentRequestDTO;
import group40.newsapp.service.newsCommentService.UpdateNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class UpdateNewsCommentController {
    private final UpdateNewsCommentService updateNewsCommentService;

    @PutMapping("/{newsId}/comments/{commentId}")
    public ResponseEntity<NewsCommentResponseDTO> updateNewsCommentById(
            @PathVariable Long newsId,
            @PathVariable Long commentId,
            @Valid @RequestBody UpdateCommentRequestDTO updateCommentRequestDTO
    ) {
        return updateNewsCommentService.updateNewsComment(commentId, updateCommentRequestDTO);
    }
}
