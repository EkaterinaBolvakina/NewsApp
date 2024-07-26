package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.news.NewsDataRequestDto;
import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class FindNewsController {

    private final FindNewsDataService findNewsDataService;

    @GetMapping
    public ResponseEntity<List<NewsDataResponseDto>> findAllNews() {
        ResponseEntity<List<NewsDataResponseDto>> response = findNewsDataService.findAllNews();
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/{newsId}")
    public ResponseEntity<NewsDataResponseDto> findNewsById(@PathVariable Long newsId) {
        ResponseEntity<NewsDataResponseDto> response = findNewsDataService.findNewsById(newsId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/findBy")
    public ResponseEntity<List<NewsDataResponseDto>> findByCriteria(@RequestParam Integer page, @RequestParam String section, @RequestParam String region) {
        ResponseEntity<List<NewsDataResponseDto>> response = findNewsDataService.findAllNewsByCriteria(section, region, page);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping("/region-id")
    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionId(@RequestParam Integer page, @RequestParam Long regionId){
        ResponseEntity<List<NewsDataResponseDto>> response = findNewsDataService.findAllNewsByRegionId(regionId, page);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}