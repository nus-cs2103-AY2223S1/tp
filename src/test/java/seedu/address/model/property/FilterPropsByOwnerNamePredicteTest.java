package seedu.address.model.property;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Phone;
import seedu.address.testutil.PropertyBuilder;
import seedu.address.model.person.Name;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterPropsByOwnerNamePredicteTest {

    @Test
    public void equals() {
        Name name1 = new Name("Tommy");
        Name name2 = new Name("Johnny Walker");

        FilterPropsByOwnerNamePredicate firstPredicate =
                new FilterPropsByOwnerNamePredicate(name1);
        FilterPropsByOwnerNamePredicate secondPredicate =
                new FilterPropsByOwnerNamePredicate(name2);
        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FilterPropsByOwnerNamePredicate firstPredicateCopy =
                new  FilterPropsByOwnerNamePredicate(name1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals("Tommy"));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different property -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_propertyOwnerNameMatch_returnsTrue() {
        Owner Rick = new Owner(new Name("Ricky Tan"), new Phone("1324019384"));
        Owner Kim = new Owner(new Name("Kim"), new Phone("124801324"));

        // Owner Name matches
        FilterPropsByOwnerNamePredicate predicate =
                new FilterPropsByOwnerNamePredicate(new Name("Ricky Tan"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(Rick).build()));

        // Owner Name partially matches
        predicate = new FilterPropsByOwnerNamePredicate(new Name("Ricky"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(Rick).build()));

        // Different case
        predicate = new FilterPropsByOwnerNamePredicate(new Name("KIM"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(Kim).build()));

        //Mixed Case
        predicate = new FilterPropsByOwnerNamePredicate(new Name("RickY TAN"));
        assertTrue(predicate.test(new PropertyBuilder().withOwner(Rick).build()));
    }

    @Test
    public void test_propertyOwnerNameDoesNotMatch_returnsFalse() {
        Owner Rick = new Owner(new Name("Ricky Tan"), new Phone("1324019384"));
        Owner Kim = new Owner(new Name("Kim"), new Phone("124801324"));
        // Owner name does not match
        FilterPropsByOwnerNamePredicate predicate =
                new FilterPropsByOwnerNamePredicate(new Name("Ricky Tan"));
        assertFalse(predicate.test(new PropertyBuilder().withOwner(Kim).build()));

        predicate = new FilterPropsByOwnerNamePredicate(new Name("Ricky Tan Hi"));
        assertFalse(predicate.test(new PropertyBuilder().withOwner(Rick).build()));
    }
}
