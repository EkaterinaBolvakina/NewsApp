package group40.newsapp.dto.newsJsonModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchResponseData {
    private Integer regionId;
    private String regionName;
    private String sectionName;
    private String title;
    private String date;
    private String titleImageSquare;
    private String titleImageWide;
    // @Lob
    private String content;
}
