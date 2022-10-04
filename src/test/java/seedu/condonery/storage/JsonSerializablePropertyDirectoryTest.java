package seedu.condonery.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.condonery.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.condonery.commons.exceptions.IllegalValueException;
import seedu.condonery.commons.util.JsonUtil;
import seedu.condonery.model.PropertyDirectory;
import seedu.condonery.testutil.TypicalPersons;

public class JsonSerializablePropertyDirectoryTest {

    private static final Path TEST_DATA_FOLDER =
        Paths.get("src", "test", "data", "JsonSerializablePropertyDirectoryTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsPropertyDirectory.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonPropertyDirectory.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonPropertyDirectory.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializablePropertyDirectory dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializablePropertyDirectory.class).get();
        PropertyDirectory propertyDirectoryFromFile = dataFromFile.toModelType();
        PropertyDirectory typicalPersonsPropertyDirectory = TypicalPersons.getTypicalPropertyDirectory();
        assertEquals(propertyDirectoryFromFile, typicalPersonsPropertyDirectory);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyDirectory dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
            JsonSerializablePropertyDirectory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializablePropertyDirectory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
            JsonSerializablePropertyDirectory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializablePropertyDirectory.MESSAGE_DUPLICATE_PERSON,
            dataFromFile::toModelType);
    }

}
