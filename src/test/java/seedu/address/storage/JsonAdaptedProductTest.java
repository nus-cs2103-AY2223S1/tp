package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedProduct.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.product.Product;

public class JsonAdaptedProductTest {

    private static final String VALID_NAME = "product1";
    private static final String INVALID_NAME = "pr@duct";

    @Test
    public void toModelType_validProductName_returnsProduct() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct(VALID_NAME);
        assertEquals(product.toModelType(), new Product(VALID_NAME));
    }

    @Test
    public void toModelType_invalidProductName_throwsIllegalValueException() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct(INVALID_NAME);
        String expectedMessage = Product.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }

    @Test
    public void toModelType_nullProductName_throwsIllegalValueException() throws Exception {
        JsonAdaptedProduct product = new JsonAdaptedProduct((String) null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "productName");
        assertThrows(IllegalValueException.class, expectedMessage, product::toModelType);
    }
}
