package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.TeachersPet;
import seedu.address.testutil.TypicalStudents;

public class JsonSerializableTeachersPetTest {

    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonSerializableTeachersPetTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsTeachersPet.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentTeachersPet.json");
    private static final Path DUPLICATE_STUDENT_FILE =
            TEST_DATA_FOLDER.resolve("duplicateStudentTeachersPet.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        TeachersPet typicalStudentsTeachersPet = TypicalStudents.getTypicalTeachersPet();
        assertEquals(teachersPetFromFile, typicalStudentsTeachersPet);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableTeachersPet.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableTeachersPet.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableTeachersPet.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
