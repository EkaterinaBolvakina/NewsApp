package group40.newsapp.controller.newsController;

import group40.newsapp.service.newsDataService.DeleteNewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class DeleteNewsController {
    private final DeleteNewsDataService deleteNewsDataService;

    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<Void> deleteNewsById(@PathVariable Long newsId) {
        return deleteNewsDataService.deleteNewsDataById(newsId);
    }
}
