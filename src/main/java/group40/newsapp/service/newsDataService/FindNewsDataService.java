package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.exception.NullArgException;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.repository.news.NewsDataRepository;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FindNewsDataService {

    private final NewsDataRepository newsDataRepository;
    private final NewsDataConverter newsDataConverter;

    public ResponseEntity<List<NewsDataResponseDto>> findAllNews() {
        PageRequest pageRequest = PageRequest.of(0, 300, Sort.by(Sort.Order.desc("id")));
        Page<NewsDataEntity> newsPage = newsDataRepository.findAll(pageRequest);
        List<NewsDataEntity> allNews = newsPage.getContent();

        if (allNews.isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND, "No News found");
        }

        List<NewsDataResponseDto> dtos = allNews.stream()
                .map(newsDataConverter::fromEntityToDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<NewsDataResponseDto> findNewsById(Long id) {
        if (newsDataRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(newsDataConverter.fromEntityToDto(newsDataRepository.findById(id).get()), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News with ID = "+ id +" not found");
        }
    }

    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionId(Long regionId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by(Sort.Order.desc("id")));
        Page<NewsDataEntity> newsPage = newsDataRepository.findByRegionId(regionId, pageRequest);
        List<NewsDataEntity> allNewsByRegionId = newsPage.getContent();
        List<NewsDataResponseDto> DTOs = allNewsByRegionId.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();
        if (!allNewsByRegionId.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for region with ID = "+ regionId+" Not found");
        }
    }

    public ResponseEntity<List<NewsDataResponseDto>> findAllNewsByCriteria(String sectionName, String regionName, Integer page) {
        if ((sectionName == null || sectionName.isEmpty()) && (regionName == null || regionName.isEmpty())) {
            throw new NullArgException("Both section and region name cannot be null or empty");
        }

        if (sectionName == null || sectionName.isEmpty()) {
            return findAllNewsByRegionName(regionName, page);
        }

        if (regionName == null || regionName.isEmpty()) {
            return findAllNewsBySectionName(sectionName, page);
        }

        PageRequest pageRequest = PageRequest.of(page, 30, Sort.by(Sort.Order.desc("id")));
        Page<NewsDataEntity> newsPage = newsDataRepository.findBySectionNameAndRegionRegionName(sectionName, regionName, pageRequest);
        List<NewsDataEntity> allNewsBySectionNameAndRegionName = newsPage.getContent();

        List<NewsDataResponseDto> DTOs = allNewsBySectionNameAndRegionName.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();

        if (!DTOs.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for section: '" + sectionName + "' and region: '" + regionName + "' not found");
        }
    }

    private ResponseEntity<List<NewsDataResponseDto>> findAllNewsBySectionName(String sectionName, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.by(Sort.Order.desc("id")));
        Page<NewsDataEntity> newsPage = newsDataRepository.findBySectionName(sectionName, pageRequest);
        List<NewsDataEntity> allNewsBySectionName = newsPage.getContent();

        List<NewsDataResponseDto> DTOs = allNewsBySectionName.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();

        if (!DTOs.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for section name: '" + sectionName + "' not found");
        }
    }

    private ResponseEntity<List<NewsDataResponseDto>> findAllNewsByRegionName(String regionName, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 30, Sort.by(Sort.Order.desc("id")));
        Page<NewsDataEntity> newsPage = newsDataRepository.findByRegionRegionName(regionName, pageRequest);
        List<NewsDataEntity> allNewsByRegionName = newsPage.getContent();

        List<NewsDataResponseDto> DTOs = allNewsByRegionName.stream()
                .map(newsDataConverter::fromEntityToDto)
                .toList();

        if (!DTOs.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "News Data for region: '" + regionName + "' not found");
        }
    }

    public NewsDataEntity getNewsById(Long id) {
        return newsDataRepository.findById(id)
                .orElseThrow(() -> new RestException(HttpStatus.NOT_FOUND, "News with ID = " + id + " not found"));
    }

}
