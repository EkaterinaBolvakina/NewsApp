package group40.newsapp.service.newsSection;

import group40.newsapp.DTO.news.NewsSectionDTO;
import group40.newsapp.exception.RestException;
import group40.newsapp.models.news.NewsSection;
import group40.newsapp.repository.news.NewsSectionRepository;
import group40.newsapp.service.util.newsSectionMapping.NewsSectionConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FindNewsSectionService {

    private final NewsSectionRepository newsSectionRepository;
    private final NewsSectionConverter newsSectionConverter;

    public ResponseEntity<List<NewsSectionDTO>> findAllSections() {
        List<NewsSection> sections = newsSectionRepository.findAll();
        List<NewsSectionDTO> DTOs = sections.stream().map(newsSectionConverter::toDTO).toList();
        if (!sections.isEmpty()) {
            return new ResponseEntity<>(DTOs, HttpStatus.OK);
        } else {
            throw new RestException(HttpStatus.NOT_FOUND, "No news sections found");
        }
    }
}
