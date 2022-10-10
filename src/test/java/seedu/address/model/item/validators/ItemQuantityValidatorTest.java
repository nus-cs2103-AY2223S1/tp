package seedu.address.model.item.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemQuantityValidatorTest {
    @Test
    public void test_quantityInValidRange() {
        Assertions.assertFalse(ItemQuantityValidator.isQuantityMoreThanMaxQuantity(0));
        assertFalse(ItemQuantityValidator.isQuantityMoreThanMaxQuantity(100000));
        assertTrue(ItemQuantityValidator.isQuantityMoreThanMaxQuantity(100001));

        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("0"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("10"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("100"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("0.0"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("0.00"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("0.000"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("0.0000"));
        assertTrue(ItemQuantityValidator.isQuantityTooPrecise("0.00000"));

        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("1"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("1.1"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("1.01"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("1.001"));
        assertFalse(ItemQuantityValidator.isQuantityTooPrecise("1.0001"));
        assertTrue(ItemQuantityValidator.isQuantityTooPrecise("1.00001"));

        assertTrue(ItemQuantityValidator.isQuantityNegative(-1));
        assertTrue(ItemQuantityValidator.isQuantityNegative(-1.01));
        assertFalse(ItemQuantityValidator.isQuantityNegative(0));
        assertFalse(ItemQuantityValidator.isQuantityNegative(100000));
    }

    @Test
    public void test_isParsableQuantity() {
        assertTrue(ItemQuantityValidator.isParsableQuantity("0"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("100000"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("-1"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("100001"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.1"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.01"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.001"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.0001"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.00001"));
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.000000000000001")); // 15dp
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.0000000000000001")); // 16dp
        // Parsable, but causes integer overflow
        assertTrue(ItemQuantityValidator.isParsableQuantity("0.99999999999999999")); // 17dp

        assertFalse(ItemQuantityValidator.isParsableQuantity(""));
        assertFalse(ItemQuantityValidator.isParsableQuantity(" "));
        assertFalse(ItemQuantityValidator.isParsableQuantity("a"));
        assertFalse(ItemQuantityValidator.isParsableQuantity("apple"));
        assertFalse(ItemQuantityValidator.isParsableQuantity("1+1"));
        assertFalse(ItemQuantityValidator.isParsableQuantity("1 + 1"));
        assertFalse(ItemQuantityValidator.isParsableQuantity("π"));
        assertFalse(ItemQuantityValidator.isParsableQuantity("1,234"));
        assertFalse(ItemQuantityValidator.isParsableQuantity("1/2"));
    }
}
