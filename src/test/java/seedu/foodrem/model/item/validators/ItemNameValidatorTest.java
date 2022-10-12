package seedu.address.model.item.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ItemNameValidatorTest {
    // TODO: Test for uniqueness of Item Name

    @Test
    public void test_nameIsValidLength() {
        Assertions.assertTrue(ItemNameValidator.isNameBlank(""));
        assertFalse(ItemNameValidator.isNameBlank("a"));
        assertFalse(ItemNameValidator.isNameLengthMoreThanMaxLength("Lorem ipsum dolor sit amet, "
                + "consectetuer adipiscing elit. "
                + "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis "
                + "parturient montes, "
                + "nascetur ridiculus mus. Donec qu"));
        assertTrue(ItemNameValidator.isNameLengthMoreThanMaxLength("Lorem ipsum dolor sit amet, consectetuer "
                + "adipiscing elit. "
                + "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus "
                + "et magnis dis parturient montes, "
                + "nascetur ridiculus mus. Donec que"));
    }

    @Test
    public void test_nameHasValidSymbols() {
        // Invalid names - Spaces
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters(""));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters(" "));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("       "));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("    a"));

        // Invalid names - Wrong Symbols
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("\\"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("\\/"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("|"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("á"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("你"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("あ"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("，")); // Chinese comma


        // Valid names
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("a"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("peter jack"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("12345"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("peter the jack 2nd"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("CAPITAL LETTERS"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("Peter Jack the Second 2nd"));
    }
}
