package seedu.address.model.commission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // valid title
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only

        // invalid title
        assertTrue(Title.isValidTitle("landscape painting")); // alphabet only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("landscape painting 2")); // alphanumeric characters
        assertTrue(Title.isValidTitle("Landscape painting 2")); // capital letters
        assertTrue(Title.isValidTitle("Very Very Very Very Very Very Wide Landscape painting 2")); // long title
    }

    @Test
    public void equals() {
        String title = "test title";
        String differentTitle = "different title";

        assertEquals(new Title(title), new Title(title)); // same title should be true
        assertNotEquals(new Title(title), new Title(differentTitle)); // different title should be false
    }
}
