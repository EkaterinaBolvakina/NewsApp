package group40.newsapp.controller.newsCommentController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.newsComment.UpdateCommentRequestDTO;
import group40.newsapp.service.newsCommentService.UpdateNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-comment")
@RequiredArgsConstructor
public class UpdateNewsCommentController {
    private final UpdateNewsCommentService updateNewsCommentService;

    @PutMapping
    public ResponseEntity<StandardResponseDto> updateNewsCommentById(@Valid @RequestBody UpdateCommentRequestDTO DTO) {
        updateNewsCommentService.updateNewsComment(DTO);
        return ResponseEntity.ok(new StandardResponseDto("Comment with id = "+ DTO.getId() +" updated successfully"));
    }
}
