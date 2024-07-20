package group40.newsapp.service.newsCommentService;

import group40.newsapp.repository.NewsCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;

}
