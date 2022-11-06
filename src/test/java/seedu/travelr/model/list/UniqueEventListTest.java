package seedu.travelr.model.list;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import seedu.travelr.testutil.EventBuilder;

class UniqueEventListTest {
    @Test
    void testFunction() {
        UniqueEventList list = new UniqueEventList();
        list.contains("Sample");
        list.getInternalList();
        list.setInternalList(new HashSet<>());
        list.iterator();
        list.equals(list);
        list.hashCode();
        list.sort(((o1, o2) -> 0));
        list.getEvent(new EventBuilder().build());

        assertTrue(true);
    }
}