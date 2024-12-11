package project.cheerspal.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Kiki
 */

@Service
public class PicService {
    private static final String API_URL = "https://api.unsplash.com/photos/random";
    private static final String API_KEY = "_5DAvLdkO1bqeSYbN37gVTv8b1kobBcaqnZ6a-rACEM";

    public String getRandomImageUrl(String query) {
        RestTemplate restTemplate = new RestTemplate();        
        String url = UriComponentsBuilder.fromHttpUrl(API_URL)
                .queryParam("query", query)
                .queryParam("client_id", API_KEY)
                .queryParam("count", 1)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        String imageUrl = response.split("\"regular\":\"")[1].split("\"")[0];
        
        return imageUrl;
    }
}
