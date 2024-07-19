package group40.newsapp.repository;

import group40.newsapp.domain.NewsDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsDataRepositoryInterface extends JpaRepository<NewsDataEntity, Long> {
    //Optional<NewsDataEntity> findById(Long id);
    List<NewsDataEntity> findBySectionName(String sectionName);
    List<NewsDataEntity> findByRegionId(Integer regionNewsId);

}
