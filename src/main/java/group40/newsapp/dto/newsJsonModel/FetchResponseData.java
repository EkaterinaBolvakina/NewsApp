package group40.newsapp.dto.newsJsonModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchResponseData {
    private Integer regionId;
    private String sectionName;
    private String title;
    private String date;
    private String titleImage;
    // @Lob
    private String content;
}
