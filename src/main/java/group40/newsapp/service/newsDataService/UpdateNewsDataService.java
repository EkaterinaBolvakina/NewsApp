package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.news.NewsDataResponseDto;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsLike;
import group40.newsapp.models.news.NewsUnlike;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.NewsDataRepository;
import group40.newsapp.repository.NewsLikeRepository;
import group40.newsapp.repository.NewsUnlikeRepository;
import group40.newsapp.service.user.UserFindService;
import group40.newsapp.service.util.newsMapping.NewsDataConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateNewsDataService {
    private final NewsDataRepository newsDataRepository;
    private final NewsLikeRepository newsLikeRepository;
    private final NewsUnlikeRepository newsUnlikeRepository;
    private final FindNewsDataService findNewsDataService;
    private final UserFindService userFindService;
    private final NewsDataConverter newsDataConverter;

    @Transactional
    public ResponseEntity<NewsDataResponseDto> addLikeToNews(Long newsId, Long userId) {
        NewsDataEntity newsData = findNewsDataService.getNewsById(newsId);
        User user = userFindService.findUserById(userId);

        // Prüfen, ob ein Unlike existiert und verhindern, dass ein Like hinzugefügt wird
        if (newsUnlikeRepository.existsByNewsDataAndUser(newsData, user))  {
            throw new RestException(HttpStatus.CONFLICT,
                    "Cannot like a news with ID: "+ newsId +
                            " that has already been unliked by this user id: " + userId);
        }

        if (!newsLikeRepository.existsByNewsDataAndUser(newsData, user)){
            NewsLike newsLike = new NewsLike();
            newsLike.setNewsData(newsData);
            newsLike.setUser(user);
            newsLikeRepository.save(newsLike);

        //UPDATE NEWS with like count:
            newsData.setLikeCount(newsData.getLikeCount() + 1);
            newsDataRepository.save(newsData);
            return new ResponseEntity<>(newsDataConverter.fromEntityToDto(newsData), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.MULTIPLE_CHOICES,
                    "Cannot like a news with ID: "+ newsId +
                            " that has already been liked by this user id: " + userId);
        }
    }

    @Transactional
    public ResponseEntity<NewsDataResponseDto> addUnlikeToNews(Long newsId, Long userId) {
        NewsDataEntity newsData = findNewsDataService.getNewsById(newsId);
        User user = userFindService.findUserById(userId);

        // Prüfen, ob ein Like existiert und verhindern, dass ein Unlike hinzugefügt wird
        if (newsLikeRepository.existsByNewsDataAndUser(newsData, user)) {
            throw new RestException(HttpStatus.CONFLICT,
                    "Cannot unlike a news with ID: "+ newsId +
                            " that has already been liked by this user id: " + userId);
        }

        if (!newsUnlikeRepository.existsByNewsDataAndUser(newsData, user)) {
            NewsUnlike newsUnlike = new NewsUnlike();
            newsUnlike.setNewsData(newsData);
            newsUnlike.setUser(user);
            newsUnlikeRepository.save(newsUnlike);

        //UPDATE NEWS with unlike count:
            newsData.setUnlikeCount(newsData.getUnlikeCount() + 1);
            newsDataRepository.save(newsData);
            return new ResponseEntity<>(newsDataConverter.fromEntityToDto(newsData), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.MULTIPLE_CHOICES,
                    "Cannot unlike a news with ID: "+ newsId +
                            " that has already been unliked by this user id: " + userId);
        }
    }

    @Transactional
    public ResponseEntity<NewsDataResponseDto> removeLike(Long newsId, Long userId) {
        NewsDataEntity newsData = findNewsDataService.getNewsById(newsId);
        User user = userFindService.findUserById(userId);

        if (newsLikeRepository.existsByNewsDataAndUser(newsData, user)) {
            newsLikeRepository.deleteAllLikesByNewsDataAndUser(newsData, user);

        //UPDATE NEWS with like count:
            newsData.setLikeCount(newsData.getLikeCount() - 1);
            newsDataRepository.save(newsData);
            return new ResponseEntity<>(newsDataConverter.fromEntityToDto(newsData), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.MULTIPLE_CHOICES,
                    "Cannot remove like for news ID: "+ newsId +
                            " because it has already been removed by this user id: " + userId);
        }
    }

    @Transactional
    public ResponseEntity<NewsDataResponseDto> removeUnlike(Long newsId, Long userId) {
        NewsDataEntity newsData = findNewsDataService.getNewsById(newsId);
        User user = userFindService.findUserById(userId);

        if (newsUnlikeRepository.existsByNewsDataAndUser(newsData, user)) {
            newsUnlikeRepository.deleteAllUnlikesByNewsDataAndUser(newsData, user);

        //UPDATE NEWS with unlike count:
            newsData.setUnlikeCount(newsData.getUnlikeCount() - 1);
            newsDataRepository.save(newsData);
            return new ResponseEntity<>(newsDataConverter.fromEntityToDto(newsData), HttpStatus.OK);
        }else {
            throw new RestException(HttpStatus.MULTIPLE_CHOICES,
                    "Cannot remove unlike for news ID: "+ newsId +
                    " because it has already been removed by this user id: " + userId);
        }
    }
}
