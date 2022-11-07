package swift.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static swift.storage.JsonAdaptedBridge.MISSING_FIELD_MESSAGE_FORMAT;
import static swift.testutil.Assert.assertThrows;
import static swift.testutil.TypicalBridges.DEFAULT_BRIDGE_1;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import swift.commons.exceptions.IllegalValueException;

public class JsonAdaptedBridgeTest {
    private static final String INVALID_PERSON_ID = "invalid";
    private static final String INVALID_TASK_ID = "invalid";

    private static final String VALID_PERSON_ID = DEFAULT_BRIDGE_1.getPersonId().toString();
    private static final String VALID_TASK_ID = DEFAULT_BRIDGE_1.getTaskId().toString();

    @Test
    public void toModelType_validBridgeDetails_returnsBridge() throws Exception {
        JsonAdaptedBridge bridge = new JsonAdaptedBridge(DEFAULT_BRIDGE_1);
        assertEquals(DEFAULT_BRIDGE_1, bridge.toModelType());
    }

    @Test
    public void toModelType_invalidPersonId_throwsIllegalValueException() {
        JsonAdaptedBridge bridge = new JsonAdaptedBridge(INVALID_PERSON_ID, VALID_TASK_ID);
        assertThrows(IllegalValueException.class, bridge::toModelType);
    }

    @Test
    public void toModelType_nullPersonId_throwsIllegalValueException() {
        JsonAdaptedBridge bridge = new JsonAdaptedBridge(null, VALID_TASK_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bridge::toModelType);
    }

    @Test
    public void toModelType_invalidTaskId_throwsIllegalValueException() {
        JsonAdaptedBridge bridge = new JsonAdaptedBridge(VALID_PERSON_ID, INVALID_TASK_ID);
        assertThrows(IllegalValueException.class, bridge::toModelType);
    }

    @Test
    public void toModelType_nullTaskId_throwsIllegalValueException() {
        JsonAdaptedBridge bridge = new JsonAdaptedBridge(VALID_PERSON_ID, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, UUID.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, bridge::toModelType);
    }
}
