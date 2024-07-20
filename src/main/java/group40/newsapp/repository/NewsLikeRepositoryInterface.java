package group40.newsapp.repository;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsLikeRepositoryInterface extends JpaRepository<NewsLike, Long> {
    boolean existsByNewsDataAndSessionId(NewsDataEntity newsData, String sessionId);
    boolean existsByNewsDataAndIpAddress(NewsDataEntity newsData, String ipAddress);

    void deleteAllLikesByNewsDataAndSessionId(NewsDataEntity newsData, String sessionId);

    void deleteAllLikesByNewsDataAndIpAddress(NewsDataEntity newsData, String ipAddress);
}
