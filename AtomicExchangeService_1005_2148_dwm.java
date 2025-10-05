// 代码生成时间: 2025-10-05 21:48:28
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AtomicExchangeService {

    private final RestTemplate restTemplate;

    // Constructor injection of RestTemplate
    public AtomicExchangeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Atomic exchange protocol implementation
     *
     * @param sourceUrl The URL of the source service
     * @param targetUrl The URL of the target service
     * @param data Data to be exchanged
     * @return The response from the target service
     */
    public String atomicExchange(String sourceUrl, String targetUrl, String data) {
        try {
            // Attempt to retrieve data from the source service
            String sourceResponse = restTemplate.getForObject(sourceUrl, String.class);
            if (sourceResponse == null || sourceResponse.isEmpty()) {
                throw new RuntimeException("Source service responded with empty data");
            }

            // Attempt to send data to the target service
            return restTemplate.postForObject(targetUrl, data, String.class);
        } catch (Exception e) {
            // Handle any exceptions that occur during the exchange process
            // Log the exception details for further investigation
            // Return an appropriate error message
            return "Error during atomic exchange: " + e.getMessage();
        }
    }
}
