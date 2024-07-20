package group40.newsapp.models.news;

import group40.newsapp.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private LocalDateTime commentDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="news_id")
    private NewsDataEntity newsDataEntity;

    public NewsComment(String comment, User user, NewsDataEntity newsDataEntity) {
        this.comment = comment;
        this.user = user;
        this.newsDataEntity = newsDataEntity;
    }

    public NewsComment(Long id, String comment, LocalDateTime commentDate) {
        this.id = id;
        this.comment = comment;
        this.commentDate = commentDate;
    }
}
