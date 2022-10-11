package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;
import seedu.address.testutil.PersonBuilder;

public class ModuleTakenPredicateTest {

    @Test
    public void equals() {
        List<Module> firstPredicateKeywordsList = Collections.singletonList(new Module("CS1101S"));
        List<Module> secondPredicateKeywordsList = Arrays.asList(
                new Module("CS1101S"),
                new Module("MA1521")
        );

        ModuleTakenPredicate firstPredicate = new ModuleTakenPredicate(firstPredicateKeywordsList);
        ModuleTakenPredicate secondPredicate = new ModuleTakenPredicate(secondPredicateKeywordsList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ModuleTakenPredicate firstPredicateCopy = new ModuleTakenPredicate(firstPredicateKeywordsList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

    }

    @Test
    public void test_personTakesModules_returnsTrue() {
        // One keyword
        ModuleTakenPredicate predicate = new ModuleTakenPredicate(
                Collections.singletonList(new Module("CS1101S")));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS1101S", "CS1231S").build()));

        // Multiple keywords
        predicate = new ModuleTakenPredicate(Arrays.asList(
                new Module("CS1101S"),
                new Module("MA1521")
        ));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS1101S", "MA1521").build()));

        // Only one matching keyword
        predicate = new ModuleTakenPredicate(Arrays.asList(
                new Module("CS1101S"),
                new Module("IS1103")
        ));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS1101S", "MA1521").build()));

        // Mixed-case keywords
        predicate = new ModuleTakenPredicate(Arrays.asList(
                new Module("cs1101S"),
                new Module("Is1103")
        ));
        assertTrue(predicate.test(new PersonBuilder().withModules("CS1101S", "MA1521").build()));
    }

    @Test
    public void test_personNotTakingModules_returnsFalse() {
        // Zero keywords
        ModuleTakenPredicate predicate = new ModuleTakenPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withModules("CS1101S").build()));

        // Non-matching keyword
        predicate = new ModuleTakenPredicate(Collections.singletonList(new Module("CS1101S")));
        assertFalse(predicate.test(new PersonBuilder().withModules("FSC2101").build()));
    }
}
