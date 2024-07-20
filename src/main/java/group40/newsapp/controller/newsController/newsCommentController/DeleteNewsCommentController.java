package group40.newsapp.controller.newsController.newsCommentController;

import group40.newsapp.service.newsCommentService.DeleteNewsCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class DeleteNewsCommentController {
    private final DeleteNewsCommentService deleteNewsCommentService;

    @DeleteMapping("/{newsId}/comments/{commentId}")
    public ResponseEntity<Void> deleteNewsCommentById(@PathVariable Long newsId, @PathVariable Long commentId) {
        return deleteNewsCommentService.deleteNewsCommentById(commentId);
    }
}
