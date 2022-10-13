package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.category.Category;

public class CategoryTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_invaliCategory_throwsIllegalArgumentException() {
        String invaliCategory = "";
        assertThrows(IllegalArgumentException.class, () -> new Category(invaliCategory));
    }

    @Test
    public void isValiCategory() {
        // nullCategory
        assertThrows(NullPointerException.class, () ->Category.isValidCategoryName(null));

        // invalidCategory formats
        assertFalse(Category.isValidCategoryName("")); // empty string
        assertFalse(Category.isValidCategoryName(" ")); // spaces only
        assertFalse(Category.isValidCategoryName("n")); // lowercase word
        assertFalse(Category.isValidCategoryName("p")); // lowercase word
        assertFalse(Category.isValidCategoryName("Nurse")); // contains full word instead of N or P
        assertFalse(Category.isValidCategoryName("12345")); // contains non-alphanumeric characters only
        assertFalse(Category.isValidCategoryName("N3")); // contains non-alphanumeric characters
        assertFalse(Category.isValidCategoryName("NP")); // contains multiples of 'N' or 'P' instead of 'N' or 'P'

        // validCategory formats
        assertTrue(Category.isValidCategoryName("N")); // Female
        assertTrue(Category.isValidCategoryName("P")); // Male
    }
}
