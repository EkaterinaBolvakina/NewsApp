package group40.newsapp.controller;

import group40.newsapp.dto.NewsDataResponseDto;
import group40.newsapp.service.newsDataService.NewsDataAddService;
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
    private final NewsDataAddService newsDataAddService;

    @GetMapping
    public List<NewsDataResponseDto> getAllLoadedNews() throws IOException {
        return newsDataAddService.saveNewsFromFetchApi();
    }
}
