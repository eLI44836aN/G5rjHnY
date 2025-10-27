// 代码生成时间: 2025-10-27 09:59:49
package com.example.imageresizer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Dimension;

@Service
public class ImageResizerService {

    private static final String TEMP_DIRECTORY = "temp/";

    /**
     * Resizes a list of images to a specified dimension.
     * 
     * @param images The list of images to resize.
     * @param targetSize The target size for the images.
     * @return A list of resized image file paths.
     * @throws IOException If an I/O error occurs during the resizing process.
     */
    public String resizeImages(List<MultipartFile> images, Dimension targetSize) throws IOException {
        List<String> resizedImagePaths = new ArrayList<>();
        Path tempDir = Paths.get(TEMP_DIRECTORY);
        Files.createDirectories(tempDir); // Ensure the temp directory exists.

        for (MultipartFile image : images) {
            BufferedImage originalImage = ImageIO.read(image.getInputStream());
            BufferedImage resizedImage = resizeImage(originalImage, targetSize);
            String resizedImagePath = saveResizedImage(resizedImage, tempDir);
            resizedImagePaths.add(resizedImagePath);
        }

        return String.join(",", resizedImagePaths); // Return a comma-separated list of paths.
    }

    /**
     * Resizes an image to a specified dimension.
     * 
     * @param originalImage The original image to resize.
     * @param targetSize The target size for the image.
     * @return The resized image.
     */
    private BufferedImage resizeImage(BufferedImage originalImage, Dimension targetSize) {
        int type = originalImage.getType();
        BufferedImage resizedImage = new BufferedImage(targetSize.width, targetSize.height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, targetSize.width, targetSize.height, null);
        g.dispose();
        return resizedImage;
    }

    /**
     * Saves a resized image to the temporary directory and returns its path.
     * 
     * @param resizedImage The resized image to save.
     * @param tempDir The temporary directory path.
     * @return The path to the saved image file.
     * @throws IOException If an I/O error occurs during the saving process.
     */
    private String saveResizedImage(BufferedImage resizedImage, Path tempDir) throws IOException {
        Path filePath = Files.createTempFile(tempDir, "resized-", ".png");
        ImageIO.write(resizedImage, "png", filePath.toFile());
        return filePath.toString();
    }
}
