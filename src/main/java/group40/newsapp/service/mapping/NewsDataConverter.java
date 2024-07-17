package group40.newsapp.service.mapping;

import group40.newsapp.domain.NewsDataEntity;
import group40.newsapp.dto.NewsDataResponseDto;
import group40.newsapp.dto.newsJsonModel.FetchResponseData;
import org.springframework.stereotype.Service;

@Service
public class NewsDataConverter {
    public NewsDataResponseDto fromEntityToDto(NewsDataEntity newsDataEntity) {
        NewsDataResponseDto dto = new NewsDataResponseDto();
        dto.setId(newsDataEntity.getId());
        dto.setRegionId(newsDataEntity.getRegionId());
        dto.setRegionName(newsDataEntity.getRegionName());
        dto.setSectionName(newsDataEntity.getSectionName());
        dto.setTitle(newsDataEntity.getTitle());
        dto.setDate(newsDataEntity.getDate());
        dto.setTitleImageSquare(newsDataEntity.getTitleImageSquare());
        dto.setTitleImageWide(newsDataEntity.getTitleImageWide());
        dto.setContent(newsDataEntity.getContent());
        return dto;
    }

    public NewsDataEntity fromFetchApiToEntity(FetchResponseData dto) {
        NewsDataEntity newsDataEntity = new NewsDataEntity();
        newsDataEntity.setRegionId(dto.getRegionId());
        newsDataEntity.setRegionName(dto.getRegionName());
        newsDataEntity.setSectionName(dto.getSectionName());
        newsDataEntity.setTitle(dto.getTitle());
        newsDataEntity.setDate(dto.getDate());
        newsDataEntity.setTitleImageSquare(dto.getTitleImageSquare());
        newsDataEntity.setTitleImageWide(dto.getTitleImageWide());
        newsDataEntity.setContent(dto.getContent());
        return newsDataEntity;
    }
}
