// 代码生成时间: 2025-10-21 14:58:26
import org.springframework.boot.SpringApplication;
# NOTE: 重要实现细节
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@SpringBootApplication
@RestController
public class NumericalIntegrationCalculator {

    public static void main(String[] args) {
# 优化算法效率
        SpringApplication.run(NumericalIntegrationCalculator.class, args);
    }

    @GetMapping("/integrate")
    public String integrate(@RequestParam double a, @RequestParam double b, @RequestParam double n, @RequestParam String functionName) {
        try {
            Function<Double, Double> function = getFunction(functionName);
            if (function == null) {
                return "Error: Unknown function name";
# TODO: 优化性能
            }
            double result = trapezoidalRule(a, b, n, function);
            return String.format("The result of numerical integration is: %.4f", result);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // Helper method to get a Function<Double, Double> based on input function name
    private Function<Double, Double> getFunction(String functionName) {
        switch (functionName) {
# 增强安全性
            case "x^2":
                return x -> Math.pow(x, 2);
            case "sin(x)":
                return Math::sin;
            default:
                return null;
# 添加错误处理
        }
    }
# FIXME: 处理边界情况

    // Trapezoidal Rule implementation for numerical integration
# 增强安全性
    private double trapezoidalRule(double a, double b, double n, Function<Double, Double> function) {
        double h = (b - a) / n;
        double sum = 0.5 * (function.apply(a) + function.apply(b));
        for (int i = 1; i < n; i++) {
            sum += function.apply(a + i * h);
        }
# FIXME: 处理边界情况
        sum *= h;
        return sum;
    }
}
