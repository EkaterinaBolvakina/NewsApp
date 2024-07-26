package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.news.NewsDataRequestDto;
import group40.newsapp.service.newsDataService.UpdateNewsDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news-reaction")
@RequiredArgsConstructor
public class UpdateNewsController {
    private final UpdateNewsDataService updateNewsDataService;

    @Operation(summary = "Update news reaction (like/dislike)")
    @ApiResponse(responseCode = "200", description = "Reaction for news with ID = ... updated successfully")
    @ApiResponse(responseCode = "404", description = "News with ID = .. not found")
    @ApiResponse(responseCode = "409", description = "Conflict: reaction already exists or conflicting reaction")
    @PutMapping
    public ResponseEntity<StandardResponseDto>  updateReaction(@RequestBody NewsDataRequestDto dto) {
        updateNewsDataService.updateReaction(dto);
        return ResponseEntity.ok(new StandardResponseDto("Reaction for news with ID = "+ dto.getNewsId() +" updated successfully"));
    }
/*
    @PutMapping("/{newsId}/{userId}/like")
    public ResponseEntity<NewsDataResponseDto> likeNews(@PathVariable Long newsId, @PathVariable Long userId) {
        return updateNewsDataService.addLikeToNews(newsId, userId);
    }

    @PutMapping("/{newsId}/{userId}/unlike")
    public ResponseEntity<NewsDataResponseDto> unlikeNews(@PathVariable Long newsId, @PathVariable Long userId) {
        return updateNewsDataService.addUnlikeToNews(newsId, userId);
    }

    @PutMapping("/{newsId}/{userId}/removeLike")
    public ResponseEntity<NewsDataResponseDto> removeLike(@PathVariable Long newsId, @PathVariable Long userId) {
        return updateNewsDataService.removeLike(newsId, userId);
    }

    @PutMapping("/{newsId}/{userId}/removeUnlike")
    public ResponseEntity<NewsDataResponseDto> removeUnlike(@PathVariable Long newsId, @PathVariable Long userId) {
        return updateNewsDataService.removeUnlike(newsId, userId);
    }

 */
}
