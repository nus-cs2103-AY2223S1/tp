package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class ClassGroupTest {
    private final String nonEmptyClassGroup = "Some class group.";
    private ClassGroup classGroup = new ClassGroup(nonEmptyClassGroup);

    @Test
    void testToString() {
        assertEquals(nonEmptyClassGroup, classGroup.toString());
    }

    @Test
    void testEquals() {

        // same object -> returns true
        assertEquals(classGroup, classGroup);

        // same values -> returns true
        ClassGroup classGroupCopy = new ClassGroup(classGroup.value);
        assertEquals(classGroup, classGroupCopy);

        // different types -> returns false
        assertNotEquals(1, classGroup);

        // null -> returns false
        assertNotEquals(null, classGroup);

        // different remark -> returns false
        ClassGroup differentClassGroup = new ClassGroup("Another class");
        assertNotEquals(classGroup, differentClassGroup);
    }

    @Test
    void testHashCode() {
        assertEquals(classGroup.hashCode(), nonEmptyClassGroup.hashCode());
    }
}
