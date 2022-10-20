package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class PersonIsInModulePredicateTest {

    @Test
    public void equals() {
        Set<Person> firstPredicatePersonSet = new HashSet<>(Arrays.asList(AMY, BOB));
        Set<Person> secondPredicatePersonSet = new HashSet<>(Arrays.asList(CARL));
        Module firstPredicateModule = new ModuleBuilder().withPersons(firstPredicatePersonSet).build();
        Module secondPredicateModule = new ModuleBuilder().withPersons(secondPredicatePersonSet).build();

        PersonIsInModulePredicate firstPredicate = new PersonIsInModulePredicate(firstPredicateModule);
        PersonIsInModulePredicate secondPredicate = new PersonIsInModulePredicate(secondPredicateModule);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonIsInModulePredicate firstPredicateCopy = new PersonIsInModulePredicate(firstPredicateModule);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_moduleContainsPersons_returnsTrue() {
        // One person in module
        Set<Person> predicatePersonsSet = new HashSet<>(Arrays.asList(AMY));
        Module predicateModule = new ModuleBuilder().withPersons(predicatePersonsSet).build();
        PersonIsInModulePredicate predicate = new PersonIsInModulePredicate(predicateModule);
        assertTrue(predicate.test(AMY));

        // Multiple persons in module
        predicatePersonsSet = new HashSet<>(Arrays.asList(AMY, BOB, CARL));
        predicateModule = new ModuleBuilder().withPersons(predicatePersonsSet).build();
        predicate = new PersonIsInModulePredicate(predicateModule);
        assertTrue(predicate.test(AMY));
        assertTrue(predicate.test(BOB));
        assertTrue(predicate.test(CARL));
    }

    @Test
    public void test_moduleDoesNotContainPersons_returnsFalse() {
        // Zero person in module
        Set<Person> predicatePersonsSet = new HashSet<>();
        Module predicateModule = new ModuleBuilder().withPersons(predicatePersonsSet).build();
        PersonIsInModulePredicate predicate = new PersonIsInModulePredicate(predicateModule);
        assertFalse(predicate.test(AMY));

        // Missing person in module
        predicatePersonsSet = new HashSet<>(Arrays.asList(AMY, BOB));
        predicateModule = new ModuleBuilder().withPersons(predicatePersonsSet).build();
        predicate = new PersonIsInModulePredicate(predicateModule);
        assertFalse(predicate.test(CARL));
    }
}
