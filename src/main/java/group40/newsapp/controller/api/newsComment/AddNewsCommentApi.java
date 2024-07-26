package group40.newsapp.controller.api.newsComment;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/news-comment")
@CrossOrigin(origins = "http://localhost:5173")
public interface AddNewsCommentApi {
    @Operation(summary = "Adding new news comment", description = "The operation is available to registered user, add new news comment")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description ="Information about news comment adding",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{ \"message\": \"Comment added successfully\"}"))),
            @ApiResponse(responseCode = "404", description = "News with ID = .. not found",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"message\": \"News with ID = 25 not found\"}"))),
            @ApiResponse(responseCode = "403", description = "User must be registered to add a new news comment",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"message\": \"Forbidden\"}")))

    })
    @PostMapping
    public ResponseEntity<StandardResponseDto> addNewsComment(@Valid @RequestBody NewsCommentRequestDTO DTO);
}
