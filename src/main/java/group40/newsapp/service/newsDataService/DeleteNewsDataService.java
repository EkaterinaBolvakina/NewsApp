package group40.newsapp.service.newsDataService;

import group40.newsapp.exception.RestException;
import group40.newsapp.repository.NewsDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNewsDataService {
    private final NewsDataRepository newsDataRepository;

    public ResponseEntity<Void> deleteNewsDataById(Long newsId) {
        if (newsDataRepository.existsById(newsId)) {
            newsDataRepository.deleteById(newsId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "News with id = " + newsId + " not found");
        }
    }
}
