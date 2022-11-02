package foodwhere.model.stall.comparator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.testutil.TypicalStalls;

public class NameComparatorTest {

    final NameComparator testComparator = new NameComparator();

    @Test
    public void compare_generalTesting_success() {
        // A < B -> return < 0
        int test1 = testComparator.compare(TypicalStalls.ALICE, TypicalStalls.BOB);
        assertTrue(test1 < 0);

        // A = B -> return 0
        int test2 = testComparator.compare(TypicalStalls.CARL, TypicalStalls.CARL);
        assertTrue(test2 == 0);

        // A > B -> return > 0
        int test3 = testComparator.compare(TypicalStalls.CARL, TypicalStalls.ALICE);
        assertTrue(test3 > 0);

        // testing reversed comparator
        // A < B -> return > 0
        int test4 = testComparator.reversed().compare(TypicalStalls.ALICE, TypicalStalls.BOB);
        assertTrue(test4 > 0);

        // A = B -> return 0
        int test5 = testComparator.reversed().compare(TypicalStalls.CARL, TypicalStalls.CARL);
        assertTrue(test5 == 0);

        // A > B -> return < 0
        int test6 = testComparator.reversed().compare(TypicalStalls.CARL, TypicalStalls.ALICE);
        assertTrue(test6 < 0);
    }

    @Test
    public void compare_nullStalls_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> testComparator.compare(null, null));
        assertThrows(NullPointerException.class, () -> testComparator.compare(TypicalStalls.ALICE, null));
        assertThrows(NullPointerException.class, () -> testComparator.compare(null, TypicalStalls.ALICE));
    }

    @Test
    public void equals_generalTesting_success() {
        // same object -> return true
        assertTrue(testComparator.equals(testComparator));

        // null -> return false
        assertFalse(testComparator.equals(null));

        // different object -> return false;
        assertFalse(testComparator.equals(2));

        // same type of object -> return true;
        NameComparator secondComparator = new NameComparator();
        assertTrue(testComparator.equals(secondComparator));
    }
}
