package foodwhere.model.commons;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag
        assertThrows(NullPointerException.class, () -> Tag.isValidTag(null));
    }

    @Test
    public void equals() {
        assertTrue(new Tag("Bob").equals(new Tag("Bob"))); //same name and capitalisation
        assertTrue(new Tag("BOB").equals(new Tag("bob"))); //case insensitive
    }

}
