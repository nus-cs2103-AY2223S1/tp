package swift.model.bridge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.Assert.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import swift.testutil.PersonBuilder;
import swift.testutil.TaskBuilder;

public class PersonTaskBridgeListTest {
    private static final PersonTaskBridge DEFAULT_BRIDGE = new PersonTaskBridge(
            UUID.fromString(PersonBuilder.DEFAULT_UUID),
            UUID.fromString(TaskBuilder.DEFAULT_ID));

    private final PersonTaskBridgeList bridges = new PersonTaskBridgeList();

    @Test
    public void contains_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.contains(null));
    }

    @Test
    public void contains_bridgeNotInList_returnsFalse() {
        assertFalse(bridges.contains(DEFAULT_BRIDGE));
    }

    @Test
    public void contains_bridgeInList_returnsTrue() {
        bridges.add(DEFAULT_BRIDGE);
        assertTrue(bridges.contains(DEFAULT_BRIDGE));
    }

    @Test
    public void add_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.add(null));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bridges
                .asUnmodifiableObservableList().remove(0));
    }
}
