package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

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
        Tag tag1 = new Tag("limao");
        Tag tag2 = new Tag("ais");
        Tag tag3 = new Tag("satu");

        List<Tag> oneTag = List.of(tag1);
        List<Tag> multipleTags = Arrays.asList(tag1, tag2, tag3);

        assertEquals("limao", Tag.toString(oneTag));
        assertEquals("limao, ais, satu", Tag.toString(multipleTags));
    }
}
