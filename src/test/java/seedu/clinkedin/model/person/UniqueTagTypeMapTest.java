package seedu.clinkedin.model.person;

import org.junit.jupiter.api.Test;

import static seedu.clinkedin.testutil.Assert.assertThrows;

class UniqueTagTypeMapTest {

    private final UniqueTagTypeMap uniqueTagTypeMap = new UniqueTagTypeMap();

    @Test
    public void contains_nullTagType_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagTypeMap.contains(null));
    }

    @Test
    void createTagType() {
    }

    @Test
    void removeExistingTagType() {
    }

    @Test
    void setExistingTagType() {
    }

    @Test
    void contains() {
    }

    @Test
    void mergeTagTypeMap() {
    }

    @Test
    void removeTags() {
    }

    @Test
    void mergeTag() {
    }

    @Test
    void setTagType() {
    }

    @Test
    void removeTagType() {
    }

    @Test
    void getTagList() {
    }

    @Test
    void setTagTypeMap() {
    }

    @Test
    void testSetTagTypeMap() {
    }

    @Test
    void getCount() {
    }

    @Test
    void getTagCount() {
    }

    @Test
    void asUnmodifiableObservableMap() {
    }

    @Test
    void iterator() {
    }

    @Test
    void getPrefixMap() {
    }

    @Test
    void setPrefixMap() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void toStream() {
    }

    @Test
    void getTagType() {
    }

    @Test
    void testToString() {
    }

    @Test
    void getPrefixFromTagType() {
    }

    @Test
    void getTagTypeFromPrefix() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void isExist() {
    }
}
