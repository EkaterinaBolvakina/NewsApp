package group40.newsapp.controller.regionController;

import group40.newsapp.DTO.region.RegionDTO;
import group40.newsapp.service.regionService.FindRegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
@RequiredArgsConstructor
public class FindRegionController {
    private final FindRegionService service;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> findAllRegions() {
        return service.findAllRegions();
    }

    @GetMapping("/findById")
    public RegionDTO findRegionById(@Valid @RequestParam Long id) {
        return service.findRegionById(id);
    }

    @GetMapping("/findBy")
    public RegionDTO findRegionByName(@Valid @RequestParam String region) {
        return service.findRegionByName(region);
    }
}
