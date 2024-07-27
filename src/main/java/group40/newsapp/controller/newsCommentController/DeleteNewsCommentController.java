package group40.newsapp.controller.newsCommentController;

import group40.newsapp.DTO.appDTO.StandardDelRequest;
import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.controller.api.newsComment.DeleteNewsCommentApi;
import group40.newsapp.service.newsCommentService.DeleteNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeleteNewsCommentController implements DeleteNewsCommentApi {
    private final DeleteNewsCommentService deleteNewsCommentService;

    @Override
    public ResponseEntity<StandardResponseDto> deleteNewsCommentById(@Valid @RequestBody StandardDelRequest dto) {
        deleteNewsCommentService.deleteNewsCommentById(dto);
        return ResponseEntity.ok(new StandardResponseDto("News Comment with ID = "+ dto.getId() +" deleted successfully"));
    }
}
