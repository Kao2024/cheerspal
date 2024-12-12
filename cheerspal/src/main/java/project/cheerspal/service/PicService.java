package project.cheerspal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Kiki
 */

@Service
public class PicService {
    @Value("${unsplash.api.url}")
    private String apiUrl;

    @Value("${unsplash.api.key}")
    private String apiKey;

    public String getRandomImageUrl(String query) {
        RestTemplate restTemplate = new RestTemplate();        
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("query", query)
                .queryParam("client_id", apiKey)
                .queryParam("count", 1)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        String imageUrl = response.split("\"regular\":\"")[1].split("\"")[0];
        
        return imageUrl;
    }
}
