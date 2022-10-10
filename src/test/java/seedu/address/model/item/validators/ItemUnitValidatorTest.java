package seedu.address.model.item.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemUnitValidatorTest {
    // TODO: Test for uniqueness of Item Unit

    @Test
    public void test_unitIsValidLength() {
        Assertions.assertTrue(ItemUnitValidator.isUnitBlank(""));
        assertFalse(ItemUnitValidator.isUnitBlank("a"));
        assertFalse(ItemUnitValidator.isUnitLengthMoreThanMaxLength("Lorem ipsum dolor si"));
        assertTrue(ItemUnitValidator.isUnitLengthMoreThanMaxLength("Lorem ipsum dolor sim"));
    }

    @Test
    public void test_unitHasValidSymbols() {
        // Invalid names - Spaces
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("")); // Cannot have nothing
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters(" "));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("       "));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("    a"));

        // Invalid names - Wrong Symbols
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("\\"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("\\/"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("|"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("á"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("你"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("あ"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("，")); // Chinese comma
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("kilogram|container"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("kilogram/container"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("kilogram\\container"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("kilogram$container"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("kilogram*container"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("*"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("$"));
        assertTrue(ItemUnitValidator.doesUnitContainInvalidCharacters("%"));


        // Valid names
        assertFalse(ItemUnitValidator.doesUnitContainInvalidCharacters("a"));
        assertFalse(ItemUnitValidator.doesUnitContainInvalidCharacters("kg"));
        assertFalse(ItemUnitValidator.doesUnitContainInvalidCharacters("kilogram"));
        assertFalse(ItemUnitValidator.doesUnitContainInvalidCharacters("KG"));
        assertFalse(ItemUnitValidator.doesUnitContainInvalidCharacters("100"));
        assertFalse(ItemUnitValidator.doesUnitContainInvalidCharacters("1234567891234567892")); // 20 characters
    }
}
