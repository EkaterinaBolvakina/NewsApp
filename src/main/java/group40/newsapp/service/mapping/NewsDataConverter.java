package group40.newsapp.service.mapping;

import group40.newsapp.models.NewsDataEntity;
import group40.newsapp.models.Region;
import group40.newsapp.dto.NewsDataResponseDto;
import group40.newsapp.dto.newsJsonModel.FetchResponseData;
import group40.newsapp.service.regionService.RegionFindService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsDataConverter {
    private final RegionFindService regionFindService;

    public NewsDataResponseDto fromEntityToDto(NewsDataEntity newsDataEntity) {
        NewsDataResponseDto dto = new NewsDataResponseDto();
        dto.setId(newsDataEntity.getId());
        dto.setRegionId(newsDataEntity.getRegion().getRegionNewsId());
        dto.setRegionName(newsDataEntity.getRegion().getRegionName());
        dto.setSectionName(newsDataEntity.getSectionName());
        dto.setTitle(newsDataEntity.getTitle());
        dto.setDate(newsDataEntity.getDate());
        dto.setTitleImageSquare(newsDataEntity.getTitleImageSquare());
        dto.setTitleImageWide(newsDataEntity.getTitleImageWide());
        dto.setContent(newsDataEntity.getContent());
        dto.setLikeCount(newsDataEntity.getLikeCount());
        dto.setUnlikeCount(newsDataEntity.getUnlikeCount());
        return dto;
    }

    public NewsDataEntity fromFetchApiToEntity(FetchResponseData dto) {
        NewsDataEntity newsDataEntity = new NewsDataEntity();

        Region region = regionFindService.findRegionByRegionNewsId(dto.getRegionId());
        newsDataEntity.setRegion(region);
        newsDataEntity.setSectionName(dto.getSectionName());
        newsDataEntity.setTitle(dto.getTitle());
        newsDataEntity.setDate(dto.getDate());
        newsDataEntity.setTitleImageSquare(dto.getTitleImageSquare());
        newsDataEntity.setTitleImageWide(dto.getTitleImageWide());
        newsDataEntity.setContent(dto.getContent());
        return newsDataEntity;
    }
}
