package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.testutil.PersonBuilder;

public class PersonContainsAttributePredicateTest {

    @Test
    public void equals() {
        PersonContainsAttributePredicate firstPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("male"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());
        PersonContainsAttributePredicate secondPredicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("female"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());

        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));

    }

    @Test
    public void test_attributeContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsAttributePredicate predicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("male"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());
        assertTrue(predicate.test(new PersonBuilder().withGender("male").build()));

        // Multiple keywords
        predicate =
                new PersonContainsAttributePredicate(List.of("Alice", "Bob"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate =
                new PersonContainsAttributePredicate(List.of("Bob", "Carol"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        new PersonContainsAttributePredicate(List.of("aLiCe", "bOb"), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new HashSet<>());
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PersonContainsAttributePredicate predicate =
                new PersonContainsAttributePredicate(new ArrayList<>(), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), List.of("r4nd0m_inV4l1d_g3nd3r"), new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                        new HashSet<>());
        assertFalse(predicate.test(new PersonBuilder().withGender("female").build()));

    }
}
