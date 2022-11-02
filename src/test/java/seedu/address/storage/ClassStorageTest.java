package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachersPet;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

public class ClassStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTeachersPetTest");
    private static final Path STUDENTS_FILE = TEST_DATA_FOLDER.resolve("studentsTeachersPet.json");

    @Test
    public void execute_hasConflictSuccess() {
        assertTrue(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(13, 0), LocalTime.of(15, 0)));
        assertTrue(ClassStorage.hasConflict(LocalTime.of(11, 0), LocalTime.of(15, 30),
                LocalTime.of(13, 0), LocalTime.of(14, 0)));
    }

    @Test
    public void execute_noConflictSuccess() {
        assertFalse(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(13, 30), LocalTime.of(15, 0)));
        assertFalse(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(11, 0), LocalTime.of(12, 30)));
        assertFalse(ClassStorage.hasConflict(LocalTime.of(12, 30), LocalTime.of(13, 30),
                LocalTime.of(11, 0), LocalTime.of(12, 0)));
        assertFalse(ClassStorage.hasConflict(null, null, null, null));
    }

    @Test
    public void execute_saveClassFailure() throws Exception {
        Student student = new StudentBuilder().withName("Daniel Tan").withPhone("81201230").withNokPhone("97228333")
                .withEmail("cornelia@example.com").withAddress("10th street")
                .withClass("2022-05-05 1200-1400").build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(STUDENTS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        // Throws an exception because Alex Yeoh in studentsTeachersPet has class timing conflict with Daniel Tan.
        assertThrows(CommandException.class, () -> classStorage.saveClass(student, 3));
    }

    @Test
    public void execute_getIndexSuccess() throws Exception {
        Student student = new StudentBuilder().withName("Alex Yeoh").withPhone("87438807").withNokPhone("67192213")
                .withEmail("alexyeoh@example.com").withAddress("Blk 16").withClass("2022-05-05 1200-1400")
                .build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(STUDENTS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        assertEquals(1, ClassStorage.getIndex(student));
    }

    @Test
    public void execute_getIndexZero() throws Exception {
        // phone number here intentionally be wrong
        Student student = new StudentBuilder().withName("Alex Yeoh").withPhone("87438811").withNokPhone("67192213")
                .withEmail("alexyeoh@example.com").withAddress("Blk 16").withClass("2022-05-05 1200-1400")
                .build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(STUDENTS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        // returns 0 since there is no same student found
        assertEquals(0, ClassStorage.getIndex(student));
    }

    @Test
    public void execute_updateStudent() throws Exception {
        Student studentToEdit = new StudentBuilder().withName("Daniel Tan").withPhone("81201230")
                .withNokPhone("97228333").withEmail("cornelia@example.com").withAddress("10th street")
                .withClass("2022-05-05 1400-1430").withMoneyOwed(0).withMoneyPaid(0)
                .withAdditionalNotes("Remind student to submit homework").withRatesPerClass(40).build();
        Student editedStudent = new StudentBuilder().withName("Daniel Tan").withPhone("81201230")
                .withNokPhone("97228333").withEmail("cornelia@example.com").withAddress("10th street")
                .withClass("2022-05-05 1400-1430").withMoneyOwed(20).withMoneyPaid(10)
                .withAdditionalNotes("Remind student to submit homework").withRatesPerClass(40).build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(STUDENTS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        // edit money owed and money paid of Daniel Tan (last student on studentsTeachersPet list)
        ClassStorage.updateStudent(studentToEdit, editedStudent);
        List<Student> listOfStudents = ClassStorage.getListOfStudent(LocalDate.of(2022, 5, 5));

        assert listOfStudents.size() == 3;
        assertEquals(editedStudent, listOfStudents.get(2));
    }
}
