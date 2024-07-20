package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.repository.NewsCommentRepository;
import group40.newsapp.service.util.newsCommentMapping.NewsCommentConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FindNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final NewsCommentConverter newsCommentConverter;

    public ResponseEntity<List<NewsCommentResponseDTO>> findAll() {
        List<NewsComment> allNewsComments = newsCommentRepository.findAll();
        List<NewsCommentResponseDTO> DTOs = allNewsComments.stream()
                .map(newsCommentConverter::toDto)
                .toList();
        if (!allNewsComments.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.NOT_FOUND, "Comments are not found");
        }
    }
}
