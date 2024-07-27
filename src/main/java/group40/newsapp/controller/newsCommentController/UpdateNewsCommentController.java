package group40.newsapp.controller.newsCommentController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.newsComment.UpdateCommentRequestDTO;
import group40.newsapp.controller.api.newsComment.UpdateNewsCommentApi;
import group40.newsapp.service.newsCommentService.UpdateNewsCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class UpdateNewsCommentController implements UpdateNewsCommentApi {
    private final UpdateNewsCommentService updateNewsCommentService;

    @Override
    @PutMapping("comment")
    public ResponseEntity<StandardResponseDto> updateNewsCommentById(@Valid @RequestBody UpdateCommentRequestDTO DTO) {
        updateNewsCommentService.updateNewsComment(DTO);
        return ResponseEntity.ok(new StandardResponseDto("Comment with ID = "+ DTO.getId() +" updated successfully"));
    }
}
