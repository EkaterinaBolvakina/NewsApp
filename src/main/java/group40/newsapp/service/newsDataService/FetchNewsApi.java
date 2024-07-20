package group40.newsapp.service.newsDataService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import group40.newsapp.DTO.newsDTO.newsJsonModel.FetchResponseData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class FetchNewsApi {
    private static final Logger logger = LoggerFactory.getLogger(FetchNewsApi.class);
    private final RestTemplate restTemplate;

    public List<FetchResponseData> fetchDataFromApi() {
        List<FetchResponseData> generalNews = fetchDataFromUrl("https://www.tagesschau.de/api2u/news", "general");
        List<FetchResponseData> sportNews = fetchDataFromUrl("https://www.tagesschau.de/api2u/news?ressort=sport", "sport");
        List<FetchResponseData> wirtschaftNews = fetchDataFromUrl("https://www.tagesschau.de/api2u/news?ressort=wirtschaft", "wirtschaft");
        List<FetchResponseData> wissenNews = fetchDataFromUrl("https://www.tagesschau.de/api2u/news?ressort=wissen", "wissen");

        List<FetchResponseData> allNews = new ArrayList<>();
        allNews.addAll(generalNews);
        allNews.addAll(sportNews);
        allNews.addAll(wirtschaftNews);
        allNews.addAll(wissenNews);
        return allNews;
    }

    private List<FetchResponseData> fetchDataFromUrl(String url, String apiType) {
        String json1Response = restTemplate.getForObject(url, String.class);
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
                JsonNode teaserImage = item.path("teaserImage");
                JsonNode imageVariants = teaserImage.path("imageVariants");
                String imageSquareUrl = imageVariants.path("1x1-840").asText();
                String imageWideUrl = imageVariants.path("16x9-960").asText();
                String detailsUrl = item.path("details").asText();

                // Check if detailsUrl is valid
                if (detailsUrl == null || detailsUrl.isEmpty()) {
                    logger.warn("Details URL is missing or empty for item with title: {}", item.path("title").asText());
                    continue;
                }

                // Fetch and check content
                String content = fetchContentFromDetailsUrl(detailsUrl);

                if ("story".equals(item.path("type").asText())
                        && !isLiveblog
                        && !(sectionName.equals("investigativ"))
                        && !teaserImage.isMissingNode()
                        && imageVariants.has("1x1-840")
                        && !imageSquareUrl.isEmpty()
                        && imageVariants.has("16x9-960")
                        && !imageWideUrl.isEmpty()
                        && content != null && !content.isEmpty()
                        && (!(apiType.equals("general") && regionId == 0 && sectionName.isEmpty()))
                ) {
                    FetchResponseData newsData = new FetchResponseData();

                    // RegionId
                    logger.info("RegionId: {}", regionId);
                    newsData.setRegionId(regionId);

                    // Section
                    if (regionId > 0) {
                        logger.info("SectionName: inland");
                        newsData.setSectionName("inland");
                    } else {
                        if (apiType.equals("sport") && sectionName.isEmpty()) {
                            sectionName = "sport";
                        } else if (apiType.equals("wirtschaft") && sectionName.isEmpty()) {
                            sectionName = "wirtschaft";
                        } else if (apiType.equals("wissen") && sectionName.isEmpty()) {
                            sectionName = "wissen";
                        }
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
                    logger.info("ImageSquareUrl: {}", imageSquareUrl);
                    newsData.setTitleImageSquare(imageSquareUrl);

                    logger.info("ImageWideUrl: {}", imageWideUrl);
                    newsData.setTitleImageWide(imageWideUrl);

                    // Set Content
                    logger.info("DetailsUrl: {}", detailsUrl);
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
                    String value = contentItem.path("value").asText();
                    // Perform replacements
                    value = value.replaceAll("/api2u", "");
                    value = value.replaceAll("\\.json", ".html");
                    value = value.replaceAll("type=\"intern\"", "type=\"extern\"");

                    contentBuilder.append("<div className=\"textValueNews\">")
                            .append(value)
                            .append("</div>")
                            .append(" ");
                }
                if (contentItem.has("quotation")) {
                    String quotationText = contentItem.path("quotation").path("text").asText();
                    // Perform replacements
                    quotationText = quotationText.replaceAll("/api2u", "");
                    quotationText = quotationText.replaceAll("\\.json", ".html");
                    quotationText = quotationText.replaceAll("type=\"intern\"", "type=\"extern\"");

                    contentBuilder.append("<div className=\"quotationNews\">")
                            .append(quotationText)
                            .append("</div>")
                            .append(" ");
                }
            }
            content = contentBuilder.toString().trim();
        } catch (IOException e) {
            logger.error("Error fetching or processing details URL JSON: {}", e.getMessage());
        }
        return content;
    }
}
