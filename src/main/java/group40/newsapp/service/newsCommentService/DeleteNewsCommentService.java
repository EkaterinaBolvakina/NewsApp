package group40.newsapp.service.newsCommentService;

import group40.newsapp.DTO.appDTO.StandardDelRequest;
import group40.newsapp.exception.NotFoundException;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.user.Role;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.news.NewsCommentRepository;
import group40.newsapp.repository.user.RoleRepository;
import group40.newsapp.service.newsDataService.UpdateNewsDataService;
import group40.newsapp.service.user.UserFindService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeleteNewsCommentService {
    private final NewsCommentRepository newsCommentRepository;
    private final UpdateNewsDataService updateNewsDataService;
    private final UserFindService userFindService;
    private final RoleRepository roleRepository;

    public void deleteNewsCommentById(StandardDelRequest dto) {

        User user = userFindService.getUserFromContext();
        NewsComment comment = newsCommentRepository.findById(dto.getId())
                .orElseThrow(()-> new NotFoundException("Comment with id = " + dto.getId() + " not found"));
        Role role = roleRepository.findByRole("ADMIN");
        if (user != comment.getUser() && user.getRole() != role){
            throw new RestException(HttpStatus.CONFLICT, "You don't have permission to delete this message");
        }
        updateNewsDataService.reduceCommentsCount(comment.getNewsDataEntity());
        newsCommentRepository.delete(comment);
    }
}
