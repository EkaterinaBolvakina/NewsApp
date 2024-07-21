package group40.newsapp.service.util.newsCommentMapping;

import group40.newsapp.DTO.newsComment.NewsCommentRequestDTO;
import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.user.User;
import group40.newsapp.service.newsDataService.FindNewsDataService;
import group40.newsapp.service.user.UserFindService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NewsCommentConverter {

    private final UserFindService userFindService;
    private final FindNewsDataService findNewsDataService;

    public NewsComment fromDto(NewsCommentRequestDTO dto) {
        NewsComment newsComment = new NewsComment();

        if (dto.getComment() != null) {
            newsComment.setComment(dto.getComment());
        }

        LocalDateTime publishedTime = LocalDateTime.now();
        newsComment.setCommentDate(publishedTime);

        if (dto.getAuthorId() != null) {
            User user = userFindService.findUserById(dto.getAuthorId());
            newsComment.setUser(user);
        }

        if (dto.getNewsId() != null) {
            NewsDataEntity newsData = findNewsDataService.getNewsById(dto.getNewsId());
            newsComment.setNewsDataEntity(newsData);
        }

        return newsComment;
    }

    public NewsCommentResponseDTO toDto(NewsComment newsComment) {
        NewsCommentResponseDTO dto = new NewsCommentResponseDTO();

        dto.setId(newsComment.getId());

        if (newsComment.getComment() != null) {
            dto.setComment(newsComment.getComment());
        }
        if (newsComment.getUser() != null) {
            dto.setCommentDate(newsComment.getCommentDate());
        }

        if (newsComment.getNewsDataEntity() != null) {
            dto.setNewsId(newsComment.getNewsDataEntity().getId());
        }
        if (newsComment.getUser()!= null) {
            dto.setUserId(newsComment.getUser().getId());
            dto.setAuthorName(newsComment.getUser().getName());
        }
        return dto;
    }
}
