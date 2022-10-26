package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Product(null));
    }

    @Test
    public void constructor_invalidproductName_throwsIllegalArgumentException() {
        String invalidProductName = "";
        assertThrows(IllegalArgumentException.class, () -> new Product(invalidProductName));
    }

    @Test
    public void isValidproductName() {
        // null Product name
        assertThrows(NullPointerException.class, () -> Product.isValidProductName(null));
    }

    @Test
    public void getProductName() {
        String testProductName = "test";
        Product testProduct = new Product(testProductName);
        assertEquals(testProduct.getProductName(), testProductName);
        assertNotEquals(testProduct.getProductName(), "");
    }

}
