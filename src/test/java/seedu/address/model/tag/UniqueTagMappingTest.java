package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagMappingTest {

    private final UniqueTagMapping uniqueTagMap = new UniqueTagMapping();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMap.contains((Tag) null));
        assertThrows(NullPointerException.class, () -> uniqueTagMap.contains((String) null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagMap.contains(new Tag("Operations")));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        Tag tag = new Tag("Operations");
        uniqueTagMap.add(tag);
        assertTrue(uniqueTagMap.contains(tag));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMap.add(null));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMap.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagMap.remove(new Tag("Operations")));
    }

    @Test
    public void remove_existingTag_removesTags() {
        uniqueTagMap.add(new Tag("Operations"));
        uniqueTagMap.remove(new Tag("Operations"));
        UniqueTagMapping expectedUniqueTagMapping = new UniqueTagMapping();
        assertEquals(expectedUniqueTagMapping, uniqueTagMap);
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMap.setTags((UniqueTagMapping) null));
    }

    @Test
    public void setPersons_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagMap.add(new Tag("Operations"));
        UniqueTagMapping expectedUniqueTagMapping = new UniqueTagMapping();
        expectedUniqueTagMapping.add(new Tag("Finance"));
        uniqueTagMap.setTags(expectedUniqueTagMapping);
        assertEquals(expectedUniqueTagMapping, uniqueTagMap);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMap.setTags((HashMap<String, Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagMap.add(new Tag("Operations"));
        Map<String, Tag> tagList = Collections.singletonMap("Finance", new Tag("Finance"));
        uniqueTagMap.setTags(tagList);
        UniqueTagMapping expectedUniqueTagMapping = new UniqueTagMapping();
        expectedUniqueTagMapping.add(new Tag("Finance"));
        assertEquals(expectedUniqueTagMapping, uniqueTagMap);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        Tag uniqueTag = new Tag("Operations");

        Map<String, Tag> mappingWithDuplicateTags = Map.of(
                "Op1", uniqueTag,
                "Op2", uniqueTag
        );

        assertThrows(DuplicateTagException.class,
                // CHECKSTYLE.OFF: SeparatorWrap
                () -> uniqueTagMap.setTags(mappingWithDuplicateTags)
        );
    }

    @Test
    public void addTags_duplicateTagsAdded_doesNotAddAgain() {
        Tag firstTag = new Tag("Backstage");
        Tag secondTag = new Tag("Backstage");

        uniqueTagMap.add(firstTag);
        assertTrue(uniqueTagMap.contains(secondTag));

        int initialCount = uniqueTagMap.asUnmodifiableObservableMap().size();
        uniqueTagMap.add(secondTag);
        assertEquals(initialCount, uniqueTagMap.asUnmodifiableObservableMap().size());
    }

    @Test
    public void removeTag_removeByEquivalentTag_removesTagInMapping() {
        Tag firstTag = new Tag("Ensemble");
        Tag secondTag = new Tag("Ensemble");

        int initialCount = uniqueTagMap.asUnmodifiableObservableMap().size();
        uniqueTagMap.add(firstTag);
        uniqueTagMap.remove(secondTag);
        assertEquals(initialCount, uniqueTagMap.asUnmodifiableObservableMap().size());
        assertFalse(uniqueTagMap.contains(firstTag));
        assertFalse(uniqueTagMap.contains(firstTag.tagName));
    }

    /* TEST REMOVED, ObservableMap::remove is a supported method -- Rui Han
    @Test
    public void asUnmodifiableObservableMap_modifyMap_throwsUnsupportedOperationException() {

        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagMap.asUnmodifiableObservableMap().remove("0"));
    }
    */
}
