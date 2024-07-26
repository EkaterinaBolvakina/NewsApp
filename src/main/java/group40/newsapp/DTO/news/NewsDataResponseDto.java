package group40.newsapp.DTO.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDataResponseDto {
    private Long id;
    private Long regionId;
    private String regionName;
    private String sectionName;
    private String title;
    private String date;
    private String titleImageSquare;
    private String titleImageWide;
    private String content;
    private int likeCount;
    private int dislikeCount;
    private int commentsCount;
}
