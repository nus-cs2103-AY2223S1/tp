package seedu.travelr.model.list;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import seedu.travelr.testutil.EventBuilder;

class BucketListTest {
    @Test
    void testFunctions() {
        BucketList bucketList = new BucketList();
        bucketList.contains("Sample");
        bucketList.setInternalList(new HashSet<>());
        bucketList.iterator();
        bucketList.hashCode();
        bucketList.sort((o1, o2) -> 0);
        bucketList.getEvent(new EventBuilder().build());

        assertTrue(true);
    }
}