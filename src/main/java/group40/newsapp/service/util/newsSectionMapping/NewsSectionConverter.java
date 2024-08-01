package group40.newsapp.service.util.newsSectionMapping;

import group40.newsapp.DTO.news.NewsSectionDTO;
import group40.newsapp.models.news.NewsSection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsSectionConverter {
    public NewsSectionDTO toDTO(NewsSection newsSection) {
        NewsSectionDTO DTO = new NewsSectionDTO();
        DTO.setId(newsSection.getId());
        DTO.setSectionName(newsSection.getSectionName());
        return DTO;
    }
}
