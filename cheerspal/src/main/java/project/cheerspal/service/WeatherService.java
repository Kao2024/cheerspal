package project.cheerspal.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kiki
 */
@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    public String getWeather(String city, LocalDate date) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        try {
            String response = restTemplate.getForObject(url, String.class);
            
            JSONObject jsonResponse = new JSONObject(response);
            String weatherDescription = jsonResponse.getJSONArray("weather")
                                                   .getJSONObject(0)
                                                   .getString("description");
            double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
            
            return String.format(" %s, Temperature: %.2f°C", weatherDescription, temperature);
        } catch (Exception e) {
            return "Unable to fetch weather";
        }
    }
}
