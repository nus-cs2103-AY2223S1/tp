package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

/**
 * Utility class containing methods to handle stock levels in the inventory.
 */
public class StockUtil {
    public static final String COLOR_HIGH = "#2ecc71";
    public static final String COLOR_MEDIUM = "#f39c12";
    public static final String COLOR_LOW = "#e74c3c";
    public static final String COLOR_DEFAULT = "transparent";
    public static final double MEDIUM_THRESHOLD = 1.2;
    public static final double HIGH_THRESHOLD = 1.65;
    /**
     * Determines the color based on {@code currentStock} and {@code minStock}.
     */
    public static String determineStockHealthColor(int currentStock, int minStock) {
        requireNonNull(currentStock);
        requireNonNull(minStock);

        double mediumStockThreshold = minStock * HIGH_THRESHOLD;
        double lowStockThreshold = minStock * MEDIUM_THRESHOLD;
        if (currentStock < lowStockThreshold) {
            return translateStockLevelToColor(StockLevel.LOW);
        } else if (currentStock < mediumStockThreshold) {
            assert currentStock >= lowStockThreshold;
            return translateStockLevelToColor(StockLevel.MEDIUM);
        } else {
            assert currentStock >= mediumStockThreshold;
            return translateStockLevelToColor(StockLevel.HIGH);
        }
    }

    /**
     * Determines the stock health and returns a enum of {@code StockLevel}.
     */
    public static StockLevel determineStockHealth(int currentStock, int minStock) {
        requireNonNull(currentStock);
        requireNonNull(minStock);

        double mediumStockThreshold = minStock * HIGH_THRESHOLD;
        double lowStockThreshold = minStock * MEDIUM_THRESHOLD;

        if (currentStock < lowStockThreshold) {
            return StockLevel.LOW;
        } else if (currentStock < mediumStockThreshold) {
            assert currentStock >= lowStockThreshold;
            return StockLevel.MEDIUM;
        } else {
            assert currentStock >= mediumStockThreshold;
            return StockLevel.HIGH;
        }
    }

    /**
     * Translates stock health to border colors.
     */
    public static String translateStockLevelToColor(StockLevel level) {
        requireNonNull(level);

        switch (level) {
        case LOW:
            return COLOR_LOW;
        case MEDIUM:
            return COLOR_MEDIUM;
        case HIGH:
            return COLOR_HIGH;
        default:
            return COLOR_DEFAULT;
        }
    }

    /**
     * Enumeration class to represent three levels of {@code LOW}, {@code MEDIUM}, {@code HIGH}.
     */
    public enum StockLevel {
        LOW,
        MEDIUM,
        HIGH
    }
}
