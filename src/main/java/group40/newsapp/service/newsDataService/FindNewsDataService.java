package group40.newsapp.service.newsDataService;

import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.repository.NewsDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindNewsDataService {
    @Autowired
    private NewsDataRepository newsDataRepository;

    public List<NewsDataEntity> getAllNews() {
        return newsDataRepository.findAll();
    }

    public NewsDataEntity findNewsById(Long id) {
        return newsDataRepository.findById(id)
                .orElseThrow(()
                        -> new RestException(HttpStatus.NOT_FOUND, "News with id = "+ id +" not found"));
    }

}
