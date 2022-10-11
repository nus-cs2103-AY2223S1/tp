package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(new Tag("Operations")));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        Tag tag = new Tag("Operations");
        uniqueTagList.add(tag);
        assertTrue(uniqueTagList.contains(tag));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(new Tag("Operations")));
    }

    @Test
    public void remove_existingTag_removesTags() {
        uniqueTagList.add(new Tag("Operations"));
        uniqueTagList.remove(new Tag("Operations"));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setPersons_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(new Tag("Operations"));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(new Tag("Finance"));
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(new Tag("Operations"));
        List<Tag> tagList = Collections.singletonList(new Tag("Finance"));
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(new Tag("Finance"));
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(new Tag("Operations"), new Tag("Operations"));
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }
}
