package group40.newsapp.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import group40.newsapp.domain.NewsDataEntity;
import group40.newsapp.service.newsDataService.NewsDataFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsDataFindController {
    @Autowired
    private NewsDataFindService newsDataFindService;
    @GetMapping
    public List<NewsDataEntity> getAllNews() {
        return newsDataFindService.getAllNews();
    }

/*
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/fetch")
    public List<NewsDataEntity> fetchAndSaveNews() throws IOException {
        String url = "https://www.tagesschau.de/api2u/news";
        String json1Response = restTemplate.getForObject(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json1 = mapper.readTree(json1Response);

       // List<News> savedNews = new ArrayList<>();

        for (JsonNode item : json1) {
            if ("story".equals(item.path("type").asText())) {
                NewsDataEntity newsDataEntity = new NewsDataEntity();

                // RegionId
                int regionId = item.path("regionId").asInt();
                newsDataEntity.setRegionId(regionId);

                // Section
                String section = item.path("ressort").asText();
                newsDataEntity.setSectionName(section);

                // Title
                String title = item.path("title").asText();
                newsDataEntity.setTitle(title);

                // Date
                String date = item.path("date").asText();
                newsDataEntity.setDate(date);

                // Teaser Image
                JsonNode teaserImage = item.path("teaserImage");
                if (!teaserImage.isMissingNode()) {
                    JsonNode imageVariants = teaserImage.path("imageVariants");
                    if (!imageVariants.isMissingNode() && imageVariants.has("1x1-840")) {
                        String imageUrl = imageVariants.path("1x1-840").asText();
                        newsDataEntity.setTitleImage(imageUrl);
                    }
                }

                // Details URL
                String detailsUrl = item.path("details").asText();
                newsDataEntity.setContent(detailsUrl);

                // Save news
              //  newsDataFindService.saveNews(newsDataEntity);
/*
                // URL für json2 extrahieren
                String json2Url = item.get("details").asText();  // Annahme: Die URL für json2 ist im Feld "details"

                String json2Response = restTemplate.getForObject(json2Url, String.class);
                JsonNode json2 = mapper.readTree(json2Response);

                List<String> detailsList = new ArrayList<>();
                for (JsonNode contentNode : json2.get("content")) {
                    if (contentNode.has("value")) {
                        detailsList.add(contentNode.get("value").asText());
                    } else if (contentNode.has("type")) {
                        detailsList.add(contentNode.get("type").asText());
                    }
                }

                if (!detailsList.isEmpty()) {
                    news.setContent(mapper.writeValueAsString(detailsList));


                    savedNews.add(newsService.saveNews(news));
                }


            }
        }
        // Debug-Ausgaben
        //System.out.println("Saved News: " + savedNews.size());
        return newsDataFindService.getAllNews();
    }
*/

}