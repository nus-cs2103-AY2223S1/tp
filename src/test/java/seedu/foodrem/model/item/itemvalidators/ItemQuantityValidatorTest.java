package seedu.foodrem.model.item.itemvalidators;

import static seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator.MESSAGE_FOR_NOT_A_NUMBER;
import static seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator.MESSAGE_FOR_PRECISION_TOO_HIGH;
import static seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator.MESSAGE_FOR_QUANTITY_IS_NEGATIVE;
import static seedu.foodrem.model.item.itemvalidators.ItemQuantityValidator.MESSAGE_FOR_QUANTITY_TOO_LARGE;
import static seedu.foodrem.model.item.itemvalidators.ItemValidatorUtilTest.assertValidateFailure;

import org.junit.jupiter.api.Test;

public class ItemQuantityValidatorTest {
    @Test
    public void test_quantityInValidRange() {
        ItemQuantityValidator.validate("0");
        ItemQuantityValidator.validate("100000");
        assertValidateFailure(() -> ItemQuantityValidator.validate("100001"),
                MESSAGE_FOR_QUANTITY_TOO_LARGE);
        assertValidateFailure(() -> ItemQuantityValidator.validate("-1"),
                MESSAGE_FOR_QUANTITY_IS_NEGATIVE);

        ItemQuantityValidator.validate("0");
        ItemQuantityValidator.validate("10");
        ItemQuantityValidator.validate("100");
        ItemQuantityValidator.validate("0.0");
        ItemQuantityValidator.validate("0.00");
        ItemQuantityValidator.validate("0.000");
        ItemQuantityValidator.validate("0.0000");
        assertValidateFailure(() -> ItemQuantityValidator.validate("0.00000"),
                MESSAGE_FOR_PRECISION_TOO_HIGH);

        ItemQuantityValidator.validate("1");
        ItemQuantityValidator.validate("1.1");
        ItemQuantityValidator.validate("1.01");
        ItemQuantityValidator.validate("1.001");
        assertValidateFailure(() -> ItemQuantityValidator.validate("1.00001"),
                MESSAGE_FOR_PRECISION_TOO_HIGH);
    }

    @Test
    public void test_isParsableQuantity() {
        assertValidateFailure(() -> ItemQuantityValidator.validate(""), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate(" "), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("a"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("apple"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1+1"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1+1"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1 + 1"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("π"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1,234"), MESSAGE_FOR_NOT_A_NUMBER);
        assertValidateFailure(() -> ItemQuantityValidator.validate("1/2"), MESSAGE_FOR_NOT_A_NUMBER);
    }
}
