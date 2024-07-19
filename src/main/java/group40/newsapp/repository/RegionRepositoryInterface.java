package group40.newsapp.repository;

import group40.newsapp.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegionRepositoryInterface extends JpaRepository<Region, Long> {
   Optional<Region> findByRegionName(String name);
   Optional<Region> findById(Long id);
   Optional<Region> findByRegionNewsId(Integer regionNewsId);
}
