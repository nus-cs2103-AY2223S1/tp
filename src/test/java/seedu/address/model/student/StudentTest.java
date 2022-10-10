package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_GROUP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // same name, all other attributes different -> returns true
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withClassGroup(VALID_CLASS_GROUP_BOB).withStudentId(VALID_STUDENTID_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Student editedBob = new StudentBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameStudent(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new StudentBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameStudent(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different class group -> returns false
        editedAlice = new StudentBuilder(ALICE).withClassGroup(VALID_CLASS_GROUP_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different student id -> returns false
        editedAlice = new StudentBuilder(ALICE).withStudentId(VALID_STUDENTID_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void contains_blankKeyword_returnsTrue() {
        assertTrue(new StudentBuilder(ALICE).build().contains(""));
    }

    @Test
    public void contains_nameContainsKeywords_returnsTrue() {
        // Exact keyword
        assertTrue(new StudentBuilder().withName("Alice Bob").build().contains("alice"));

        // Only partial matching keyword
        assertTrue(new StudentBuilder().withName("Alice Bob").build().contains("ali"));
    }

    @Test
    public void contains_nameDoesNotContainKeywords_returnsFalse() {
        // Mixed-case keywords
        assertFalse(new StudentBuilder().withName("Alice Bob").build().contains("ALiCE"));

        // Non-matching keyword
        assertFalse(new StudentBuilder().withName("Alice Bob").build().contains("Carol"));
    }

    @Test
    public void contains_emailContainsKeywords_returnsTrue() {
        // Exact keyword
        assertTrue(new StudentBuilder().withEmail("alice@example.com").build().contains("alice@example.com"));

        // Only partial matching keyword
        assertTrue(new StudentBuilder().withEmail("alice@example.com").build().contains("@example.com"));
    }

    @Test
    public void contains_emailDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        assertFalse(new StudentBuilder().withEmail("alice@example.com").build().contains("carol@example.com"));

        // Mixed-case keywords
        assertFalse(new StudentBuilder().withEmail("alice@example.com").build().contains("alIce@example.com"));
    }

    @Test
    public void contains_phoneContainsKeywords_returnsTrue() {
        // Exact keyword
        assertTrue(new StudentBuilder().withPhone("12345678").build().contains("12345678"));

        // Only partial matching keyword
        assertTrue(new StudentBuilder().withPhone("12345678").build().contains("3456"));
    }

    @Test
    public void contains_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        StudentContainsKeywordsPredicate predicate = new StudentContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withPhone("98765678").build()));

        // Non-matching keyword
        predicate = new StudentContainsKeywordsPredicate(Arrays.asList("99999999"));
        assertFalse(predicate.test(new StudentBuilder().withPhone("98765678").build()));

        // Keyword with alphabets
        assertFalse(new StudentBuilder().withPhone("12345678").build().contains("aaaaa"));

        // Keyword with both numbers and alphabets
        assertFalse(new StudentBuilder().withPhone("12345678").build().contains("a9d87g"));
    }

    @Test
    public void contains_classGroupContainsKeywords_returnsTrue() {
        // Exact keyword
        assertTrue(new StudentBuilder().withClassGroup("cs2030 lab 31").build().contains("cs2030"));

        // Only partial matching keyword
        assertTrue(new StudentBuilder().withClassGroup("cs2030 lab 31").build().contains("2030"));
    }

    @Test
    public void contains_classGroupDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        assertFalse(new StudentBuilder().withClassGroup("cs2030 lab 31").build().contains("cs2103t"));


        // Mixed-case keyword
        assertFalse(new StudentBuilder().withClassGroup("cs2030 lab 31").build().contains("CS2030"));
    }

    @Test
    public void contains_studentIdContainsKeywords_returnsTrue() {
        // Exact keyword
        assertTrue(new StudentBuilder().withStudentId("e0123456").build().contains("e0123456"));

        // Partial matching keyword
        assertTrue(new StudentBuilder().withStudentId("e0123456").build().contains("234"));
    }

    @Test
    public void contains_studentIdDoesNotContainKeywords_returnsFalse() {
        // Keyword with only alphabets
        assertFalse(new StudentBuilder().withStudentId("e0123456").build().contains("aaaaa"));

        // Non-matching keyword
        assertFalse(new StudentBuilder().withStudentId("e0123456").build().contains("a9d87g"));

        // Mixed-case keyword
        assertFalse(new StudentBuilder().withStudentId("e0123456").build().contains("E01234"));
    }
}
