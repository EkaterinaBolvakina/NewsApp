package group40.newsapp.repository;

import group40.newsapp.models.news.NewsComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
    Optional<NewsComment> findById(Long id);
    List<NewsComment> findAllByUserId(Long userId);
    List<NewsComment> findAllByUserName(String userName);
    List<NewsComment> findAllByNewsId(Long newsId);
    List<NewsComment> findAllByCommentDate(LocalDateTime commentDate);

}
