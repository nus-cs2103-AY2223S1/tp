package seedu.masslinkers.storage;
//@@author
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.masslinkers.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.commons.exceptions.IllegalValueException;
import seedu.masslinkers.commons.util.JsonUtil;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.testutil.TypicalStudents;

public class JsonSerializableMassLinkersTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMassLinkersTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsMassLinkers.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentMassLinkers.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentMassLinkers.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableMassLinkers dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableMassLinkers.class).get();
        MassLinkers massLinkersFromFile = dataFromFile.toModelType();
        MassLinkers typicalStudentsMassLinkers = TypicalStudents.getTypicalMassLinkers();
        assertEquals(massLinkersFromFile, typicalStudentsMassLinkers);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMassLinkers dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableMassLinkers.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableMassLinkers dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableMassLinkers.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMassLinkers.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
