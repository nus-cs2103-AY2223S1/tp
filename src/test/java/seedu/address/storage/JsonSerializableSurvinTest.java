package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Survin;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableSurvinTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSurvinTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsSurvin.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonSurvin.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonSurvin.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableSurvin dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableSurvin.class).get();
        Survin survinFromFile = dataFromFile.toModelType();
        Survin typicalPersonsSurvin = TypicalPersons.getTypicalSurvin();
        assertEquals(survinFromFile, typicalPersonsSurvin);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSurvin dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableSurvin.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableSurvin dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableSurvin.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSurvin.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
