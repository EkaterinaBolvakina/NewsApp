package group40.newsapp.controller.newsController.newsCommentController;

import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.service.newsCommentService.FindNewsCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class FindNewsCommentController {
    private final FindNewsCommentService findNewsCommentService;

    @GetMapping("/comments")
    public ResponseEntity<List<NewsCommentResponseDTO>> findAllNewsComments() {
        return findNewsCommentService.findAll();
    }
}
