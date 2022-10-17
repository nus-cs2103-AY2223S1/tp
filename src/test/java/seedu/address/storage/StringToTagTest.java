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
    void invalidString_throwsRuntimeException() {
        try {
            new StringToTag().convertToRead("");
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
        }
    }
}
