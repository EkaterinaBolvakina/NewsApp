package group40.newsapp.service.newsDataService;

import group40.newsapp.DTO.news.NewsDataRequestDto;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsReaction;
import group40.newsapp.models.user.User;
import group40.newsapp.repository.news.NewsDataRepository;
import group40.newsapp.repository.news.NewsReactionRepository;
import group40.newsapp.service.user.UserFindService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateNewsDataService {
    private final NewsDataRepository newsDataRepository;
    private final NewsReactionRepository newsReactionRepository;
    private final FindNewsDataService findNewsDataService;
    private final UserFindService userFindService;

    @Transactional
    public void updateReaction(NewsDataRequestDto dto) {
        User user = userFindService.getUserFromContext();
        NewsDataEntity newsData = findNewsDataService.getNewsById(dto.getNewsId());

        // Получить или создать новую реакцию
        NewsReaction reaction = newsReactionRepository.findByNewsDataAndUser(newsData, user)
                .orElse(new NewsReaction());

        int likeChange = 0;
        int dislikeChange = 0;

        if (dto.getLiked() && dto.getDisliked()) {
            if (reaction.getId() == null || (!reaction.getLiked() && !reaction.getDisliked())) {
                throw new RestException(HttpStatus.CONFLICT, "Cannot like and dislike the news simultaneously");
            }
        }

        if (reaction.getId() == null) {
            // Новая реакция
            reaction.setNewsData(newsData);
            reaction.setUser(user);
            reaction.setLiked(dto.getLiked());
            reaction.setDisliked(dto.getDisliked());
            if (dto.getLiked()) {
                likeChange = 1;
            }
            if (dto.getDisliked()) {
                dislikeChange = 1;
            }
        } else {
            // Существующая реакция
            if (dto.getLiked() && dto.getDisliked()) {
                if (reaction.getLiked()) {
                    // Сценарий 1: новость уже лайкнута, лайк убирается, дизлайк добавляется
                    likeChange = -1;
                    dislikeChange = 1;
                    reaction.setLiked(false);
                    reaction.setDisliked(true);
                } else if (reaction.getDisliked()) {
                    // Сценарий 2: новость уже дизлайкнута, дизлайк убирается, лайк добавляется
                    likeChange = 1;
                    dislikeChange = -1;
                    reaction.setLiked(true);
                    reaction.setDisliked(false);
                } else {
                    // Если новость не лайкнута и не дизлайкнута, устанавливаем значения из dto
                    reaction.setLiked(dto.getLiked());
                    reaction.setDisliked(dto.getDisliked());
                    if (dto.getLiked()) {
                        likeChange = 1;
                    }
                    if (dto.getDisliked()) {
                        dislikeChange = 1;
                    }
                }
            } else {
                if (dto.getLiked() && reaction.getLiked()) {
                    throw new RestException(HttpStatus.CONFLICT, "News already liked by this user");
                }
                if (dto.getDisliked() && reaction.getDisliked()) {
                    throw new RestException(HttpStatus.CONFLICT, "News already disliked by this user");
                }
                if (dto.getLiked()) {
                    if (reaction.getDisliked()) {
                        reaction.setDisliked(false);
                        dislikeChange = -1;
                    }
                    reaction.setLiked(true);
                    likeChange = 1;
                } else if (dto.getDisliked()) {
                    if (reaction.getLiked()) {
                        reaction.setLiked(false);
                        likeChange = -1;
                    }
                    reaction.setDisliked(true);
                    dislikeChange = 1;
                } else {
                    // Отмена существующей реакции
                    if (reaction.getLiked()) {
                        reaction.setLiked(false);
                        likeChange = -1;
                    } else if (reaction.getDisliked()) {
                        reaction.setDisliked(false);
                        dislikeChange = -1;
                    }
                }
            }
        }

        newsData.setLikeCount(newsData.getLikeCount() + likeChange);
        newsData.setDislikeCount(newsData.getDislikeCount() + dislikeChange);

        // Сохранить обновленную реакцию и данные о новости
        newsReactionRepository.save(reaction);
        newsDataRepository.save(newsData);


        /*
        NewsDataEntity newsData = findNewsDataService.getNewsById(dto.getNewsId());
        User user = userFindService.getUserFromContext();

        // Fetch or create a new reaction
        NewsReaction reaction = newsReactionRepository.findByNewsDataAndUser(newsData, user)
                .orElse(new NewsReaction());

        if (reaction.getId() == null) {
            reaction.setNewsData(newsData);
            reaction.setUser(user);
        }

        // Prevent simultaneous like and dislike
        if ((dto.getLiked() != null && dto.getLiked()) && (dto.getDisliked() != null && dto.getDisliked())) {
            throw new RestException(HttpStatus.CONFLICT, "Cannot like and dislike the news simultaneously");
        }

        // Check if the action is redundant
        if (dto.getLiked() != null && dto.getLiked() && reaction.getLiked() != null && reaction.getLiked()) {
            throw new RestException(HttpStatus.CONFLICT, "News already liked by this user");
        }

        if (dto.getDisliked() != null && dto.getDisliked() && reaction.getDisliked() != null && reaction.getDisliked()) {
            throw new RestException(HttpStatus.CONFLICT, "News already disliked by this user");
        }

        // Adjust counts based on changes
        boolean isLikedChanged = dto.getLiked() != null && !dto.getLiked().equals(reaction.getLiked());
        boolean isDislikedChanged = dto.getDisliked() != null && !dto.getDisliked().equals(reaction.getDisliked());

        if (isLikedChanged) {
            if (dto.getLiked()) {
                // Increment like count if setting a new like
                newsData.setLikeCount(newsData.getLikeCount() + 1);
            } else if (reaction.getLiked() != null && reaction.getLiked()) {
                // Decrement like count if removing a like
                newsData.setLikeCount(newsData.getLikeCount() - 1);
            }
            reaction.setLiked(dto.getLiked());
        }

        if (isDislikedChanged) {
            if (dto.getDisliked()) {
                // Increment dislike count if setting a new dislike
                newsData.setDislikeCount(newsData.getDislikeCount() + 1);
            } else if (reaction.getDisliked() != null && reaction.getDisliked()) {
                // Decrement dislike count if removing a dislike
                newsData.setDislikeCount(newsData.getDislikeCount() - 1);
            }
            reaction.setDisliked(dto.getDisliked());
        }

        // Save the updated reaction and news data
        newsReactionRepository.save(reaction);
        newsDataRepository.save(newsData);

         */
    }

    public void increaseCommentsCount(NewsDataEntity newsData) {
        newsData.setCommentsCount(newsData.getCommentsCount() + 1);
        newsDataRepository.save(newsData);
    }

    public void reduceCommentsCount(NewsDataEntity newsData) {
        newsData.setCommentsCount(newsData.getCommentsCount() - 1);
        newsDataRepository.save(newsData);
    }
}
    /*

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

     */