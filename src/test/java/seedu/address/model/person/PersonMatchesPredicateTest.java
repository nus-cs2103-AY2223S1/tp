package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class PersonMatchesPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateNamesList = Collections.singletonList("firstNames");
        List<String> firstPredicateModuleList = Collections.singletonList("firstModules");
        List<String> secondPredicateNamesList = Arrays.asList("firstNames", "secondModules");
        List<String> secondPredicateModuleList = Arrays.asList("firstModules", "secondModules");


        PersonMatchesPredicate firstPredicate = new PersonMatchesPredicate();
        PersonMatchesPredicate secondPredicate = new PersonMatchesPredicate();
        firstPredicate.setNamesList(firstPredicateNamesList);
        firstPredicate.setModulesList(firstPredicateModuleList);
        secondPredicate.setNamesList(secondPredicateNamesList);
        secondPredicate.setModulesList(secondPredicateModuleList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonMatchesPredicate firstPredicateCopy = new PersonMatchesPredicate();
        firstPredicateCopy.setNamesList(firstPredicateNamesList);
        firstPredicateCopy.setModulesList(firstPredicateModuleList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One names
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();

        predicate.setNamesList(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple names
        predicate.setNamesList(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching names
        predicate.setNamesList(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case names
        predicate.setNamesList(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        PersonMatchesPredicate predicate = new PersonMatchesPredicate();
        predicate.setNamesList(Collections.emptyList());
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        predicate.setNamesList(Arrays.asList("Carol"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        predicate.setNamesList(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new StudentBuilder().withName("Alice").withPhone("12345")
            .withEmail("alice@email.com").withGender("F").build()));
    }
}
