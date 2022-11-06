package swift.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static swift.testutil.Assert.assertThrows;

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
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals_sameObject_true() {
        Tag tag = new Tag("Developer");
        assertEquals(tag, tag);
    }

    @Test
    public void equals_sameTagName_true() {
        assertEquals(new Tag("Developer"), new Tag("Developer"));
    }

    @Test
    public void equals_differentTagName_false() {
        assertNotEquals(new Tag("Developer"), new Tag("Friend"));
    }
}
