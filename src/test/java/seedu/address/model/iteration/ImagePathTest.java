package seedu.address.model.iteration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ImagePathTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ImagePath(null));
    }

    @Test
    public void constructor_anyString_success() {
        assertDoesNotThrow(() -> new ImagePath(""));
        assertDoesNotThrow(() -> new ImagePath(" "));
        assertDoesNotThrow(() -> new ImagePath("invalid image path"));
        assertDoesNotThrow(() -> new ImagePath("/Users/John/Downloads/test image.png"));
    }

    @Test
    public void equals() {
        String someImagePathString = "/Users/Amy/Desktop/testing.png";
        ImagePath someImagePath = new ImagePath(someImagePathString);
        ImagePath otherImagePath = new ImagePath("");

        // same object -> returns true
        assertTrue(someImagePath.equals(someImagePath));

        // different type -> returns false
        assertFalse(someImagePath.equals(1));

        // null -> returns false
        assertFalse(someImagePath.equals(null));

        // same values (copy) -> returns true
        assertTrue(someImagePath.equals(new ImagePath(someImagePathString)));

        // different feedback -> returns false
        assertFalse(someImagePath.equals(otherImagePath));
    }
}
