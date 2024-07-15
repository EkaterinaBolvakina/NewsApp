package group40.newsapp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "news")
public class NewsDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer regionId;      // 1-16        , 17,      0     , 18
    //private String regionName; // BundesLänder, Ausland, Inland, non-region
    //private int sectionId;     // 0,            1,       0,      2,     3
    private String sectionName;// BundesLänder,   Ausland, Inland, Sport, Wissenschaft
    private String title;
    private String date;
    private String titleImage;
  //  @Lob
    private String content;

}
