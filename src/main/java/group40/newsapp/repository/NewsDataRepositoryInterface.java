package group40.newsapp.repository;

import group40.newsapp.domain.NewsDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDataRepositoryInterface extends JpaRepository<NewsDataEntity, Integer> {
    List<NewsDataEntity> findByRegionId(Integer regionId);
    List<NewsDataEntity> findBySectionName(String sectionName);

}
