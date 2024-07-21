package group40.newsapp.service.newsDataService;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.DTO.news.newsJsonModel.FetchResponseData;
import group40.newsapp.repository.NewsDataRepository;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddNewsDataService {
    private final FetchNewsApi fetchNewsApi;
    private final NewsDataRepository newsDataRepository;
    private final NewsDataConverter newsDataConverter;

    @Transactional
    public ResponseEntity<List<NewsDataResponseDto>>  saveNewsFromFetchApi() {
        try{
        List<NewsDataResponseDto> responses = new ArrayList<>();
        List<FetchResponseData> newsFromFetch = fetchNewsApi.fetchDataFromApi();

        for (FetchResponseData fetchResponseData : newsFromFetch) {

            NewsDataEntity newsDataEntity = newsDataConverter.fromFetchApiToEntity(fetchResponseData);
            newsDataRepository.save(newsDataEntity);

            NewsDataResponseDto responseDto = newsDataConverter.fromEntityToDto(newsDataEntity);
            responses.add(responseDto);
        }
            return new ResponseEntity<>(responses, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Логирование исключения (рекомендуется использовать логгер)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
