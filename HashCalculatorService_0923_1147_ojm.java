// 代码生成时间: 2025-09-23 11:47:57
import org.springframework.stereotype.Service;
# 扩展功能模块
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@Service
public class HashCalculatorService {

    // Computes the hash of the given input string using the specified algorithm
    public String computeHash(String input, String algorithm) throws NoSuchAlgorithmException {
        // Check if the input is null or empty
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }
# 改进用户体验

        // Check if the algorithm is null or empty
        if (algorithm == null || algorithm.isEmpty()) {
            throw new IllegalArgumentException("Hash algorithm cannot be null or empty");
        }
# 增强安全性

        // Get an instance of the specified hashing algorithm
        MessageDigest md = MessageDigest.getInstance(algorithm);
# 添加错误处理
        
        // Update the digest using the specified input
        md.update(input.getBytes(StandardCharsets.UTF_8));

        // Compute the hash and return it as a hexadecimal string
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Example method to compute SHA-256 hash of a string
# NOTE: 重要实现细节
    public String computeSHA256Hash(String input) {
        try {
            return computeHash(input, "SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to compute SHA-256 hash", e);
        }
    }
}
