package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemValidatorUtilTest.assertValidateFailure;

import org.junit.jupiter.api.Test;

import seedu.foodrem.testutil.MessageToUser;

/**
 * A class to test the ItemUnitValidator.
 */
public class ItemUnitValidatorTest {
    @Test
    public void test_nameIsValidLength() {
        ItemUnitValidator.validate("a");
        ItemUnitValidator.validate("aaaaaaaaaa"); // 10 char

        assertValidateFailure(() -> ItemUnitValidator.validate("aaaaaaaaaaa"),
                MessageToUser.MESSAGE_FOR_UNIT_TOO_LONG); // 11 char
    }

    @Test
    public void test_nameHasValidSymbols() {
        // Invalid names - Spaces
        assertValidateFailure(() -> ItemUnitValidator.validate("    a"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);

        // Invalid names - Wrong Symbols
        assertValidateFailure(() -> ItemUnitValidator.validate("\\"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("\\/"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("|"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("á"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("你"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("あ"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("kg|m"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("kg/m"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT);
        assertValidateFailure(() -> ItemUnitValidator.validate("，"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_UNIT); // Chinese comma

        // Valid names
        ItemUnitValidator.validate("a");
        ItemUnitValidator.validate("kilogram");
    }
}
