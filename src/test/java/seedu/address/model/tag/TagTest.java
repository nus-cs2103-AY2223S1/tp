package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWES_MONEY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagEmptyName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void constructor_invalidTagNumericOnlyName_throwsIllegalArgumentException() {
        String invalidTagName = "123";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void toString_emptyCollection_returnsEmptyString() {
        assertEquals("", Tag.toString(new HashSet<>()));
        assertEquals("", Tag.toString(new ArrayList<>()));
    }

    @Test
    public void toString_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tag.toString(null));
    }

    @Test
    public void toString_nonEmptyCollection_returnsTagStrings() {
        List<Tag> oneTag = List.of(VALID_TAG_FRIENDS);
        List<Tag> multipleTags = Arrays.asList(VALID_TAG_FRIENDS, VALID_TAG_OWES_MONEY);

        assertEquals(VALID_TAG_FRIENDS.tagName, Tag.toString(oneTag));
        assertEquals(VALID_TAG_FRIENDS.tagName + ", " + VALID_TAG_OWES_MONEY.tagName,
                Tag.toString(multipleTags));
    }
}
