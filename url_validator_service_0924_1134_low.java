// 代码生成时间: 2025-09-24 11:34:19
// URL Validator Service using Spring Cloud

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import java.net.URI;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import java.net.URISyntaxException;
import org.springframework.http.HttpHeaders;
import java.io.IOException;

@Service
public class UrlValidatorService {

    private final RestTemplate restTemplate;

    public UrlValidatorService() {
        // Creating a RestTemplate with a custom request factory to handle redirects
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectionRequestTimeout(5000);
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        this.restTemplate = new RestTemplate(requestFactory);
    }

    /**
     * Validates the URL by making a HEAD request to check its existence and response status.
     * 
     * @param url The URL to validate
     * @return true if the URL is valid and accessible, false otherwise.
     */
    public boolean validateUrl(String url) {
        try {
            URI uri = new URI(url);
            ResponseEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.HEAD,
                null,
                String.class
            );

            // Check if the response status code is 2xx which indicates success
            return response.getStatusCode().is2xxSuccessful();

        } catch (URISyntaxException e) {
            // URL is not properly formatted
            System.err.println("Invalid URL format: " + e.getMessage());
        } catch (IOException e) {
            // Failed to connect or read from the URL
            System.err.println("I/O Error when connecting to URL: " + e.getMessage());
        }
        return false;
    }
}
