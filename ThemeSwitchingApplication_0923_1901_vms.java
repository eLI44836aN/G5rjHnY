// 代码生成时间: 2025-09-23 19:01:22
package com.example.themeswitching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ThemeSwitchingApplication {

    @RestController
    class ThemeController {
        private final Map<String, String> themes = new HashMap<>();
        private String currentTheme = "default"; // Default theme
# 改进用户体验

        @PostConstruct
        private void init() {
            // Initialize themes
            themes.put("dark", "Dark Theme");
            themes.put("light", "Light Theme");
        }

        @GetMapping("/switchTheme")
        public String switchTheme(@RequestParam String theme) {
            try {
                if (!themes.containsKey(theme)) {
                    throw new IllegalArgumentException("Invalid theme: " + theme);
                }
                currentTheme = theme;
                return "Theme switched to: " + themes.get(theme);
            } catch (IllegalArgumentException e) {
                return "Error: " + e.getMessage();
            }
        }
# 添加错误处理
    }

    public static void main(String[] args) {
        SpringApplication.run(ThemeSwitchingApplication.class, args);
    }
}
