package group40.newsapp.service.regionService;

import group40.newsapp.domain.Region;
import group40.newsapp.repository.RegionRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegionFindService {
    private final RegionRepositoryInterface regionRepository;

    public Region findRegionByRegionNewsId(Integer regionNewsId) {
        Optional<Region> foundedRegionOpt= regionRepository.findByRegionNewsId(regionNewsId);

        if (foundedRegionOpt.isPresent()) {
            return foundedRegionOpt.get();
        }else {
            throw new RuntimeException();
        }
    }

    public Region findRegionById(Integer regionId) {
        Optional<Region> foundedRegionOpt= regionRepository.findById(regionId);

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

}
