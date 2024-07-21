package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.NewsCommentRepository;
import group40.newsapp.repository.UserRepository;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import group40.newsapp.service.newsDataService.UpdateNewsDataService;
import group40.newsapp.service.user.UserFindService;
import group40.newsapp.service.util.newsCommentMapping.NewsCommentConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final NewsCommentConverter newsCommentConverter;
    private final FindNewsDataService findNewsDataService;
    private final UserFindService userFindService;
    private final UpdateNewsDataService updateNewsDataService;

    public ResponseEntity<NewsCommentResponseDTO> addNewsComment(NewsCommentRequestDTO newsCommentRequestDTO) {

            NewsDataEntity newsData = findNewsDataService.getNewsById(newsCommentRequestDTO.getNewsId());
            User user = userFindService.findUserById(newsCommentRequestDTO.getAuthorId());

            NewsComment newsCommentForAdd = newsCommentConverter.fromDto(newsCommentRequestDTO);
            NewsComment savedNewsComment = newsCommentRepository.save(newsCommentForAdd);
            NewsCommentResponseDTO dto = newsCommentConverter.toDto(savedNewsComment);

            updateNewsDataService.upCommentsCount(newsData.getId());

            return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }

}
