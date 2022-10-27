package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

class StringToTagTest {

    @Test
    void validString_returnsTag() {
        Tag tag = (Tag) new StringToTag().convertToRead("foobar");
        Tag expectedTag = new Tag("foobar");
        assertEquals(tag, expectedTag);
    }

    @Test
    void emptyString_returnsNullTag() {
        assertEquals(new Tag("null"), new StringToTag().convertToRead(""));
    }
}
