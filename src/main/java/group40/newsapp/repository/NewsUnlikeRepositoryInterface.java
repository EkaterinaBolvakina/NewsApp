package group40.newsapp.repository;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsUnlike;
import group40.newsapp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsUnlikeRepositoryInterface extends JpaRepository<NewsUnlike, Long> {
    boolean existsByNewsDataAndUser(NewsDataEntity newsData, User user);

    void deleteAllUnlikesByNewsDataAndUser(NewsDataEntity newsData, User user);
}
