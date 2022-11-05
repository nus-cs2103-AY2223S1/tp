package seedu.realtime.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.realtime.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.exceptions.IllegalValueException;
import seedu.realtime.commons.util.JsonUtil;
import seedu.realtime.model.realTime;
import seedu.realtime.testutil.TypicalPersons;

public class JsonSerializableRealTimeTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableRealTimeTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsRealTime.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonRealTime.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonRealTime.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableRealTime dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableRealTime.class).get();
        RealTime realTimeFromFile = dataFromFile.toModelType();
        RealTime typicalPersonsRealTime = TypicalPersons.getTypicalRealTime();
        assertEquals(realTimeFromFile, typicalPersonsRealTime);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableRealTime dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableRealTime.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableRealTime dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableRealTime.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableRealTime.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
