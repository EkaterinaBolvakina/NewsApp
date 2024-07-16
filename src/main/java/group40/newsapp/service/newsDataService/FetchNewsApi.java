package group40.newsapp.service.newsDataService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import group40.newsapp.dto.newsJsonModel.FetchResponseData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Service
@AllArgsConstructor
public class FetchNewsApi {
    private static final Logger logger = LoggerFactory.getLogger(FetchNewsApi.class);
    private final RestTemplate restTemplate;

    public List<FetchResponseData> fetchDataFromApi() {
        String url = "https://www.tagesschau.de/api2u/news";
       // logger.info("Fetching data from URL: {}", url);
        String json1Response = restTemplate.getForObject(url, String.class);
     //   logger.info("Received JSON response: {}", json1Response);
        ObjectMapper mapper = new ObjectMapper();
        List<FetchResponseData> savedNews = new ArrayList<>();

        try {
            JsonNode jsonResponse = mapper.readTree(json1Response);
            JsonNode newsArray = jsonResponse.path("news");
            for (JsonNode item : newsArray) {

                boolean isLiveblog = false;
                JsonNode trackingArray = item.path("tracking");
                for (JsonNode trackingItem : trackingArray) {
                    if ("LIVEBLOG".equals(trackingItem.path("ctp").asText())) {
                        isLiveblog = true;
                        break;
                    }
                }
                Integer regionId = item.path("regionId").asInt();
                String sectionName = item.path("ressort").asText();

                if ("story".equals(item.path("type").asText()) && !isLiveblog
                        && !(regionId == 0 && sectionName.isEmpty())) {
                    FetchResponseData newsData = new FetchResponseData();

                    // RegionId
                    logger.info("RegionId: {}", regionId);
                    newsData.setRegionId(regionId);

                    // Section
                    if (regionId > 0) {
                        logger.info("SectionName: inland");
                        newsData.setSectionName("inland");
                    } else {
                        logger.info("SectionName: {}", sectionName);
                        newsData.setSectionName(sectionName);
                    }

                    // Title
                    String title = item.path("title").asText();
                    logger.info("Title: {}", title);
                    newsData.setTitle(title);

                    // Date
                    String date = item.path("date").asText();
                    logger.info("Date: {}", date);
                    newsData.setDate(date);

                    // Teaser Image
                    JsonNode teaserImage = item.path("teaserImage");
                    if (!teaserImage.isMissingNode()) {
                        JsonNode imageVariants = teaserImage.path("imageVariants");
                        if (!imageVariants.isMissingNode() && imageVariants.has("1x1-840")) {
                            String imageUrl = imageVariants.path("1x1-840").asText();
                            logger.info("ImageUrl: {}", imageUrl);
                            newsData.setTitleImage(imageUrl);
                        }
                    }

                    // Details URL
                    String detailsUrl = item.path("details").asText();
                    logger.info("DetailsUrl: {}", detailsUrl);
                    String content = fetchContentFromDetailsUrl(detailsUrl);
                    newsData.setContent(content);

                    // Save news
                    savedNews.add(newsData);
                }
            }
        } catch (IOException e) {
            logger.error("Error processing JSON response: {}", e.getMessage());
        }

        return savedNews;
    }

    private String fetchContentFromDetailsUrl(String detailsUrl) {
        String content = "";
        try {
            String json2Response = restTemplate.getForObject(detailsUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonResponse = mapper.readTree(json2Response);
            JsonNode contentArray = jsonResponse.path("content");
            StringBuilder contentBuilder = new StringBuilder();

            for (JsonNode contentItem : contentArray) {
                if (contentItem.has("value")) {
                    contentBuilder.append(contentItem.path("value").asText()).append(" ");
                }
            }
            content = contentBuilder.toString().trim();
        } catch (IOException e) {
            logger.error("Error fetching or processing details URL JSON: {}", e.getMessage());
        }
        return content;
    }
}

