// 代码生成时间: 2025-10-04 18:12:38
package com.example.tokeneconomy;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

/**
 * Service class to handle token economy model operations.
 */
@Service
public class TokenEconomyService {

    private static final BigDecimal INITIAL_SUPPLY = new BigDecimal("1000000"); // initial token supply
    private static final BigDecimal DECIMAL_PLACES = new BigDecimal("100"); // token decimal places

    private BigDecimal totalSupply;
    private BigDecimal circulatingSupply;

    /**
     * Initializes the token economy service with initial supply.
     */
    public TokenEconomyService() {
        this.totalSupply = INITIAL_SUPPLY;
        this.circulatingSupply = INITIAL_SUPPLY;
    }

    /**
     * Mints new tokens and adds them to the circulating supply.
     *
     * @param amount The amount of tokens to mint.
     * @return The new total supply after minting.
     * @throws IllegalArgumentException If the amount is negative.
     */
    public BigDecimal mintTokens(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot mint a negative amount of tokens.");
        }
        this.totalSupply = this.totalSupply.add(amount);
        this.circulatingSupply = this.circulatingSupply.add(amount);
        return this.totalSupply;
    }

    /**
     * Burns tokens from the circulating supply.
     *
     * @param amount The amount of tokens to burn.
     * @return The new total supply after burning.
     * @throws IllegalArgumentException If the amount is negative or exceeds the circulating supply.
     */
    public BigDecimal burnTokens(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot burn a negative amount of tokens.");
        }
        if (amount.compareTo(this.circulatingSupply) > 0) {
            throw new IllegalArgumentException("Cannot burn more tokens than the circulating supply.");
        }
        this.totalSupply = this.totalSupply.subtract(amount);
        this.circulatingSupply = this.circulatingSupply.subtract(amount);
        return this.totalSupply;
    }

    /**
     * Gets the current total supply of tokens.
     *
     * @return The total supply of tokens.
     */
    public BigDecimal getTotalSupply() {
        return this.totalSupply;
    }

    /**
     * Gets the current circulating supply of tokens.
     *
     * @return The circulating supply of tokens.
     */
    public BigDecimal getCirculatingSupply() {
        return this.circulatingSupply;
    }
}
