package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.DTO.news.NewsDataRequestDto;
import group40.newsapp.controller.api.news.UpdateNewsApi;
import group40.newsapp.service.newsDataService.UpdateNewsDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UpdateNewsController implements UpdateNewsApi {
    private final UpdateNewsDataService updateNewsDataService;
    @Override
    public ResponseEntity<StandardResponseDto> updateReaction(@Valid @RequestBody NewsDataRequestDto dto) {
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
