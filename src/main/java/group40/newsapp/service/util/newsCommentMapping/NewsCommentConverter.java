package group40.newsapp.service.util.newsCommentMapping;

import group40.newsapp.DTO.newsComment.NewsCommentResponseDTO;
import group40.newsapp.models.news.NewsComment;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsCommentConverter {

    public NewsCommentResponseDTO toDto(NewsComment newsComment) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String commentAuthorEmail = newsComment.getUser() != null ? newsComment.getUser().getEmail() : "";

        System.out.println("Current user email: " + email);
        System.out.println("Comment author email: " + commentAuthorEmail);

        NewsCommentResponseDTO dto = new NewsCommentResponseDTO();

        dto.setId(newsComment.getId());

        if (newsComment.getComment() != null) {
            dto.setComment(newsComment.getComment());
        }

        if (newsComment.getNewsDataEntity().getId() != null) {
            dto.setNewsId(newsComment.getNewsDataEntity().getId());
        }

        if (newsComment.getCommentDate() != null) {
            dto.setCommentDate(newsComment.getCommentDate());
        }

        if (newsComment.getUser()!= null) {
            dto.setAuthorName(newsComment.getUser().getName());
        }

        dto.setIsPublishedByCurrentUser(email.equals(commentAuthorEmail));

        return dto;
    }
}
