package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemValidatorUtilTest.assertValidateFailure;

import org.junit.jupiter.api.Test;

import seedu.foodrem.testutil.MessageToUser;

/**
 * A class to test the ItemQuantityValidator.
 */
public class ItemQuantityValidatorTest {
    @Test
    public void test_quantityInValidRange() {
        ItemQuantityValidator.validate("0");
        ItemQuantityValidator.validate("100000");
        assertValidateFailure(() -> ItemQuantityValidator.validate("100001"),
                MessageToUser.MESSAGE_FOR_QUANTITY_TOO_LARGE);
        assertValidateFailure(() -> ItemQuantityValidator.validate("-1"),
                MessageToUser.MESSAGE_FOR_QUANTITY_IS_NEGATIVE);

        ItemQuantityValidator.validate("0");
        ItemQuantityValidator.validate("10");
        ItemQuantityValidator.validate("100");
        ItemQuantityValidator.validate("0.0");
        ItemQuantityValidator.validate("0.00");
        ItemQuantityValidator.validate("0.000");
        ItemQuantityValidator.validate("0.0000");
        assertValidateFailure(() -> ItemQuantityValidator.validate("0.00000"),
                MessageToUser.MESSAGE_FOR_QUANTITY_PRECISION_TOO_HIGH);

        ItemQuantityValidator.validate("1");
        ItemQuantityValidator.validate("1.1");
        ItemQuantityValidator.validate("1.01");
        ItemQuantityValidator.validate("1.001");
        assertValidateFailure(() -> ItemQuantityValidator.validate("1.00001"),
                MessageToUser.MESSAGE_FOR_QUANTITY_PRECISION_TOO_HIGH);
    }

    @Test
    public void test_isParsableQuantity() {
        assertValidateFailure(() -> ItemQuantityValidator.validate(""),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate(" "),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("a"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("apple"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1+1"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1+1"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1 + 1"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("π"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1,234"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1/2"),
                MessageToUser.MESSAGE_FOR_QUANTITY_NOT_A_NUMBER);
    }
}
