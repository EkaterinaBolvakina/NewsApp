package group40.newsapp.controller.newsController.newsDataController;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.service.newsDataService.AddNewsDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class AddNewsController {
    private final AddNewsDataService addNewsDataService;

    @GetMapping("/loading")
    public ResponseEntity<List<NewsDataResponseDto>> getAllLoadedNews(){
        return addNewsDataService.saveNewsFromFetchApi();
    }
}
