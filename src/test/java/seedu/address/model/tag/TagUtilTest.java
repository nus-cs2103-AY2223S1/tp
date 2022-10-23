package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TagUtilTest {

    @Test
    public void retrieveTagString_shortcutPresent_returnsResultString() {
        assertEquals(TagUtil.retrieveTagString("assm"), "assignment");
        assertEquals(TagUtil.retrieveTagString("tut"), "tutorial");
        assertEquals(TagUtil.retrieveTagString("lec"), "lecture");
    }

    @Test
    public void retrieveTagString_shortcutNotPresent_returnsSameString() {
        String testTagA = "tagA";
        String testTagB = "tagB";
        assertEquals(TagUtil.retrieveTagString(testTagA), testTagA);
        assertEquals(TagUtil.retrieveTagString(testTagB), testTagB);
    }

}
