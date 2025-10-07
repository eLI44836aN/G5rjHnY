// 代码生成时间: 2025-10-07 18:16:37
 * and adheres to Java best practices for maintainability and extensibility.
 */

package com.example.mediaplayer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

/**
 * Service class for streaming media player.
 */
@Service
public class StreamingMediaPlayer {

    private final RestTemplate restTemplate;

    /**
     * Constructor to initialize the RestTemplate.
     * @param restTemplate The RestTemplate instance for making HTTP requests.
     */
    public StreamingMediaPlayer(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Plays a media stream from a given URL.
     * @param mediaUrl The URL of the media to be streamed.
     * @return A ResponseEntity containing the media stream.
     */
    public ResponseEntity<byte[]> playMediaStream(String mediaUrl) {
        try {
            // Fetch the media stream from the provided URL
            ResponseEntity<byte[]> response = restTemplate.getForEntity(mediaUrl, byte[].class);

            // Check if the response status is OK
            if (response.getStatusCode().is2xxSuccessful()) {
                return response;
            } else {
                // Handle non-successful status codes
                throw new RuntimeException("Failed to fetch media stream: " + response.getStatusCode());
            }
        } catch (Exception e) {
            // Log and handle any exceptions that occur during the streaming process
            System.err.println("Error playing media stream: " + e.getMessage());
            throw e;
        }
    }
}
