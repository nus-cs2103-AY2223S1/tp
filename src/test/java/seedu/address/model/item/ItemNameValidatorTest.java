package seedu.address.model.item;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.item.itemvalidator.ItemNameValidator;

public class ItemNameValidatorTest {
    // TODO: Test for uniqueness of Item Name

    @Test
    public void test_nameIsValidLength() {
        assertTrue(ItemNameValidator.isNameBlank(""));
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
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters(""));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters(" "));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("       "));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("    a"));

        // Invalid names - Wrong Symbols
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("\\"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("\\/"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("|"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("á"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("你"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("あ"));
        assertFalse(ItemNameValidator.doesNameContainInvalidCharacters("，")); // Chinese comma


        // Valid names
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("a"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("peter jack"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("12345"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("peter the jack 2nd"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("CAPITAL LETTERS"));
        assertTrue(ItemNameValidator.doesNameContainInvalidCharacters("Peter Jack the Second 2nd"));
    }
}
