package group40.newsapp.service.regionService;

import group40.newsapp.models.region.Region;
import group40.newsapp.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FindRegionService {
    private final RegionRepository regionRepository;

    public List<Region> findAll() {return regionRepository.findAll();}

    public Region findRegionById(Long id) {
        Optional<Region> foundedRegionOpt= regionRepository.findById(id);

        if (foundedRegionOpt.isPresent()) {
            return foundedRegionOpt.get();
        }else {
            throw new RuntimeException();
        }
    }

    public Region findRegionByName(String regionName) {
        Optional<Region> foundedRegionOpt= regionRepository.findByRegionName(regionName);

        if (foundedRegionOpt.isPresent()) {
            return foundedRegionOpt.get();
        }else {
            throw new RuntimeException();
        }
    }
/*
    public Region findRegionByRegionNewsId(Integer regionNewsId) {
        Optional<Region> foundedRegionOpt= regionRepository.findByRegionNewsId(regionNewsId);

        if (foundedRegionOpt.isPresent()) {
            return foundedRegionOpt.get();
        }else {
            throw new RuntimeException();
        }
    }

 */
}
