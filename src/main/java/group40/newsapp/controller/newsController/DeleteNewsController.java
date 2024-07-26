package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.service.newsDataService.DeleteNewsDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class DeleteNewsController {
    private final DeleteNewsDataService deleteNewsDataService;

    @DeleteMapping("/delete/{newsId}")
    public StandardResponseDto deleteNewsById(@PathVariable Long newsId) {
        return deleteNewsDataService.deleteNewsDataById(newsId);
    }
}
