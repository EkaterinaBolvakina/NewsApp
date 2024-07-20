package group40.newsapp.controller.newsController.newsCommentController;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.service.newsCommentService.AddNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class AddNewsCommentController {
    private final AddNewsCommentService addNewsCommentService;

    @PostMapping("/addComment")
    public ResponseEntity<NewsCommentResponseDTO> addNewsComment(@Valid @RequestBody NewsCommentRequestDTO newsCommentRequestDTO) {
        return addNewsCommentService.addNewsComment(newsCommentRequestDTO);
    }
}
