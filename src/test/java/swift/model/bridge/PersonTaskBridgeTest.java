package swift.model.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
