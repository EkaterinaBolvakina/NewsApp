package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.service.newsDataService.UpdateNewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class UpdateNewsController {
    private final UpdateNewsDataService updateNewsDataService;

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
}
