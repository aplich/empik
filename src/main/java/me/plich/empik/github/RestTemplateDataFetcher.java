package me.plich.empik.github;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
final class RestTemplateDataFetcher implements HttpDataFetcher {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public <T> T makeGetRequest(String url, Class<T> responseType) {
        return restTemplate.getForObject(url, responseType);
    }
}
