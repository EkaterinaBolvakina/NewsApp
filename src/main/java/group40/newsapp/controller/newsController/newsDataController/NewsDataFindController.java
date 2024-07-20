package group40.newsapp.controller.newsController.newsDataController;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsDataFindController {
    @Autowired
    private FindNewsDataService findNewsDataService;
    @GetMapping
    public List<NewsDataEntity> getAllNews() {
        return findNewsDataService.getAllNews();
    }
}