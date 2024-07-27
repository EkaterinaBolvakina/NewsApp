package group40.newsapp.DTO.region;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response: Region")
public class RegionDTO {

    @Schema(description = "Region name", example = "Hessen")
    private String regionName;
}
