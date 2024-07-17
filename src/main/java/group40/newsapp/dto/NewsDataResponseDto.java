package group40.newsapp.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDataResponseDto {
    private Integer id;
    private Integer regionId;
    private String regionName; // BundesLänder, Ausland, Inland, non-region
    //private int sectionId;     // 0,            1,       0,      2,     3
    private String sectionName;// BundesLänder,   Ausland, Inland, Sport, Wissenschaft
    private String title;
    private String date;
    private String titleImageSquare;
    private String titleImageWide;
   // @Lob
    private String content;
}
