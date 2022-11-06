package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemValidatorUtilTest.assertValidateFailure;

import org.junit.jupiter.api.Test;

import seedu.foodrem.testutil.MessageToUser;

/**
 * A class to test the ItemNameValidator.
 */
public class ItemNameValidatorTest {
    // TODO: Test for uniqueness of Item Name

    @Test
    public void test_nameIsValidLength() {
        ItemNameValidator.validate("a");
        ItemNameValidator.validate("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        // Blank
        assertValidateFailure(() -> ItemNameValidator.validate(""), MessageToUser.MESSAGE_FOR_NAME_IS_BLANK);
        assertValidateFailure(() -> ItemNameValidator.validate(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
                MessageToUser.MESSAGE_FOR_NAME_TOO_LONG); // 201 char
    }

    @Test
    public void test_nameHasValidSymbols() {
        // Invalid names - Spaces
        assertValidateFailure(() -> ItemNameValidator.validate(" "),
                MessageToUser.MESSAGE_FOR_NAME_IS_BLANK);
        assertValidateFailure(() -> ItemNameValidator.validate(" "),
                MessageToUser.MESSAGE_FOR_NAME_IS_BLANK);
        assertValidateFailure(() -> ItemNameValidator.validate("       "),
                MessageToUser.MESSAGE_FOR_NAME_IS_BLANK);
        assertValidateFailure(() -> ItemNameValidator.validate("    a"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);

        // Invalid names - Wrong Symbols
        assertValidateFailure(() -> ItemNameValidator.validate("\\"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertValidateFailure(() -> ItemNameValidator.validate("\\/"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertValidateFailure(() -> ItemNameValidator.validate("|"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertValidateFailure(() -> ItemNameValidator.validate("á"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertValidateFailure(() -> ItemNameValidator.validate("你"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertValidateFailure(() -> ItemNameValidator.validate("あ"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME);
        assertValidateFailure(() -> ItemNameValidator.validate("，"),
                MessageToUser.MESSAGE_FOR_INVALID_CHARACTERS_IN_NAME); // Chinese comma

        // Valid names
        ItemNameValidator.validate("a");
        ItemNameValidator.validate("peter jack");
        ItemNameValidator.validate("12345");
        ItemNameValidator.validate("peter the jack 2nd");
        ItemNameValidator.validate("CAPITAL LETTERS");
        ItemNameValidator.validate("Peter Jack the Second 2nd");
    }
}
