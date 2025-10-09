// 代码生成时间: 2025-10-10 02:05:22
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URL;
import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.net.HttpURLConnection;
import java.io.IOException;

/**
 * Service to validate the validity of a URL.
 */
@Service
public class UrlValidatorService {

    private RestTemplate restTemplate;

    /**
     * Constructor.
     */
    public UrlValidatorService() {
        // Initialize the RestTemplate.
        restTemplate = new RestTemplate();
    }

    /**
     * Validates the URL using the following steps:
     * 1. Check if the URL is a valid format.
     * 2. Check if the URL is reachable and returns a 200 OK status code.
     *
     * @param url The URL to validate.
     * @return true if the URL is valid, false otherwise.
     */
    public boolean validateUrl(String url) {
        try {
            // Step 1: Check if the URL is a valid format.
            new URL(url);

            // Step 2: Check if the URL is reachable.
            return isUrlReachable(url);
        } catch (MalformedURLException e) {
            // Log the exception (not shown here).
            // The URL is not in a valid format.
            return false;
        } catch (UnknownHostException e) {
            // Log the exception (not shown here).
            // The URL is not reachable.
            return false;
        } catch (IOException e) {
            // Log the exception (not shown here).
            // An I/O error occurred.
            return false;
        }
    }

    /**
     * Checks if the URL is reachable and returns a 200 OK status code.
     *
     * @param url The URL to check.
     * @return true if the URL is reachable, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    private boolean isUrlReachable(String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("HEAD");
        connection.connect();

        int responseCode = connection.getResponseCode();
        return HttpURLConnection.HTTP_OK == responseCode;
    }

    @PostConstruct
    public void init() {
        // Any initialization code, if necessary.
    }
}
