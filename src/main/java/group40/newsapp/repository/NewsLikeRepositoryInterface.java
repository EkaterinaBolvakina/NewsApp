package group40.newsapp.repository;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsLike;
import group40.newsapp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLikeRepositoryInterface extends JpaRepository<NewsLike, Long> {

    boolean existsByNewsDataAndUser(NewsDataEntity newsData, User user);

    void deleteAllLikesByNewsDataAndUser(NewsDataEntity newsData, User user);

}
