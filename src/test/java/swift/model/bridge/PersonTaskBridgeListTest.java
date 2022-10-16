package swift.model.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static swift.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import swift.model.bridge.exceptions.BridgeNotFoundException;
import swift.model.bridge.exceptions.DuplicateBridgeException;
import swift.testutil.PersonBuilder;
import swift.testutil.TaskBuilder;
import swift.testutil.TypicalBridges;

public class PersonTaskBridgeListTest {
    private final PersonTaskBridgeList bridges = new PersonTaskBridgeList();

    @Test
    public void contains_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.contains(null));
    }

    @Test
    public void contains_bridgeNotInList_returnsFalse() {
        assertFalse(bridges.contains(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void contains_bridgeInList_returnsTrue() {
        bridges.add(TypicalBridges.DEFAULT_BRIDGE_1);
        assertTrue(bridges.contains(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void add_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateBridgeException() {
        bridges.add(TypicalBridges.DEFAULT_BRIDGE_1);
        assertThrows(DuplicateBridgeException.class, () -> bridges.add(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void remove_nullBridge_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.remove(null));
    }

    @Test
    public void remove_bridgeNotInList_throwsBridgeNotFoundException() {
        assertThrows(BridgeNotFoundException.class, () -> bridges.remove(TypicalBridges.DEFAULT_BRIDGE_1));
    }

    @Test
    public void remove_existingBridge_removesBridge() {
        bridges.add(TypicalBridges.DEFAULT_BRIDGE_1);
        bridges.remove(TypicalBridges.DEFAULT_BRIDGE_1);
        PersonTaskBridgeList expectedUniqueBridgeList = new PersonTaskBridgeList();
        assertEquals(bridges, expectedUniqueBridgeList);
    }

    @Test
    public void removePerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.removePerson(null));
    }

    @Test
    public void removePerson_existingBridge_removesBridge() {
        bridges.add(TypicalBridges.DEFAULT_BRIDGE_1);
        bridges.removePerson(new PersonBuilder().withId("c4c645da-27b3-454d-9428-5295a6ee1f33").build());
        PersonTaskBridgeList expectedUniqueBridgeList = new PersonTaskBridgeList();
        assertEquals(bridges, expectedUniqueBridgeList);
    }

    @Test
    public void removeTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bridges.removeTask(null));
    }

    @Test
    public void removeTask_existingBridge_removesBridge() {
        bridges.add(TypicalBridges.DEFAULT_BRIDGE_1);
        bridges.removeTask(new TaskBuilder().withId("bfbf250c-fd58-49b4-be15-ca12095ca2ee").build());
        PersonTaskBridgeList expectedUniqueBridgeList = new PersonTaskBridgeList();
        assertEquals(bridges, expectedUniqueBridgeList);
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bridges
                .asUnmodifiableObservableList().remove(0));
    }
}
