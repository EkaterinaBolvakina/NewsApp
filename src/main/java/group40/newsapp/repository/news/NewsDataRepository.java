package group40.newsapp.repository.news;

import group40.newsapp.models.news.NewsDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsDataRepository extends JpaRepository<NewsDataEntity, Long> {
    Optional<NewsDataEntity> findById(Long id);

    Page<NewsDataEntity> findBySectionNameAndRegionRegionName(String sectionName, String regionName, Pageable pageable);
    Page<NewsDataEntity> findByRegionRegionName(String regionName, Pageable pageable);
    Page<NewsDataEntity> findBySectionName(String sectionName, Pageable pageable);
    Page<NewsDataEntity> findByRegionId(Long regionId, Pageable pageable);
/*
    List<NewsDataEntity> findByRegionId(Long regionId);
    List<NewsDataEntity> findBySectionName(String sectionName);
    List<NewsDataEntity> findByRegionRegionName(String regionName);
    List<NewsDataEntity> findBySectionNameAndRegionRegionName(String sectionName, String regionName);
    Optional<NewsDataEntity> findByTitle(String title);

 */

    @Query("SELECT MAX(date) FROM NewsDataEntity")
    Optional<String> findLastDate();
}
