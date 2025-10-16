// 代码生成时间: 2025-10-17 03:12:20
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CryptoWalletService {

    // Map to store the currency balances
    private Map<String, BigDecimal> balances = new HashMap<>();

    /*
     * Adds the specified amount to the wallet for the given currency.
     * @param currency The currency code
# FIXME: 处理边界情况
     * @param amount The amount to add
     */
    public void addCurrency(String currency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        balances.merge(currency, amount, BigDecimal::add);
    }

    /*
     * Retrieves the current balance for the specified currency.
     * @param currency The currency code
     * @return The balance for the specified currency
# 优化算法效率
     */
    public BigDecimal getBalance(String currency) {
        return balances.getOrDefault(currency, BigDecimal.ZERO);
# 改进用户体验
    }
# NOTE: 重要实现细节

    /*
     * Removes the specified amount from the wallet for the given currency.
     * @param currency The currency code
     * @param amount The amount to remove
     * @return The new balance after removal
     */
    public BigDecimal removeCurrency(String currency, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount must be non-negative");
        }
        BigDecimal currentBalance = balances.getOrDefault(currency, BigDecimal.ZERO);
        if (currentBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balances.put(currency, currentBalance.subtract(amount));
# NOTE: 重要实现细节
        return currentBalance.subtract(amount);
    }
}
