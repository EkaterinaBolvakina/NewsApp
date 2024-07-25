package group40.newsapp.DTO.validationErrorDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorDto {

    private String field;
    private String rejectedValue;
    private String message;
}
