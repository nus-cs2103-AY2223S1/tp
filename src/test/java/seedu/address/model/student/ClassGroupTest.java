package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ClassGroupTest {
    private final String nonEmptyClassGroup = "Some class group.";
    private ClassGroup classGroup = new ClassGroup(nonEmptyClassGroup);

    @Test
    void contains_classGroupContainsKeyword_returnsTrue() {
        // Blank keyword
        assertTrue(new ClassGroup("cs2030 lab 31").contains(""));

        // Exact keyword
        assertTrue(new ClassGroup("cs2030 lab 31").contains("cs2030"));

        // Partial matching keyword
        assertTrue(new ClassGroup("cs2030 lab 31").contains("2030"));
    }

    @Test
    void contains_classGroupContainsKeyword_returnsFalse() {
        // Non-matching keyword
        assertFalse(new ClassGroup("cs2030 lab 31").contains("cs2103t"));

        // Mixed-case keyword
        assertFalse(new ClassGroup("cs2030 lab 31").contains("CS2030"));

        // No class information
        assertFalse(new ClassGroup("").contains("NA"));
        assertFalse(new ClassGroup("cs2030").contains("NA"));
    }

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
