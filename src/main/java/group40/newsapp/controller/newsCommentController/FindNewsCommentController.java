package group40.newsapp.controller.newsCommentController;

import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.controller.api.newsComment.FindNewsCommentApi;
import group40.newsapp.service.newsCommentService.FindNewsCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class FindNewsCommentController implements FindNewsCommentApi {
    private final FindNewsCommentService findNewsCommentService;

    @Override
    @GetMapping("/comments")
    public ResponseEntity<List<NewsCommentResponseDTO>> findAllNewsComments() {
        return findNewsCommentService.findAll();
    }

    @Override
    @GetMapping("/{newsId}/comments")
    public ResponseEntity<List<NewsCommentResponseDTO>> findAllNewsCommentsByNewsId(@PathVariable Long newsId){
        return findNewsCommentService.findAllCommentsByNewsId(newsId);
    }

    @Override
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<NewsCommentResponseDTO> findNewsCommentById(@PathVariable Long commentId){
        return findNewsCommentService.findById(commentId);
    }
}
