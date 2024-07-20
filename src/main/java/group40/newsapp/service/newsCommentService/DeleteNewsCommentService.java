package group40.newsapp.service.newsCommentService;

import group40.newsapp.exception.RestException;
import group40.newsapp.repository.NewsCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;

    public ResponseEntity<Void> deleteNewsCommentById(Long newsCommentId) {
        if (newsCommentRepository.existsById(newsCommentId)) {
            newsCommentRepository.deleteById(newsCommentId);
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "Comment with id = " + newsCommentId + " not found");
        }
    }
}
