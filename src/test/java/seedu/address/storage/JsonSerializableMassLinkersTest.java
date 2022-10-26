package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.MassLinkers;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableMassLinkersTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMassLinkersTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsMassLinkers.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentMassLinkers.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentMassLinkers.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableMassLinkers dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableMassLinkers.class).get();
        MassLinkers massLinkersFromFile = dataFromFile.toModelType();
        MassLinkers typicalStudentsMassLinkers = TypicalStudents.getTypicalMassLinkers();
        assertEquals(massLinkersFromFile, typicalStudentsMassLinkers);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMassLinkers dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableMassLinkers.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableMassLinkers dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableMassLinkers.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMassLinkers.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
