package seedu.classify.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classify.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.classify.commons.exceptions.IllegalValueException;
import seedu.classify.commons.util.JsonUtil;
import seedu.classify.model.StudentRecord;
import seedu.classify.testutil.TypicalStudents;

public class JsonSerializableStudentRecordTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentRecordTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentRecord.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidStudentRecord.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentRecord.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableStudentRecord dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableStudentRecord.class).get();
        StudentRecord studentRecordFromFile = dataFromFile.toModelType();
        StudentRecord typicalStudentRecord = TypicalStudents.getTypicalStudentRecord();
        assertEquals(studentRecordFromFile, typicalStudentRecord);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentRecord dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableStudentRecord.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentRecord dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableStudentRecord.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentRecord.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
