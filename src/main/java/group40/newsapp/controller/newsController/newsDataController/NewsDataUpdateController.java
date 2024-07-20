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

    @PostMapping("/{newsId}/like")
    public void likeNews(@PathVariable Long newsId, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String ipAddress = request.getRemoteAddr();
        newsDataUpdateService.addLikeToNews(newsId, sessionId, ipAddress);
    }

    @PostMapping("/{newsId}/unlike")
    public void unlikeNews(@PathVariable Long newsId, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String ipAddress = request.getRemoteAddr();
        newsDataUpdateService.addUnlikeToNews(newsId, sessionId, ipAddress);
    }

    @DeleteMapping("/{newsId}/like")
    public void removeLike(@PathVariable Long newsId, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String ipAddress = request.getRemoteAddr();
        newsDataUpdateService.removeLike(newsId, sessionId, ipAddress);
    }

    @DeleteMapping("/{newsId}/unlike")
    public void removeUnlike(@PathVariable Long newsId, HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String ipAddress = request.getRemoteAddr();
        newsDataUpdateService.removeUnlike(newsId, sessionId, ipAddress);
    }
}
