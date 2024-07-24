package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.DTO.news.newsJsonModel.FetchNewsDataDTO;
import group40.newsapp.repository.news.NewsDataRepository;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class AddNewsDataService {
    private final FetchNewsApi fetchNewsApi;
    private final NewsDataRepository newsDataRepository;
    private final NewsDataConverter newsDataConverter;

    @Transactional
    public StandardResponseDto saveNewsFromFetchApi() {
        List<FetchNewsDataDTO> newsFromFetch = fetchNewsApi.fetchDataFromApi();

            for (FetchNewsDataDTO fetchNewsDataDTO : newsFromFetch) {
                NewsDataEntity newsDataEntity = newsDataConverter.fromFetchApiToEntity(fetchNewsDataDTO);

                if (newsDataRepository.findByTitle(newsDataEntity.getTitle()).isEmpty()) {
                    newsDataRepository.save(newsDataEntity);
                }
            }
            return new StandardResponseDto("All news loaded successfully");

    }
}
