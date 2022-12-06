package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.JeeqTracker;
import seedu.address.testutil.TypicalClients;

public class JsonSerializableJeeqTrackerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableJeeqTrackerTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientsJeeqTracker.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientJeeqTracker.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClientJeeqTracker.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableJeeqTracker dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableJeeqTracker.class).get();
        JeeqTracker jeeqTrackerFromFile = dataFromFile.toModelType();
        JeeqTracker typicalClientsJeeqTracker = TypicalClients.getTypicalJeeqTracker();
        assertEquals(jeeqTrackerFromFile, typicalClientsJeeqTracker);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJeeqTracker dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
                JsonSerializableJeeqTracker.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableJeeqTracker dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
                JsonSerializableJeeqTracker.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJeeqTracker.MESSAGE_DUPLICATE_CLIENT,
                dataFromFile::toModelType);
    }

}
