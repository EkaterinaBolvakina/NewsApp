package group40.newsapp.service.newsDataService;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsLike;
import group40.newsapp.models.news.NewsUnlike;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.NewsDataRepository;
import group40.newsapp.repository.NewsLikeRepository;
import group40.newsapp.repository.NewsUnlikeRepository;
import group40.newsapp.service.user.UserFindService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateNewsDataService {
    private final NewsDataRepository newsDataRepository;
    private final NewsLikeRepository newsLikeRepository;
    private final NewsUnlikeRepository newsUnlikeRepository;
    private final UserFindService userFindService;

    @Transactional
    public void addLikeToNews(Long newsId, Long userId) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        User user = userFindService.findUserById(userId);

        // Prüfen, ob ein Unlike existiert und verhindern, dass ein Like hinzugefügt wird
        if (newsUnlikeRepository.existsByNewsDataAndUser(newsData, user))  {
            throw new RuntimeException("Cannot like a news item that has already been unliked by this user id: " + userId);
        }

        if (!newsLikeRepository.existsByNewsDataAndUser(newsData, user)){
            NewsLike newsLike = new NewsLike();
            newsLike.setNewsData(newsData);
            newsLike.setUser(user);
            newsLikeRepository.save(newsLike);

            newsData.setLikeCount(newsData.getLikeCount() + 1);
            newsDataRepository.save(newsData);
        }
    }

    @Transactional
    public void addUnlikeToNews(Long newsId, Long userId) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        User user = userFindService.findUserById(userId);

        // Prüfen, ob ein Like existiert und verhindern, dass ein Unlike hinzugefügt wird
        if (newsLikeRepository.existsByNewsDataAndUser(newsData, user)) {
            throw new RuntimeException("Cannot unlike a news item that has already been liked by this user id: " + userId);
        }

        if (!newsUnlikeRepository.existsByNewsDataAndUser(newsData, user)) {
            NewsUnlike newsUnlike = new NewsUnlike();
            newsUnlike.setNewsData(newsData);
            newsUnlike.setUser(user);
            newsUnlikeRepository.save(newsUnlike);

            newsData.setUnlikeCount(newsData.getUnlikeCount() + 1);
            newsDataRepository.save(newsData);
        }
    }

    @Transactional
    public void removeLike(Long newsId, Long userId) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        User user = userFindService.findUserById(userId);

        if (newsLikeRepository.existsByNewsDataAndUser(newsData, user)) {
            newsLikeRepository.deleteAllLikesByNewsDataAndUser(newsData, user);

            newsData.setLikeCount(newsData.getLikeCount() - 1);
            newsDataRepository.save(newsData);
        }
    }

    @Transactional
    public void removeUnlike(Long newsId, Long userId) {
        NewsDataEntity newsData = newsDataRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        User user = userFindService.findUserById(userId);

        if (newsUnlikeRepository.existsByNewsDataAndUser(newsData, user)) {
            newsUnlikeRepository.deleteAllUnlikesByNewsDataAndUser(newsData, user);

            newsData.setUnlikeCount(newsData.getUnlikeCount() - 1);
            newsDataRepository.save(newsData);
        }
    }
}
