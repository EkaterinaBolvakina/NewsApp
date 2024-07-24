package group40.newsapp.service.util.newsMapping;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.region.Region;
import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.DTO.news.newsJsonModel.FetchResponseData;
import group40.newsapp.service.regionService.FindRegionService;
import group40.newsapp.service.util.regionMapping.RegionConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsDataConverter {
    private final FindRegionService findRegionService;
    private final RegionConverter regionConverter;

    public NewsDataResponseDto fromEntityToDto(NewsDataEntity newsDataEntity) {
        NewsDataResponseDto dto = new NewsDataResponseDto();

        dto.setId(newsDataEntity.getId());
        dto.setRegionId(newsDataEntity.getRegion().getId());
        dto.setRegionName(newsDataEntity.getRegion().getRegionName());
        dto.setSectionName(newsDataEntity.getSectionName());
        dto.setTitle(newsDataEntity.getTitle());
        dto.setDate(newsDataEntity.getDate());
        dto.setTitleImageSquare(newsDataEntity.getTitleImageSquare());
        dto.setTitleImageWide(newsDataEntity.getTitleImageWide());
        dto.setContent(newsDataEntity.getContent());
        dto.setLikeCount(newsDataEntity.getLikeCount());
        dto.setUnlikeCount(newsDataEntity.getUnlikeCount());
        dto.setCommentsCount(newsDataEntity.getCommentsCount());
        return dto;
    }

    public NewsDataEntity fromFetchApiToEntity(FetchResponseData dto) {
        NewsDataEntity newsDataEntity = new NewsDataEntity();

        Region region = regionConverter.fromDTO(findRegionService.findRegionById(dto.getRegionId()));
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
