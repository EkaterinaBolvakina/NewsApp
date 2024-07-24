package group40.newsapp.service.util.regionMapping;

import group40.newsapp.DTO.region.RegionDTO;
import group40.newsapp.models.region.Region;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegionConverter {
    public RegionDTO toDTO(Region region) {
        RegionDTO DTO = new RegionDTO();
        DTO.setId(region.getId());
        DTO.setRegionName(region.getRegionName());
        return DTO;
    }

    public Region fromDTO(RegionDTO DTO) {
        Region region = new Region();
        region.setId(DTO.getId());
        region.setRegionName(DTO.getRegionName());
        return region;
    }
}
