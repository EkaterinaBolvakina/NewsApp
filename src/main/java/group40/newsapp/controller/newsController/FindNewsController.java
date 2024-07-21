package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class FindNewsController {

    private final FindNewsDataService findNewsDataService;

    @GetMapping
    public ResponseEntity<List<NewsDataResponseDto>> findAllNews() {
        return findNewsDataService.findAllNews();
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<NewsDataResponseDto> findNewsById(@PathVariable Long newsId) {
        return findNewsDataService.findNewsById(newsId);
    }
    //localhost:8080/api/news/section/sport
    @GetMapping("/section/{sectionName}")
    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsBySectionName(@PathVariable String sectionName){
        return findNewsDataService.findAllNewsBySectionName(sectionName);
    }

    @GetMapping("/region/name/{regionName}")
    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionName(@PathVariable String regionName){
        return findNewsDataService.findAllNewsByRegionName(regionName);
    }

    @GetMapping("/region/id/{regionId}")
    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionId(@PathVariable Long regionId){
        return findNewsDataService.findAllNewsByRegionId(regionId);
    }




}