package swift.model.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class PersonTaskBridgeTest {
    @Test
    public void getPersonId_validPersonId_returnsPersonId() {
        UUID personId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        PersonTaskBridge personTaskBridge = new PersonTaskBridge(personId, taskId);
        assertEquals(personId, personTaskBridge.getPersonId());
    }

    @Test
    public void getTaskId_validTaskId_returnsTaskId() {
        UUID personId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        PersonTaskBridge personTaskBridge = new PersonTaskBridge(personId, taskId);
        assertEquals(taskId, personTaskBridge.getTaskId());
    }

    @Test
    public void equals_null_false() {
        UUID personId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        assertNotEquals(new PersonTaskBridge(personId, taskId), null);
    }

    @Test
    public void equals_differentPersonIdAndTaskId_false() {
        UUID personOneId = UUID.fromString("6517916e-80c0-40e1-ac13-7cb870f57d80");
        UUID personTwoId = UUID.fromString("049fb6e6-7e43-4075-a1e3-faad028faa0f");
        UUID taskOneId = UUID.fromString("5f3f93b9-d839-4d5c-b197-9f3e53ebbb71");
        UUID taskTwoId = UUID.fromString("f2d431ed-1793-4761-9121-3652441e0ea2");
        assertNotEquals(new PersonTaskBridge(personOneId, taskOneId), new PersonTaskBridge(personTwoId, taskTwoId));
    }
}
