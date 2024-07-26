package group40.newsapp.controller.newsCommentController;

import group40.newsapp.DTO.appDTO.StandardDelRequest;
import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.service.newsCommentService.DeleteNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-comment")
@RequiredArgsConstructor
public class DeleteNewsCommentController {
    private final DeleteNewsCommentService deleteNewsCommentService;

    @DeleteMapping
    public ResponseEntity<StandardResponseDto> deleteNewsCommentById(@Valid @RequestBody StandardDelRequest dto) {
        deleteNewsCommentService.deleteNewsCommentById(dto);
        return ResponseEntity.ok(new StandardResponseDto("News Comment with ID = "+ dto.getId() +" deleted successfully"));
    }
}
