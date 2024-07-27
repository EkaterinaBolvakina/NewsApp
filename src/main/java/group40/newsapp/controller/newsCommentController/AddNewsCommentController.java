package group40.newsapp.controller.newsCommentController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.controller.api.newsComment.AddNewsCommentApi;
import group40.newsapp.service.newsCommentService.AddNewsCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddNewsCommentController implements AddNewsCommentApi {
    private final AddNewsCommentService addNewsCommentService;

    @Override
    public ResponseEntity<StandardResponseDto> addNewsComment(NewsCommentRequestDTO DTO) {
        addNewsCommentService.addNewsComment(DTO);
        return ResponseEntity.ok(new StandardResponseDto("Comment to news with ID = "+ DTO.getNewsId() +" added successfully"));
    }
}
