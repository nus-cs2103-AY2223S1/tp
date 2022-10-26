package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.PriceFormatter.formatPrice;

import org.junit.jupiter.api.Test;

public class PriceFormatterTest {

    @Test
    public void formatPrice_positiveWholeNumber_correctRepresentation() {
        double amount = 533;

        assertEquals(formatPrice(amount), "$533");
    }

    @Test
    public void formatPrice_positiveDecimalNumber_correctRepresentation() {
        double amount = 323.11;

        assertEquals(formatPrice(amount), "$323.11");
    }

    @Test
    public void formatPrice_largePositiveWholeNumber_correctRepresentation() {
        double amount = 53355454;

        assertEquals(formatPrice(amount), "$53,355,454");
    }

    @Test
    public void formatPrice_largePositiveDecimalNumber_correctRepresentation() {
        double amount = 2312233.5;

        assertEquals(formatPrice(amount), "$2,312,233.50");
    }

    @Test
    public void formatPrice_smallPositiveDecimalNumber_correctRepresentation() {
        double amount = 0.8;

        assertEquals(formatPrice(amount), "$0.80");
    }

    @Test
    public void formatPrice_smallNegativeDecimalNumber_correctRepresentation() {
        double amount = -0.80;

        assertEquals(formatPrice(amount), "-$0.80");
    }

    @Test
    public void formatPrice_negativeWholeNumber_correctRepresentation() {
        double amount = -533;

        assertEquals(formatPrice(amount), "-$533");
    }

    @Test
    public void formatPrice_negativeDecimalNumber_correctRepresentation() {
        double amount = -323.11;

        assertEquals(formatPrice(amount), "-$323.11");
    }

    @Test
    public void formatPrice_largeNegativeWholeNumber_correctRepresentation() {
        double amount = -53355454;

        assertEquals(formatPrice(amount), "-$53,355,454");
    }

    @Test
    public void formatPrice_largeNegativeDecimalNumber_correctRepresentation() {
        double amount = -2312233.5877;

        assertEquals(formatPrice(amount), "-$2,312,233.59");
    }

}
