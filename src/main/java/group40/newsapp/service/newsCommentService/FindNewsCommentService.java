package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.repository.news.NewsCommentRepository;
import group40.newsapp.service.util.newsCommentMapping.NewsCommentConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FindNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final NewsCommentConverter newsCommentConverter;

    public ResponseEntity<List<NewsCommentResponseDTO>> findAll() {
        List<NewsComment> allNewsComments = newsCommentRepository.findAll();
        if (allNewsComments.isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND, "Comments are not found");
        }
        List<NewsCommentResponseDTO> DTOs = allNewsComments.stream()
                .map(newsCommentConverter::toDto)
                .toList();
        return new ResponseEntity<>(DTOs, HttpStatus.OK);
    }

    public ResponseEntity<NewsCommentResponseDTO> findById(Long id) {
        if (newsCommentRepository.findById(id).isPresent()) {
            return new ResponseEntity<>(newsCommentConverter.toDto(newsCommentRepository.findById(id).get()), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "Comment with ID = "+ id +" not found");
        }
    }

    public ResponseEntity<List<NewsCommentResponseDTO>> findAllCommentsByNewsId(Long newsId) {
        List<NewsComment> allCommentsForNewsId = newsCommentRepository.findAllByNewsDataEntityId(newsId);
        if (allCommentsForNewsId.isEmpty()) {
            throw new RestException(HttpStatus.NOT_FOUND, "Comments for news with ID = " + newsId + " are not found");
        }
        List<NewsCommentResponseDTO> DTOs = allCommentsForNewsId.stream()
                .map(newsCommentConverter::toDto)
                .toList();
        return new ResponseEntity<>(DTOs, HttpStatus.OK);
    }
}

