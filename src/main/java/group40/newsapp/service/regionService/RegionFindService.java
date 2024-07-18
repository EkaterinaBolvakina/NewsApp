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

    public Region findRegionByRegionId(Integer regionId) {
        Optional<Region> foundedRegionOpt= regionRepository.findRegionByRegionNewsId(regionId);

        if (foundedRegionOpt.isPresent()) {
            return foundedRegionOpt.get();
        }else {
            throw new RuntimeException();
        }
    }

}
