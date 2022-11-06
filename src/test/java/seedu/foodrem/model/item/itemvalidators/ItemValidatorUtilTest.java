package seedu.foodrem.model.item.itemvalidators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Supplier;

/**
 * A class to test the ItemValidatorUtil.
 */
public class ItemValidatorUtilTest {
    /**
     * Asserts that the validation of {@code userInput} by {@code validator} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertValidateFailure(Supplier<Void> supplier, String errorMessage) {
        try {
            supplier.get();
        } catch (Exception e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
