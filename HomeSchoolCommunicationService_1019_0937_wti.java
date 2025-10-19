// 代码生成时间: 2025-10-19 09:37:28
package com.homeschool.communication;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

/**
 * Service for handling home-school communication.
 */
@Service
public class HomeSchoolCommunicationService {

    @Autowired
    private MessageRepository messageRepository; // Repository for storing messages

    // Send a message from a teacher to a student or parent
    public ResponseEntity<?> sendMessage(String senderId, String receiverId, String messageContent) {
        try {
            Message message = new Message(senderId, receiverId, messageContent);
            messageRepository.save(message);
            return ResponseEntity.ok("Message sent successfully");
        } catch (Exception e) {
            // Log the error and return a failure response
            // Log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to send message");
        }
    }

    // Get messages for a specific user
    public ResponseEntity<List<Message>> getMessagesForUser(String userId) {
        try {
            return ResponseEntity.ok(messageRepository.findByReceiverIdOrSenderId(userId));
        } catch (Exception e) {
            // Log the error and return a failure response
            // Log.error(e.getMessage(), e);
            return ResponseEntity.internalServerError().body("Failed to retrieve messages");
        }
    }
}

/*
 * Message.java
 *
 * Represents a message in the home-school communication system.
 */
package com.homeschool.communication;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String senderId;
    private String receiverId;
    private String content;

    public Message() {
        // Default constructor for JPA
    }

    public Message(String senderId, String receiverId, String content) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }

    // Getters and setters omitted for brevity
}

/*
 * MessageRepository.java
 *
 * Repository interface for accessing messages in the database.
 */
package com.homeschool.communication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for accessing messages in the database.
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByReceiverIdOrSenderId(String userId);
}