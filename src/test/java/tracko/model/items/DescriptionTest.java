package tracko.model.items;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid addresses
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid addresses
        assertTrue(Description.isValidDescription("This is a wooden chair."));
        assertTrue(Description.isValidDescription("-")); // one character
        assertTrue(Description.isValidDescription("A chair imported from the USA."));
        assertTrue(Description.isValidDescription("But I must explain to you how all this mistaken idea of "
                + "denouncing pleasure and praising pain was born and I will give you a complete account of the "
                + "system, and expound the actual teachings of the great explorer of the truth, the master-builder"
                + " of human happiness.")); // a very long wall of text
    }

    @Test
    public void equals() {
        String firstDescriptionString = "This is a chair that costs a fortune. Made with leather and mahogany wood.";
        String secondDescriptionString = "Product with a guaranteed 5-star rating!";

        Description firstDescription = new Description(firstDescriptionString);
        Description secondDescription = new Description(secondDescriptionString);

        // same object -> returns true
        assertTrue(firstDescription.equals(firstDescription));

        Description firstDescriptionCopy = new Description(firstDescriptionString);
        // same values -> returns true
        assertTrue(firstDescription.equals(firstDescriptionCopy));

        // different types -> returns false
        assertFalse(firstDescription.equals(3));
        assertFalse(firstDescription.equals(29.5));

        // null -> returns false
        assertFalse(firstDescription.equals(null));

        // different description -> returns false
        assertFalse(firstDescription.equals(secondDescription));
    }
}
