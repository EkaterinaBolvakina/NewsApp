package group40.newsapp.controller.newsController.newsDataController;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.service.newsDataService.AddNewsDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/loading")
@AllArgsConstructor
public class NewsDataAddController {
    private final AddNewsDataService addNewsDataService;

    @GetMapping
    public List<NewsDataResponseDto> getAllLoadedNews() throws IOException {
        return addNewsDataService.saveNewsFromFetchApi();
    }
}
