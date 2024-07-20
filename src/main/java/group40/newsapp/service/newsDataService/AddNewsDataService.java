package group40.newsapp.service.newsDataService;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.DTO.news.newsJsonModel.FetchResponseData;
import group40.newsapp.repository.NewsDataRepository;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddNewsDataService {
    private final FetchNewsApi fetchNewsApi;
    private final NewsDataRepository newsDataRepository;
    private final NewsDataConverter newsDataConverter;

    @Transactional
    public List<NewsDataResponseDto> saveNewsFromFetchApi() throws IOException {
        List<NewsDataResponseDto> responses = new ArrayList<>();
        List<FetchResponseData> newsFromFetch = fetchNewsApi.fetchDataFromApi();

        for (FetchResponseData fetchResponseData : newsFromFetch) {

            NewsDataEntity newsDataEntity = newsDataConverter.fromFetchApiToEntity(fetchResponseData);
            newsDataRepository.save(newsDataEntity);

            NewsDataResponseDto responseDto = newsDataConverter.fromEntityToDto(newsDataEntity);
            responses.add(responseDto);
        }
        return responses;
    }
}
