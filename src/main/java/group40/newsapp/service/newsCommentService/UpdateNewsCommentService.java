package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.newsComment.UpdateCommentRequestDTO;
import group40.newsapp.exception.NotFoundException;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.user.Role;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.news.NewsCommentRepository;
import group40.newsapp.repository.user.RoleRepository;
import group40.newsapp.service.user.UserFindService;
import group40.newsapp.service.util.newsCommentMapping.NewsCommentConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UpdateNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final UserFindService userFindService;
    private final RoleRepository roleRepository;

    @Transactional
    public void updateNewsComment(UpdateCommentRequestDTO dto) {
        User user = userFindService.getUserFromContext();
        NewsComment comment = newsCommentRepository.findById(dto.getId())
                .orElseThrow(()-> new NotFoundException("Comment with ID = " + dto.getId() + " not found"));
        Role role = roleRepository.findByRole("ADMIN");
        if (user != comment.getUser() && user.getRole() != role){
            throw new RestException(HttpStatus.CONFLICT, "You don't have permission to update this comment");
        }
        newsCommentRepository.updateCommentById(dto.getComment(), LocalDateTime.now(), dto.getId());
    }
}
