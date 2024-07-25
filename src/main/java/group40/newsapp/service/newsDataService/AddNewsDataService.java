package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.DTO.news.newsJsonModel.FetchNewsDataDTO;
import group40.newsapp.repository.news.NewsDataRepository;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddNewsDataService {
    private final FetchNewsApi fetchNewsApi;
    private final NewsDataRepository newsDataRepository;
    private final NewsDataConverter newsDataConverter;

    @Transactional
    public StandardResponseDto saveNewsFromFetchApi() {
        Map<String, FetchNewsDataDTO> newsFromFetch = fetchNewsApi.fetchDataFromApi();
        Optional<String> lastDateOpt = newsDataRepository.findLastDate();

        String lastDate = lastDateOpt.orElse("0000-00-00T00:00:00");

        for (FetchNewsDataDTO fetchNewsDataDTO : newsFromFetch.values()) {
            String newsDate = fetchNewsDataDTO.getDate();

            if (newsDate.compareTo(lastDate) > 0) {
                NewsDataEntity newsDataEntity = newsDataConverter.fromFetchApiToEntity(fetchNewsDataDTO);
                newsDataRepository.save(newsDataEntity);
            }
        }
        return new StandardResponseDto("All news loaded successfully");
    }
}
