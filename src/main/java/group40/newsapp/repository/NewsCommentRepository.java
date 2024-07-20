package group40.newsapp.repository;

import group40.newsapp.models.news.NewsComment;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
    Optional<NewsComment> findById(Long id);
    List<NewsComment> findAllByUserId(Long userId);
    List<NewsComment> findAllByUserName(String name);
    List<NewsComment> findAllByNewsDataEntityId(Long newsDataEntityId);
    List<NewsComment> findAllByCommentDate(LocalDateTime commentDate);

    @Modifying
    @Query("UPDATE NewsComment c SET c.comment = :comment, c.commentDate = :commentDate WHERE c.id = :id")
    void updateCommentById(@Param("comment") String comment, @Param("commentDate") LocalDateTime commentDate, @Param("id") Long id);

}
