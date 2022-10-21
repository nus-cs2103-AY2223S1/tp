package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachersPet;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ClassStorageTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableTeachersPetTest");
    private static final Path PERSONS_FILE = TEST_DATA_FOLDER.resolve("personsTeachersPet.json");

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
        Person person = new PersonBuilder().withName("Daniel Tan").withPhone("81201230").withNokPhone("97228333")
                .withEmail("cornelia@example.com").withAddress("10th street")
                .withClass("2022-05-05 1200-1400").build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(PERSONS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        // Throws an exception because Alex Yeoh in personsTeachersPet has class timing conflict with Daniel Tan.
        assertThrows(CommandException.class, () -> classStorage.saveClass(person, 3));
    }

    @Test
    public void execute_getIndexSuccess() throws Exception {
        Person person = new PersonBuilder().withName("Alex Yeoh").withPhone("87438807").withNokPhone("67192213")
                .withEmail("alexyeoh@example.com").withAddress("Blk 16").withClass("2022-05-05 1200-1400")
                .build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(PERSONS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        assertEquals(1, ClassStorage.getIndex(person));
    }

    @Test
    public void execute_getIndexZero() throws Exception {
        // phone number here intentionally be wrong
        Person person = new PersonBuilder().withName("Alex Yeoh").withPhone("87438811").withNokPhone("67192213")
                .withEmail("alexyeoh@example.com").withAddress("Blk 16").withClass("2022-05-05 1200-1400")
                .build();
        JsonSerializableTeachersPet dataFromFile = JsonUtil.readJsonFile(PERSONS_FILE,
                JsonSerializableTeachersPet.class).get();
        TeachersPet teachersPetFromFile = dataFromFile.toModelType();
        ModelManager modelManager = new ModelManager(teachersPetFromFile, new UserPrefs());
        ClassStorage classStorage = new ClassStorage(modelManager);
        // returns 0 since there is no same person found
        assertEquals(0, ClassStorage.getIndex(person));
    }

}
