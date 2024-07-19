package group40.newsapp.service.newsDataService;

import group40.newsapp.models.NewsDataEntity;
import group40.newsapp.models.NewsLike;
import group40.newsapp.models.NewsUnlike;
import group40.newsapp.repository.NewsDataRepositoryInterface;
import group40.newsapp.repository.NewsLikeRepositoryInterface;
import group40.newsapp.repository.NewsUnlikeRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class NewsDataUpdateService {
    private final NewsDataRepositoryInterface newsDataRepository;
    private final NewsLikeRepositoryInterface newsLikeRepository;
    private final NewsUnlikeRepositoryInterface newsUnlikeRepository;

    @Transactional
    public void addLikeToNews(Long newsId, String sessionId, String ipAddress) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        // Prüfen, ob ein Unlike existiert und verhindern, dass ein Like hinzugefügt wird
        if (newsUnlikeRepository.existsByNewsDataAndSessionId(newsData, sessionId) ||
                newsUnlikeRepository.existsByNewsDataAndIpAddress(newsData, ipAddress)) {
            throw new RuntimeException("Cannot like a news item that has already been unliked by this session/IP address");
        }

        if (!newsLikeRepository.existsByNewsDataAndSessionId(newsData, sessionId) &&
                !newsLikeRepository.existsByNewsDataAndIpAddress(newsData, ipAddress)) {
            NewsLike newsLike = new NewsLike();
            newsLike.setNewsData(newsData);
            newsLike.setSessionId(sessionId);
            newsLike.setIpAddress(ipAddress);
            newsLikeRepository.save(newsLike);

            newsData.setLikeCount(newsData.getLikeCount() + 1);
            newsDataRepository.save(newsData);
        }
    }

    @Transactional
    public void addUnlikeToNews(Long newsId, String sessionId, String ipAddress) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        // Prüfen, ob ein Like existiert und verhindern, dass ein Unlike hinzugefügt wird
        if (newsLikeRepository.existsByNewsDataAndSessionId(newsData, sessionId) ||
                newsLikeRepository.existsByNewsDataAndIpAddress(newsData, ipAddress)) {
            throw new RuntimeException("Cannot unlike a news item that has already been liked by this session/IP address");
        }

        if (!newsUnlikeRepository.existsByNewsDataAndSessionId(newsData, sessionId) &&
                !newsUnlikeRepository.existsByNewsDataAndIpAddress(newsData, ipAddress)) {
            NewsUnlike newsUnlike = new NewsUnlike();
            newsUnlike.setNewsData(newsData);
            newsUnlike.setSessionId(sessionId);
            newsUnlike.setIpAddress(ipAddress);
            newsUnlikeRepository.save(newsUnlike);

            newsData.setUnlikeCount(newsData.getUnlikeCount() + 1);
            newsDataRepository.save(newsData);
        }
    }

    @Transactional
    public void removeLike(Long newsId, String sessionId, String ipAddress) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        if (newsLikeRepository.existsByNewsDataAndSessionId(newsData, sessionId) &&
                newsLikeRepository.existsByNewsDataAndIpAddress(newsData, ipAddress)) {
            newsLikeRepository.deleteAllLikesByNewsDataAndSessionId(newsData, sessionId);
            newsLikeRepository.deleteAllLikesByNewsDataAndIpAddress(newsData, ipAddress);

            newsData.setLikeCount(newsData.getLikeCount() - 1);
            newsDataRepository.save(newsData);
        }
    }

    @Transactional
    public void removeUnlike(Long newsId, String sessionId, String ipAddress) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        if (newsUnlikeRepository.existsByNewsDataAndSessionId(newsData, sessionId) &&
                newsUnlikeRepository.existsByNewsDataAndIpAddress(newsData, ipAddress)) {
            newsUnlikeRepository.deleteAllUnlikesByNewsDataAndSessionId(newsData, sessionId);
            newsUnlikeRepository.deleteAllUnlikesByNewsDataAndIpAddress(newsData, ipAddress);

            newsData.setUnlikeCount(newsData.getUnlikeCount() - 1);
            newsDataRepository.save(newsData);
        }
    }
}
