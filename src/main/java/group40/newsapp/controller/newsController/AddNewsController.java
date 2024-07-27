package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.controller.api.news.AddNewsApi;
import group40.newsapp.service.newsDataService.AddNewsDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AddNewsController implements AddNewsApi {
    private final AddNewsDataService addNewsDataService;

    @Override
    @PostMapping("/news")
    public StandardResponseDto loadAllNewsFromAPIsToDataBase(){
        return addNewsDataService.saveNewsFromFetchApi();
    }
}
