package group40.newsapp.repository.news;

import group40.newsapp.models.news.NewsDataEntity;
import group40.newsapp.models.news.NewsReaction;
import group40.newsapp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NewsReactionRepository extends JpaRepository<NewsReaction, Long> {

    Optional<NewsReaction> findByNewsDataAndUser(NewsDataEntity newsData, User user);
}