package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ProfNus;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableProfNusTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableProfNusTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsProfNus.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonProfNus.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonProfNus.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableProfNus dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableProfNus.class).get();
        ProfNus profNusFromFile = dataFromFile.toModelType();
        ProfNus typicalStudentsProfNus = TypicalStudents.getTypicalProfNus();
        assertEquals(profNusFromFile, typicalStudentsProfNus);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableProfNus dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableProfNus.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableProfNus dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableProfNus.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableProfNus.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
