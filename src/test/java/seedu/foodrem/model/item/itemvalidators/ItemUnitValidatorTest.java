package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemUnitValidator.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT;
import static seedu.foodrem.model.item.itemvalidators.ItemUnitValidator.MESSAGE_FOR_UNIT_IS_BLANK;
import static seedu.foodrem.model.item.itemvalidators.ItemUnitValidator.MESSAGE_FOR_UNIT_TOO_LONG;
import static seedu.foodrem.model.item.itemvalidators.ItemValidatorUtilTest.assertValidateFailure;

import org.junit.jupiter.api.Test;

public class ItemUnitValidatorTest {
    // TODO: Test for uniqueness of Item Unit

    @Test
    public void test_nameIsValidLength() {
        ItemUnitValidator.validate("a");
        ItemUnitValidator.validate("aaaaaaaaaa"); // 10 char

        assertValidateFailure(() -> ItemUnitValidator.validate(""), MESSAGE_FOR_UNIT_IS_BLANK); // Blank
        assertValidateFailure(() -> ItemUnitValidator.validate("aaaaaaaaaaa"),
                MESSAGE_FOR_UNIT_TOO_LONG); // 11 char
    }

    @Test
    public void test_nameHasValidSymbols() {
        // Invalid names - Spaces
        assertValidateFailure(() -> ItemUnitValidator.validate(" "), MESSAGE_FOR_UNIT_IS_BLANK);
        assertValidateFailure(() -> ItemUnitValidator.validate(" "), MESSAGE_FOR_UNIT_IS_BLANK);
        assertValidateFailure(() -> ItemUnitValidator.validate("       "), MESSAGE_FOR_UNIT_IS_BLANK);
        assertValidateFailure(() -> ItemUnitValidator.validate("    a"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);

        // Invalid names - Wrong Symbols
        assertValidateFailure(() -> ItemUnitValidator.validate("\\"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("\\/"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("|"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("á"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("你"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("あ"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("kg|m"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("kg/m"), MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("，"),
                MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT); // Chinese comma

        // Valid names
        ItemUnitValidator.validate("a");
        ItemUnitValidator.validate("kilogram");
    }
}
