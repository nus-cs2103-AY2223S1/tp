package seedu.uninurse.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalTags.TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TAG_FALL_RISK;
import static seedu.uninurse.testutil.TypicalTags.TAG_NURSING_HOME;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_FALL_RISK;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.tag.exceptions.DuplicateTagException;
import seedu.uninurse.model.tag.exceptions.TagNotFoundException;

public class TagListTest {
    private final TagList emptyTagList = new TagList();
    private final TagList tagListElderly = new TagList(List.of(TAG_ELDERLY));
    private final TagList tagListFallRisk = new TagList(List.of(TAG_FALL_RISK));
    // TAG_NURSING_HOME is lexicographically < TAG_ELDERLY < TAG_FALL_RISK
    private final TagList sortedTagList = new TagList(Arrays.asList(TAG_ELDERLY, TAG_FALL_RISK));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagList(null));
    }

    @Test
    public void constructor_unsortedTagList_returnsSortedTagList() {
        TagList tagList = new TagList(Arrays.asList(TAG_FALL_RISK, TAG_ELDERLY));
        assertEquals(tagList, sortedTagList);
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        assertThrows(DuplicateTagException.class, () -> tagListElderly.add(TAG_ELDERLY));
    }

    @Test
    public void add_validTag_success() {
        TagList updatedTagList = emptyTagList.add(TAG_ELDERLY);
        assertEquals(updatedTagList, tagListElderly);
    }

    @Test
    public void add_returnsSortedTagList() {
        TagList tagList = tagListFallRisk.add(TAG_ELDERLY);
        assertEquals(tagList, sortedTagList);
    }

    @Test
    public void delete_emptyList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> emptyTagList.delete(0));
    }

    @Test
    public void delete_invalidIndex_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> emptyTagList.delete(-1));
    }

    @Test
    public void delete_validIndex_success() {
        TagList updatedTagList = tagListFallRisk.delete(0);
        assertEquals(updatedTagList, emptyTagList);
    }

    @Test
    public void delete_indexOutOfBounds_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> tagListFallRisk.delete(1));
    }

    @Test
    public void edit_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyTagList.add(null));
    }

    @Test
    public void edit_emptyList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> emptyTagList.edit(0, TAG_FALL_RISK));
    }

    @Test
    public void edit_invalidIndex_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> emptyTagList.edit(-1, TAG_FALL_RISK));
    }

    @Test
    public void edit_indexOutOfBounds_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> tagListFallRisk.edit(1, TAG_ELDERLY));
    }

    @Test
    public void edit_duplicateTag_throwsDuplicateTagException() {
        assertThrows(DuplicateTagException.class, () -> sortedTagList.edit(0, TAG_FALL_RISK));
    }

    @Test
    public void edit_validArgs_success() {
        TagList updatedTagList = tagListFallRisk.edit(0, TAG_ELDERLY);
        assertEquals(updatedTagList, tagListElderly);
    }

    @Test
    public void edit_returnsSortedTagList() {
        TagList tagList = new TagList(Arrays.asList(TAG_NURSING_HOME, TAG_ELDERLY)).edit(0, TAG_FALL_RISK);
        assertEquals(tagList, sortedTagList);
    }

    @Test
    public void get_emptyList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> emptyTagList.get(0));
    }

    @Test
    public void get_invalidIndex_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> emptyTagList.get(-1));
    }

    @Test
    public void get_indexOutOfBounds_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> tagListFallRisk.get(1));
    }

    @Test
    public void get_validIndex_success() {
        assertEquals(tagListFallRisk.get(0), TAG_FALL_RISK);
    }

    @Test
    public void size_emptyList_returnsZero() {
        assertEquals(emptyTagList.size(), 0);
    }

    @Test
    public void size_nonEmptyList_returnsNonZero() {
        assertNotEquals(tagListFallRisk.size(), 0);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(emptyTagList.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_returnsFalse() {
        assertFalse(tagListFallRisk.isEmpty());
    }

    @Test
    public void getInternalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> tagListFallRisk.getInternalList().remove(0));
    }

    @Test
    public void toString_emptyList_returnsEmptyString() {
        assertEquals(emptyTagList.toString(), "");
    }

    @Test
    public void toString_nonEmptyList() {
        String expectedString = "[" + TYPICAL_TAG_ELDERLY + "][" + TYPICAL_TAG_FALL_RISK + "]";
        assertEquals(sortedTagList.toString(), expectedString);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(tagListFallRisk, tagListFallRisk);

        // same internal list -> returns true
        TagList tagListFallRiskCopy = new TagList(List.of(TAG_FALL_RISK));
        assertEquals(tagListFallRisk, tagListFallRiskCopy);

        // different types -> returns false
        assertNotEquals(1, tagListFallRisk);

        // null -> returns false
        assertNotEquals(null, tagListFallRisk);

        // different internal list -> returns false
        assertNotEquals(tagListFallRisk, sortedTagList);
    }
}
