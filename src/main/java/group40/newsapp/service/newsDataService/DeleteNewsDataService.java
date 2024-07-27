package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.exception.RestException;
import group40.newsapp.repository.news.NewsDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNewsDataService {
    private final NewsDataRepository newsDataRepository;

    public StandardResponseDto deleteNewsDataById(Long newsId) {
        if (newsDataRepository.existsById(newsId)) {
            newsDataRepository.deleteById(newsId);
            return new StandardResponseDto("News with ID = "+ newsId +" deleted successfully");
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News with ID = " + newsId + " not found");
        }
    }
}
