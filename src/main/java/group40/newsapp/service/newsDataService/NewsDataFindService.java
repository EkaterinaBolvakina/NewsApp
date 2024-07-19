package group40.newsapp.service.newsDataService;

import group40.newsapp.models.NewsDataEntity;
import group40.newsapp.repository.NewsDataRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsDataFindService {
    @Autowired
    private NewsDataRepositoryInterface newsDataRepository;

    public List<NewsDataEntity> getAllNews() {
        return newsDataRepository.findAll();
    }

}
