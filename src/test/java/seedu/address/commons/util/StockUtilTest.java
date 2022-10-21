package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StockUtilTest {
    private static final String COLOR_HIGH = "#2ecc71";
    private static final String COLOR_MEDIUM = "#f39c12";
    private static final String COLOR_LOW = "#e74c3c";

    @Test
    public void stockLevelToColor_validInputs_correctResult() {
        assertEquals(StockUtil.translateStockLevelToColor(StockUtil.StockLevel.LOW), COLOR_LOW);
        assertEquals(StockUtil.translateStockLevelToColor(StockUtil.StockLevel.MEDIUM), COLOR_MEDIUM);
        assertEquals(StockUtil.translateStockLevelToColor(StockUtil.StockLevel.HIGH), COLOR_HIGH);
    }

    @Test
    public void stockHealth_validInputs_correctResult() {
        assertEquals(StockUtil.determineStockHealth(110, 100), StockUtil.StockLevel.LOW);
        assertEquals(StockUtil.determineStockHealth(150, 100), StockUtil.StockLevel.MEDIUM);
        assertEquals(StockUtil.determineStockHealth(180, 100), StockUtil.StockLevel.HIGH);
    }

    @Test
    public void stockHealthColor_validInputs_correctColor() {
        assertEquals(StockUtil.determineStockHealthColor(110, 100), COLOR_LOW);
        assertEquals(StockUtil.determineStockHealthColor(150, 100), COLOR_MEDIUM);
        assertEquals(StockUtil.determineStockHealthColor(180, 100), COLOR_HIGH);
    }
}
