package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.news.NewsCommentRepository;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import group40.newsapp.service.newsDataService.UpdateNewsDataService;
import group40.newsapp.service.user.UserFindService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AddNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final FindNewsDataService findNewsDataService;
    private final UserFindService userFindService;
    private final UpdateNewsDataService updateNewsDataService;

    public void addNewsComment(NewsCommentRequestDTO DTO) {
        User user = userFindService.getUserFromContext();
        NewsDataEntity newsData = findNewsDataService.getNewsById(DTO.getNewsId());
        newsCommentRepository.save(new NewsComment(DTO.getComment(), LocalDateTime.now(), user, newsData));

        updateNewsDataService.increaseCommentsCount(newsData);
    }
}
