package foodwhere.model.tag;

import static foodwhere.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import foodwhere.model.commons.Tag;

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

}
