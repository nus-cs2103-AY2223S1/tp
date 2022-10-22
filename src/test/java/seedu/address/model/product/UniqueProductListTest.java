package seedu.address.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.product.exceptions.DuplicateProductException;
import seedu.address.model.product.exceptions.ProductNotFoundException;

public class UniqueProductListTest {
    private static final Product VALID_PRODUCT1 = new Product("Product1");
    private static final Product VALID_PRODUCT1_COPY = new Product(VALID_PRODUCT1.getProductName());
    private static final Product VALID_PRODUCT2 = new Product("Product2");

    private final UniqueProductList uniqueProductList = new UniqueProductList();

    @Test
    public void contains_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.contains(null));
    }

    @Test
    public void contains_productNotInList_returnsFalse() {
        assertFalse(uniqueProductList.contains(VALID_PRODUCT1));
    }

    @Test
    public void contains_productInList_returnsTrue() {
        uniqueProductList.add(VALID_PRODUCT1);
        assertTrue(uniqueProductList.contains(VALID_PRODUCT1));
    }

    @Test
    public void contains_productWithSameIdentityFieldsInList_returnsTrue() {
        uniqueProductList.add(VALID_PRODUCT1);
        assertTrue(uniqueProductList.contains(VALID_PRODUCT1_COPY));
    }

    @Test
    public void add_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.add(null));
    }

    @Test
    public void add_duplicateProduct_throwsDuplicateProductException() {
        uniqueProductList.add(VALID_PRODUCT1);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.add(VALID_PRODUCT1_COPY));
    }

    @Test
    public void remove_nullProduct_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.remove(null));
    }

    @Test
    public void remove_productDoesNotExist_throwsProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> uniqueProductList.remove(VALID_PRODUCT1));
    }

    @Test
    public void remove_existingProduct_removesProduct() {
        uniqueProductList.add(VALID_PRODUCT1);
        uniqueProductList.remove(VALID_PRODUCT1);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setProducts_nullUniqueProductList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProducts(null));
    }

    @Test
    public void setProducts_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueProductList.setProducts(null));
    }

    @Test
    public void uniqueProductList_list_replacesOwnListWithProvidedList() {
        uniqueProductList.add(VALID_PRODUCT1);
        List<Product> productList = Collections.singletonList(VALID_PRODUCT2);
        uniqueProductList.setProducts(productList);
        UniqueProductList expectedUniqueProductList = new UniqueProductList();
        expectedUniqueProductList.add(VALID_PRODUCT2);
        assertEquals(expectedUniqueProductList, uniqueProductList);
    }

    @Test
    public void setClients_listWithDuplicateProducts_throwsDuplicateProductException() {
        List<Product> listWithDuplicatesProducts = Arrays.asList(VALID_PRODUCT1, VALID_PRODUCT1);
        assertThrows(DuplicateProductException.class, () -> uniqueProductList.setProducts(listWithDuplicatesProducts));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueProductList.asUnmodifiableObservableList().remove(0));
    }
}
