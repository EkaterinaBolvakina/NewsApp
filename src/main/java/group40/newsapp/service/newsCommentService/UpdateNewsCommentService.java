package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.DTO.newsComment.UpdateCommentRequestDTO;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.repository.NewsCommentRepository;
import group40.newsapp.service.util.newsCommentMapping.NewsCommentConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UpdateNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final NewsCommentConverter newsCommentConverter;

    @Transactional
    public ResponseEntity<NewsCommentResponseDTO> updateNewsComment(Long newsCommentId, UpdateCommentRequestDTO updateCommentRequestDTO) {
        if (newsCommentRepository.findById(newsCommentId).isPresent()) {

            newsCommentRepository.updateCommentById(updateCommentRequestDTO.getComment(), LocalDateTime.now(), newsCommentId);
            NewsComment updatedNewsComment = newsCommentRepository.findById(newsCommentId).get();
            NewsCommentResponseDTO dto = newsCommentConverter.toDto(updatedNewsComment);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "Comment with id = " + newsCommentId + " not found");
        }
    }
}
