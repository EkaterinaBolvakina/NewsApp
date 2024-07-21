package group40.newsapp.service.newsDataService;

import group40.newsapp.repository.NewsDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNewsDataService {
    private final NewsDataRepository newsDataRepository;
}
