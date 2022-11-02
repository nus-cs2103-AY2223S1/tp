package seedu.classify.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_STUDENT_NAME;
import static seedu.classify.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.classify.logic.commands.CommandTestUtil.CLASS_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.PARENT_NAME_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.classify.logic.commands.CommandTestUtil.STUDENT_NAME_DESC_AMY;
import static seedu.classify.testutil.Assert.assertThrows;
import static seedu.classify.testutil.TypicalStudents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.classify.logic.commands.AddStudentCommand;
import seedu.classify.logic.commands.CommandResult;
import seedu.classify.logic.commands.ViewAllCommand;
import seedu.classify.logic.commands.exceptions.CommandException;
import seedu.classify.logic.parser.exceptions.ParseException;
import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.ReadOnlyStudentRecord;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Student;
import seedu.classify.storage.JsonStudentRecordStorage;
import seedu.classify.storage.JsonUserPrefsStorage;
import seedu.classify.storage.StorageManager;
import seedu.classify.testutil.StudentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonStudentRecordStorage studentRecordStorage =
                new JsonStudentRecordStorage(temporaryFolder.resolve("classify.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(studentRecordStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete nm/Alice";
        assertCommandException(deleteCommand, MESSAGE_INVALID_STUDENT_NAME);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ViewAllCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ViewAllCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonStudentRecordIoExceptionThrowingStub
        JsonStudentRecordStorage studentRecordStorage =
                new JsonStudentRecordIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionStudentRecord.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(studentRecordStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddStudentCommand.COMMAND_WORD + STUDENT_NAME_DESC_AMY + ID_DESC_AMY + CLASS_DESC_AMY
                + PARENT_NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY;
        Student expectedPerson = new StudentBuilder(AMY).withExams().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addStudent(expectedPerson);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredStudentList().remove(0));
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
        Model expectedModel = new ModelManager(model.getStudentRecord(), new UserPrefs());
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
    private static class JsonStudentRecordIoExceptionThrowingStub extends JsonStudentRecordStorage {
        private JsonStudentRecordIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveStudentRecord(ReadOnlyStudentRecord studentRecord, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
