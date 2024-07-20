package group40.newsapp.repository;

import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
    Optional<NewsComment> findById(Long id);
    List<NewsComment> findAllByUserId(Long userId);
    List<NewsComment> findAllByUser(User user);
    List<NewsComment> findAllByNewsDataEntity(NewsDataEntity newsDataEntity);
    List<NewsComment> findAllByNewsDataEntityId(Long newsDataEntityId);
    List<NewsComment> findAllByCommentDate(LocalDateTime commentDate);

}
