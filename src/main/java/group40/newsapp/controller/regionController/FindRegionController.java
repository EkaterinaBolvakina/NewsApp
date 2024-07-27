package group40.newsapp.controller.regionController;

import group40.newsapp.DTO.region.RegionDTO;
import group40.newsapp.controller.api.region.FindRegionApi;
import group40.newsapp.service.regionService.FindRegionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class FindRegionController implements FindRegionApi {
    private final FindRegionService service;

    @Override
    @GetMapping
    public ResponseEntity<List<RegionDTO>> findAllRegions() {
        ResponseEntity<List<RegionDTO>> response = service.findAllRegions();
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
    @Override
    @GetMapping("/findById")
    public ResponseEntity<RegionDTO> findRegionById(@Valid @RequestParam Long id) {
        return new ResponseEntity<>(service.findRegionById(id), HttpStatus.OK);
    }
    @Override
    @GetMapping("/findBy")
    public ResponseEntity<RegionDTO> findRegionByName(@Valid @RequestParam String region) {
        return new ResponseEntity<>(service.findRegionByName(region), HttpStatus.OK);
    }
}
