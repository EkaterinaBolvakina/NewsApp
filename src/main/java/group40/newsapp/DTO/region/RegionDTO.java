package group40.newsapp.DTO.region;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Response: Region")
public class RegionDTO {
    @Schema(description = "Region ID", example = "8")
    @NotNull(message = "Region name must be not null.")
    private Long id;

    @Schema(description = "Region name", example = "Hessen")
    private String regionName;

    public RegionDTO(String regionName) {
        this.regionName = regionName;
    }
}
