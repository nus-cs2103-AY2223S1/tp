package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.STUDENT1;
import static seedu.address.testutil.TypicalTuitionClasses.TUITIONCLASS1;
import static seedu.address.testutil.TypicalTutors.TUTOR1;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.PersonBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NameContainsKeywordsPredicate<Person> firstPredicate =
                new NameContainsKeywordsPredicate<>(firstPredicateKeywordList);
        NameContainsKeywordsPredicate<Person> secondPredicate =
                new NameContainsKeywordsPredicate<>(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate<Person> firstPredicateCopy =
                new NameContainsKeywordsPredicate<>(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_invalidType_throwsClassCastException() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        NameContainsKeywordsPredicate<String> firstPredicate =
                new NameContainsKeywordsPredicate<>(firstPredicateKeywordList);
        assertThrows(ClassCastException.class, () -> firstPredicate.test("Invalid type"));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate<Person> predicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_studentNameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate<Student> predicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("Alice"));
        assertTrue(predicate.test(STUDENT1));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Pauline"));
        assertTrue(predicate.test(STUDENT1));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Carol"));
        assertTrue(predicate.test(STUDENT1));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("aLIce", "pAUline"));
        assertTrue(predicate.test(STUDENT1));
    }

    @Test
    public void test_tutorNameContainsKeywords_returnsTrue() {
        // One keyword
        NameContainsKeywordsPredicate<Tutor> predicate =
                new NameContainsKeywordsPredicate<>(Collections.singletonList("Alice"));
        assertTrue(predicate.test(TUTOR1));

        // Multiple keywords
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Pauline"));
        assertTrue(predicate.test(TUTOR1));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Carol"));
        assertTrue(predicate.test(TUTOR1));

        // Mixed-case keywords
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("aLIce", "pAUline"));
        assertTrue(predicate.test(TUTOR1));
    }

    @Test
    public void test_tuitionClassNameContainsKeywords_returnsTrue() {
        // One Keyword
        NameContainsKeywordsPredicate<TuitionClass> predicate = new NameContainsKeywordsPredicate<>(
                Collections.singletonList("P2MATH"));
        assertTrue(predicate.test(TUITIONCLASS1));

        // Only one matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("P2MATH", "123"));
        assertTrue(predicate.test(TUITIONCLASS1));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate<Person> predicate = new NameContainsKeywordsPredicate<>(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }

    @Test
    public void test_studentNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate<Student> predicate = new NameContainsKeywordsPredicate<>(Collections.emptyList());
        assertFalse(predicate.test(STUDENT1));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Collections.singletonList("123"));
        assertFalse(predicate.test(STUDENT1));

        // Keywords match phone but does not match name
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("94351253", "Bob"));
        assertFalse(predicate.test(STUDENT1));
    }

    @Test
    public void test_tutorNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate<Tutor> predicate = new NameContainsKeywordsPredicate<>(Collections.emptyList());
        assertFalse(predicate.test(TUTOR1));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Collections.singletonList("123"));
        assertFalse(predicate.test(TUTOR1));

        // Keywords match phone but does not match name
        predicate = new NameContainsKeywordsPredicate<>(Arrays.asList("94351253", "Bob"));
        assertFalse(predicate.test(TUTOR1));
    }

    @Test
    public void test_tuitionClassNameNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate<TuitionClass> predicate =
                new NameContainsKeywordsPredicate<>(Collections.emptyList());
        assertFalse(predicate.test(TUITIONCLASS1));

        // Non-matching keyword
        predicate = new NameContainsKeywordsPredicate<>(Collections.singletonList("123"));
        assertFalse(predicate.test(TUITIONCLASS1));
    }
}
