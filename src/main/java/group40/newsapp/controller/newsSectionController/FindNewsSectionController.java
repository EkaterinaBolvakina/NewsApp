package group40.newsapp.controller.newsSectionController;

import group40.newsapp.DTO.news.NewsSectionDTO;
import group40.newsapp.DTO.region.RegionDTO;
import group40.newsapp.controller.api.newsSection.FindNewsSectionApi;
import group40.newsapp.controller.api.region.FindRegionApi;
import group40.newsapp.service.newsSection.FindNewsSectionService;
import group40.newsapp.service.regionService.FindRegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/news-sections")
@RequiredArgsConstructor
public class FindNewsSectionController implements FindNewsSectionApi {
    private final FindNewsSectionService service;

    @Override
    @GetMapping
    public ResponseEntity<List<NewsSectionDTO>> findAllNewsSections() {
        ResponseEntity<List<NewsSectionDTO>> response = service.findAllSections();
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
