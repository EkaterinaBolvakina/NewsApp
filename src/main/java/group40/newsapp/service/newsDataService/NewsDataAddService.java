package group40.newsapp.service.newsDataService;

import group40.newsapp.domain.NewsDataEntity;
import group40.newsapp.dto.NewsDataResponseDto;
import group40.newsapp.dto.newsJsonModel.FetchResponseData;
import group40.newsapp.repository.NewsDataRepositoryInterface;
import group40.newsapp.service.mapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class NewsDataAddService {
    private final FetchNewsApi fetchNewsApi;
    private final NewsDataRepositoryInterface newsDataRepository;
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
