package jeryl.fyp.logic;

import static jeryl.fyp.commons.core.Messages.MESSAGE_STUDENT_NOT_FOUND;
import static jeryl.fyp.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static jeryl.fyp.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.PROJECT_NAME_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static jeryl.fyp.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static jeryl.fyp.testutil.Assert.assertThrows;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.AMY;
import static jeryl.fyp.testutil.TypicalStudents.BENSON;
import static jeryl.fyp.testutil.TypicalStudents.CARL;
import static jeryl.fyp.testutil.TypicalStudents.DANIEL;
import static jeryl.fyp.testutil.TypicalStudents.ELLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.commands.CommandResult;
import jeryl.fyp.logic.commands.ListCommand;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.storage.JsonFypManagerStorage;
import jeryl.fyp.storage.JsonUserPrefsStorage;
import jeryl.fyp.storage.StorageManager;
import jeryl.fyp.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonFypManagerStorage fypManagerStorage =
                new JsonFypManagerStorage(temporaryFolder.resolve("fypManager.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(fypManagerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete -s i/A9999999Z";
        assertCommandException(deleteCommand, MESSAGE_STUDENT_NOT_FOUND);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFypManagerIoExceptionThrowingStub
        JsonFypManagerStorage fypManagerStorage =
                new JsonFypManagerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionFypManager.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(fypManagerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddStudentCommand.COMMAND_WORD + STUDENT_NAME_DESC_AMY + STUDENT_ID_DESC_AMY
                + PROJECT_NAME_DESC_AMY + EMAIL_DESC_AMY;
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
    }

    @Test
    public void getUncompletedStudentList() {
        Student[] students = { ALICE, BENSON, DANIEL };
        for (Student student : students) {
            model.addStudent(student);
        }
        assertEquals(logic.getUncompletedStudentList().get(0), ALICE);
        assertEquals(logic.getUncompletedStudentList().get(1), BENSON);
        assertThrows(IndexOutOfBoundsException.class, () -> logic.getUncompletedStudentList().get(2));
    }

    @Test
    public void getCompletedStudentList() {
        Student[] students = { ALICE, BENSON, DANIEL };
        for (Student student : students) {
            model.addStudent(student);
        }
        assertEquals(logic.getCompletedStudentList().get(0), DANIEL);
        assertThrows(IndexOutOfBoundsException.class, () -> logic.getCompletedStudentList().get(1));
    }

    @Test
    public void getSortedByProjectNameUncompletedStudentList() {
        Student[] students = { ALICE, BENSON, CARL, DANIEL };
        for (Student student : students) {
            model.addStudent(student);
        }
        assertEquals(logic.getSortedByProjectNameUncompletedStudentList().get(0), CARL);
        assertEquals(logic.getSortedByProjectNameUncompletedStudentList().get(1), BENSON);
        assertEquals(logic.getSortedByProjectNameUncompletedStudentList().get(2), ALICE);
        assertThrows(IndexOutOfBoundsException.class, () -> logic
                .getSortedByProjectNameUncompletedStudentList().get(3));
    }

    @Test
    public void getSortedByProjectStatusUncompletedStudentList() {
        Student[] students = { CARL, BENSON, DANIEL, ALICE };
        for (Student student : students) {
            model.addStudent(student);
        }
        assertEquals(logic.getSortedByProjectStatusUncompletedStudentList().get(0), CARL);
        assertEquals(logic.getSortedByProjectStatusUncompletedStudentList().get(1), ALICE);
        assertEquals(logic.getSortedByProjectStatusUncompletedStudentList().get(2), BENSON);
        assertThrows(IndexOutOfBoundsException.class, () -> logic
                .getSortedByProjectStatusUncompletedStudentList().get(3));
    }

    @Test
    public void getSortedCompletedStudentList() {
        Student[] students = { BENSON, ALICE, DANIEL, ELLE };
        for (Student student : students) {
            model.addStudent(student);
        }
        assertEquals(logic.getSortedCompletedStudentList().get(0), ELLE);
        assertEquals(logic.getSortedCompletedStudentList().get(1), DANIEL);
        assertThrows(IndexOutOfBoundsException.class, () -> logic.getSortedCompletedStudentList().get(2));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getFypManager(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonFypManagerIoExceptionThrowingStub extends JsonFypManagerStorage {
        private JsonFypManagerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFypManager(ReadOnlyFypManager fypManager, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
