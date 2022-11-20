package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class FullGroupNamePredicateTest {
    @Test
    public void for_test_method() {
        GroupName gName = new GroupName("Alpha");
        Group g = new Group(gName, new HashSet<>());
        FullGroupNamePredicate fullGroupNamePredicate = new FullGroupNamePredicate("Alpha");
        assertTrue(fullGroupNamePredicate.test(g));
        Group g1 = new Group(new GroupName("CS2101"), new HashSet<>());
        assertFalse(fullGroupNamePredicate.test(g1));
    }

    @Test
    public void for_toString_method() {
        FullGroupNamePredicate fullGroupNamePredicate = new FullGroupNamePredicate("Alpha");
        FullGroupNamePredicate fullGroupNamePredicate1 = new FullGroupNamePredicate("Alpha");
        FullGroupNamePredicate fullGroupNamePredicate2 = new FullGroupNamePredicate("Braba");
        assertFalse(fullGroupNamePredicate == fullGroupNamePredicate1);
        assertFalse(fullGroupNamePredicate.equals(fullGroupNamePredicate2));
        assertTrue(fullGroupNamePredicate.equals(fullGroupNamePredicate));
        assertTrue(fullGroupNamePredicate.equals(fullGroupNamePredicate1));
    }
}
