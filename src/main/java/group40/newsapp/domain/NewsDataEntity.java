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

   @ManyToOne
   @JoinColumn(name = "region_news_id", referencedColumnName = "regionNewsId")
   private Region region = new Region();

    private String sectionName;
    private String title;
    private String date;
    private String titleImageSquare;
    private String titleImageWide;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

}
