package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.repository.NewsCommentRepository;
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

    public ResponseEntity<NewsCommentResponseDTO> addNewsComment(NewsCommentRequestDTO newsCommentRequestDTO) {
        try{
            NewsComment newsCommentForAdd = newsCommentConverter.fromDto(newsCommentRequestDTO);
            NewsComment savedNewsComment = newsCommentRepository.save(newsCommentForAdd);
            NewsCommentResponseDTO dto = newsCommentConverter.toDto(savedNewsComment);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Логирование исключения (рекомендуется использовать логгер)
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
