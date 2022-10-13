package seedu.address.logic.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.util.exceptions.SortedListException;

public class MaximumSortedListTest {
    private class ComparableStub implements Comparable<ComparableStub> {
        private int value;

        private ComparableStub(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof ComparableStub)) {
                return false;
            }

            ComparableStub otherComparableStub = (ComparableStub) other;
            if (otherComparableStub.value != this.value) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public int compareTo(ComparableStub other) {
            if (this.value == other.value) {
                return 0;
            } else if (this.value > other.value) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static final int MAX_LIST_SIZE = 5;
    private MaximumSortedList<ComparableStub> stubMaximumSortedList;
    private final ComparableStub comparableStubOne = new ComparableStub(1);
    private final ComparableStub comparableStubTwo = new ComparableStub(2);
    private final ComparableStub comparableStubThree = new ComparableStub(3);
    private final ComparableStub comparableStubFour = new ComparableStub(4);
    private final ComparableStub comparableStubFive = new ComparableStub(5);

    @BeforeEach
    public void setUp() {
        stubMaximumSortedList = new MaximumSortedList<>(MAX_LIST_SIZE);
    }

    @Test
    public void contains_nullComparable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stubMaximumSortedList.contains(null));
    }

    @Test
    public void contains_emptyList_returnsFalse() {
        assertFalse(stubMaximumSortedList.contains(comparableStubOne));
    }

    @Test
    public void contains_comparableInList_returnsTrue() {
        stubMaximumSortedList.add(comparableStubOne);
        assertTrue(stubMaximumSortedList.contains(comparableStubOne));
    }

    @Test
    public void contains_comparableWithSameValueInList_returnsTrue() {
        stubMaximumSortedList.add(new ComparableStub(1));
        assertTrue(stubMaximumSortedList.contains(comparableStubOne));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stubMaximumSortedList.add(null));
    }

    @Test
    public void add_duplicateComparable_returnsFalse() {
        stubMaximumSortedList.add(comparableStubOne);
        assertFalse(stubMaximumSortedList.add(comparableStubOne));
        assertFalse(stubMaximumSortedList.add(new ComparableStub(1)));
    }

    @Test
    public void add_comparableInSortedOrder() {
        stubMaximumSortedList.add(comparableStubFive);
        stubMaximumSortedList.add(comparableStubOne);
        stubMaximumSortedList.add(comparableStubThree);
        stubMaximumSortedList.add(comparableStubTwo);
        stubMaximumSortedList.add(comparableStubFour);

        for (int i = 0; i < MAX_LIST_SIZE; i++) {
            try {
                assertEquals(stubMaximumSortedList.remove(0), new ComparableStub(i + 1));
            } catch (SortedListException e) {
                fail();
            }
        }
    }

    @Test
    public void remove_nullComparable_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stubMaximumSortedList.remove(null));
    }

    @Test
    public void remove_invalidIndex_throwsSortedListException() {
        assertThrows(SortedListException.class, () -> stubMaximumSortedList.remove(-1));
        assertThrows(SortedListException.class, () -> stubMaximumSortedList.remove(-100));
        assertThrows(SortedListException.class, () -> stubMaximumSortedList.remove(10));
    }

    @Test
    public void remove_invalidComparable_throwsSortedListException() {
        stubMaximumSortedList.add(comparableStubOne);
        assertThrows(SortedListException.class, () -> stubMaximumSortedList.remove(new ComparableStub(2)));
        assertThrows(SortedListException.class, () -> stubMaximumSortedList.remove(new ComparableStub(-1)));
        assertThrows(SortedListException.class, () -> stubMaximumSortedList.remove(new ComparableStub(-10)));
    }

    @Test
    public void remove_existingComparable_removesComparable() {
        stubMaximumSortedList.add(comparableStubOne);
        try {
            stubMaximumSortedList.remove(comparableStubOne);
        } catch (SortedListException e) {
            fail();
        }
        MaximumSortedList<ComparableStub> expectedMaximumSortedList = new MaximumSortedList<>(MAX_LIST_SIZE);
        assertEquals(expectedMaximumSortedList, stubMaximumSortedList);
    }

    @Test
    public void remove_existingEquivalentComparable_removesComparable() {
        stubMaximumSortedList.add(comparableStubOne);
        try {
            stubMaximumSortedList.remove(new ComparableStub(1));
        } catch (SortedListException e) {
            fail();
        }
        MaximumSortedList<ComparableStub> expectedMaximumSortedList = new MaximumSortedList<>(MAX_LIST_SIZE);
        assertEquals(expectedMaximumSortedList, stubMaximumSortedList);
    }

    @Test
    public void equals() {
        final MaximumSortedList<ComparableStub> firstTestMaximumSortedList = new MaximumSortedList<>(MAX_LIST_SIZE);

        // same max_size value -> returns true
        assertTrue(stubMaximumSortedList.equals(firstTestMaximumSortedList));

        // same object -> returns true
        assertTrue(stubMaximumSortedList.equals(stubMaximumSortedList));

        // null -> returns false
        assertFalse(stubMaximumSortedList.equals(null));

        // different types -> returns false
        assertFalse(stubMaximumSortedList.equals(1));

        // different elements -> returns false
        stubMaximumSortedList.add(comparableStubOne);
        stubMaximumSortedList.add(comparableStubTwo);
        stubMaximumSortedList.add(comparableStubThree);

        firstTestMaximumSortedList.add(comparableStubOne);
        firstTestMaximumSortedList.add(comparableStubTwo);
        firstTestMaximumSortedList.add(comparableStubFour);

        assertFalse(stubMaximumSortedList.equals(firstTestMaximumSortedList));

        // same elements -> returns false
        final MaximumSortedList<ComparableStub> secondTestMaximumSortedList = new MaximumSortedList<>(MAX_LIST_SIZE);
        secondTestMaximumSortedList.add(comparableStubOne);
        secondTestMaximumSortedList.add(comparableStubTwo);
        secondTestMaximumSortedList.add(comparableStubThree);

        assertTrue(stubMaximumSortedList.equals(secondTestMaximumSortedList));
    }
}
