package seedu.address.model.tag;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWES_MONEY;

import org.junit.jupiter.api.Test;

public class TagSetTest {

    @Test
    public void add() {
        TagSet tagSet = new TagSet();
        assertFalse(tagSet.contains(VALID_TAG_FRIENDS));
        assertFalse(tagSet.contains(VALID_TAG_OWES_MONEY));

        tagSet.add(VALID_TAG_FRIENDS);
        assertTrue(tagSet.contains(VALID_TAG_FRIENDS));
        assertFalse(tagSet.contains(VALID_TAG_OWES_MONEY));

        tagSet.add(VALID_TAG_OWES_MONEY);
        assertTrue(tagSet.contains(VALID_TAG_FRIENDS));
        assertTrue(tagSet.contains(VALID_TAG_OWES_MONEY));
    }

    @Test
    public void remove() {
        TagSet tagSet = new TagSet();
        assertFalse(tagSet.contains(VALID_TAG_FRIENDS));
        assertFalse(tagSet.contains(VALID_TAG_OWES_MONEY));

        tagSet.add(VALID_TAG_FRIENDS);
        tagSet.add(VALID_TAG_OWES_MONEY);
        assertTrue(tagSet.contains(VALID_TAG_FRIENDS));
        assertTrue(tagSet.contains(VALID_TAG_OWES_MONEY));

        tagSet.remove(VALID_TAG_FRIENDS);
        assertFalse(tagSet.contains(VALID_TAG_FRIENDS));
        assertTrue(tagSet.contains(VALID_TAG_OWES_MONEY));

        tagSet.remove(VALID_TAG_OWES_MONEY);
        assertFalse(tagSet.contains(VALID_TAG_FRIENDS));
        assertFalse(tagSet.contains(VALID_TAG_OWES_MONEY));
    }

    @Test
    public void equals() {
        TagSet tagSet1 = new TagSet();
        tagSet1.add(VALID_TAG_FRIENDS);
        tagSet1.add(VALID_TAG_OWES_MONEY);

        TagSet tagSet2 = new TagSet();
        tagSet2.add(VALID_TAG_FRIENDS);
        tagSet2.add(VALID_TAG_OWES_MONEY);

        TagSet tagSet3 = new TagSet();
        tagSet3.add(VALID_TAG_FRIENDS);

        // Both empty -> return true
        assertTrue((new TagSet()).equals(new TagSet()));

        // Same tags -> return true
        assertTrue(tagSet1.equals(tagSet2));

        // Contain different tags -> return false
        assertFalse(tagSet1.equals(tagSet3));
    }
}
