package group40.newsapp.repository.region;

import group40.newsapp.models.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
   Optional<Region> findByRegionName(String name);
   Optional<Region> findById(Long id);
   List<Region> findAll();

}
