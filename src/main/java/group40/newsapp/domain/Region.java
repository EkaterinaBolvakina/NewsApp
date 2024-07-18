package group40.newsapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Column(name = "region_news_id", unique = true, nullable = false)
    private Integer regionNewsId;

    private String regionName;

}
