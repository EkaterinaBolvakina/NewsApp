package group40.newsapp.repository;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsUnlike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsUnlikeRepositoryInterface extends JpaRepository<NewsUnlike, Long> {
    boolean existsByNewsDataAndSessionId(NewsDataEntity newsData, String sessionId);
    boolean existsByNewsDataAndIpAddress(NewsDataEntity newsData, String ipAddress);

    void deleteAllUnlikesByNewsDataAndSessionId(NewsDataEntity newsData, String sessionId);

    void deleteAllUnlikesByNewsDataAndIpAddress(NewsDataEntity newsData, String ipAddress);
}
