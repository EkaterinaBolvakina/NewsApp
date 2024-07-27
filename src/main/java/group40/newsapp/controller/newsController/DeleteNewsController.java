package group40.newsapp.controller.newsController;

import group40.newsapp.DTO.appDTO.StandardDelRequest;
import group40.newsapp.DTO.appDTO.StandardResponseDto;
import group40.newsapp.controller.api.news.DeleteNewsApi;
import group40.newsapp.service.newsDataService.DeleteNewsDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class DeleteNewsController implements DeleteNewsApi {
    private final DeleteNewsDataService deleteNewsDataService;

    @Override
    @DeleteMapping("/news")
    public StandardResponseDto deleteNewsById(@Valid @RequestBody StandardDelRequest dto) {
        return deleteNewsDataService.deleteNewsDataById(dto.getId());
    }
}
