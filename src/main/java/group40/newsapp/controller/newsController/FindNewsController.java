package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.news.NewsDataPageResponseDto;
import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.controller.api.news.FindNewsApi;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class FindNewsController implements FindNewsApi {

    private final FindNewsDataService findNewsDataService;
    @Override
    @GetMapping
    public ResponseEntity<NewsDataPageResponseDto> findAllNews(@RequestParam Integer page) {
        ResponseEntity<NewsDataPageResponseDto> response = findNewsDataService.findAllNews(page);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @Override
    @GetMapping("/{newsId}")
    public ResponseEntity<NewsDataResponseDto> findNewsById(@PathVariable Long newsId) {
        ResponseEntity<NewsDataResponseDto> response = findNewsDataService.findNewsById(newsId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
    @Override
    @GetMapping("/findBy")
    public ResponseEntity<NewsDataPageResponseDto> findByCriteria(@RequestParam Integer page, @RequestParam(required = false) String section, @RequestParam(required = false) String region) {
        ResponseEntity<NewsDataPageResponseDto> response = findNewsDataService.findAllNewsByCriteria(section, region, page);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @Override
    @GetMapping("/region-id")
    public ResponseEntity<NewsDataPageResponseDto> findAllNewsByRegionId(@RequestParam Integer page, @RequestParam Long regionId){
        ResponseEntity<NewsDataPageResponseDto> response = findNewsDataService.findAllNewsByRegionId(regionId, page);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}