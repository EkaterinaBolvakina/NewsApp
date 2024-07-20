package group40.newsapp.controller.newsController.newsCommentController;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.service.newsCommentService.AddNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class AddNewsCommentController {
    private final AddNewsCommentService addNewsCommentService;

    @PostMapping("/{newsId}/addComment")
    public ResponseEntity<NewsCommentResponseDTO> addNewsComment(@Valid @RequestBody NewsCommentRequestDTO newsCommentRequestDTO, @PathVariable Long newsId) {
        return addNewsCommentService.addNewsComment(newsCommentRequestDTO);
    }
}
