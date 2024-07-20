package group40.newsapp.controller.newsController.newsDataController;

import group40.newsapp.service.newsDataService.NewsDataUpdateService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsDataUpdateController {
    private final NewsDataUpdateService newsDataUpdateService;

    @PostMapping("/{newsId}/{userId}/like")
    public void likeNews(@PathVariable Long newsId, @PathVariable Long userId) {
        newsDataUpdateService.addLikeToNews(newsId, userId);
    }

    @PostMapping("/{newsId}/{userId}/unlike")
    public void unlikeNews(@PathVariable Long newsId, @PathVariable Long userId) {
        newsDataUpdateService.addUnlikeToNews(newsId, userId);
    }

    @DeleteMapping("/{newsId}/{userId}/like")
    public void removeLike(@PathVariable Long newsId, @PathVariable Long userId) {
        newsDataUpdateService.removeLike(newsId, userId);
    }

    @DeleteMapping("/{newsId}/{userId}/unlike")
    public void removeUnlike(@PathVariable Long newsId, @PathVariable Long userId) {
        newsDataUpdateService.removeUnlike(newsId, userId);
    }
}
