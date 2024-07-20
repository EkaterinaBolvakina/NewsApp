package group40.newsapp.models.news;

import group40.newsapp.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news_unlike")
public class NewsUnlike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_id", referencedColumnName = "id")
    private NewsDataEntity newsData;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "news_unlike_user_id", referencedColumnName = "id")
    private User user;
    //private String sessionId;
    //private String ipAddress;
}
