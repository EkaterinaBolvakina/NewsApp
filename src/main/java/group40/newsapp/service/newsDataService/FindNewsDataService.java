package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.repository.NewsDataRepository;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindNewsDataService {

    private final NewsDataRepository newsDataRepository;
    private final NewsDataConverter newsDataConverter;

    public ResponseEntity<List<NewsDataResponseDto>>  findAllNews() {
        List<NewsDataEntity> allNews = newsDataRepository.findAll();
        List<NewsDataResponseDto> DTOs = allNews.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();
        if (!allNews.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data Not Found");
        }
    }

    public ResponseEntity<NewsDataResponseDto> findNewsById(Long id) {
        if (newsDataRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(newsDataConverter.fromEntityToDto(newsDataRepository.findById(id).get()), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News with ID = "+ id +" not found");
        }
    }

    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsBySectionName(String sectionName) {
        List<NewsDataEntity> allNewsBySectionName = newsDataRepository.findBySectionName(sectionName);
        List<NewsDataResponseDto> DTOs = allNewsBySectionName.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();
        if (!allNewsBySectionName.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for section name: '"+ sectionName+"' Not found");
        }
    }

    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionName(String regionName) {
        List<NewsDataEntity> allNewsByRegionName = newsDataRepository.findByRegionRegionName(regionName);
        List<NewsDataResponseDto> DTOs = allNewsByRegionName.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();
        if (!allNewsByRegionName.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for region: '"+ regionName+"' Not found");
        }
    }

    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionId(Long regionId) {
        List<NewsDataEntity> allNewsByRegionId = newsDataRepository.findByRegionId(regionId);
        List<NewsDataResponseDto> DTOs = allNewsByRegionId.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();
        if (!allNewsByRegionId.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for region with ID = "+ regionId+" Not found");
        }
    }

    public NewsDataEntity getNewsById(Long id) {
        return newsDataRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "News with ID = " + id + " not found"));
    }

}
