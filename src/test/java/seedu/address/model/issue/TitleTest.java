package seedu.address.model.issue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        //null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        //invalid title
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // spaces only

        //valid title
        assertTrue(Title.isValidTitle("create class")); // alphabets only
        assertTrue(Title.isValidTitle("12345")); // numbers only
        assertTrue(Title.isValidTitle("^/*")); // non-alphanumeric characters only
        assertTrue(Title.isValidTitle("redo 2 classes")); // alphanumeric characters only
        assertTrue(Title.isValidTitle("a^2 = b^2 + c^2")); // all characters
        assertTrue(Title.isValidTitle("Complete Final Class")); // with capital letters
        assertTrue(Title.isValidTitle("Finish project class creation "
                + "before doing 2 sub-classes")); //long title
    }
}

