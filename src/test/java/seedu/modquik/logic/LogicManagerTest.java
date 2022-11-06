package seedu.modquik.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.modquik.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND_TEMPLATE;
import static seedu.modquik.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.ID_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.modquik.logic.commands.CommandTestUtil.TUTORIAL_DESC_AMY;
import static seedu.modquik.testutil.Assert.assertThrows;
import static seedu.modquik.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.ListCommand;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.logic.commands.student.AddStudentCommand;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.ReadOnlyModQuik;
import seedu.modquik.model.UserPrefs;
import seedu.modquik.model.student.Student;
import seedu.modquik.storage.JsonModQuikStorage;
import seedu.modquik.storage.JsonUserPrefsStorage;
import seedu.modquik.storage.StorageManager;
import seedu.modquik.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonModQuikStorage addressBookStorage =
                new JsonModQuikStorage(temporaryFolder.resolve("modquik.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, String.format(MESSAGE_UNKNOWN_COMMAND_TEMPLATE, invalidCommand));
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete student 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonModQuikIoExceptionThrowingStub
        JsonModQuikStorage addressBookStorage =
                new JsonModQuikIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddStudentCommand.COMMAND_WORD
                + NAME_DESC_AMY + ID_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + TELEGRAM_DESC_AMY + MODULE_DESC_AMY + TUTORIAL_DESC_AMY;
        Student expectedStudent = new PersonBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedStudent);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredTutorialList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTutorialList().remove(0));
    }

    @Test
    public void getFilteredConsultationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredConsultationList().remove(0));
    }

    @Test
    public void getFilteredReminderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredReminderList().remove(0));
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
        Model expectedModel = new ModelManager(model.getModQuik(), new UserPrefs());
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
    private static class JsonModQuikIoExceptionThrowingStub extends JsonModQuikStorage {
        private JsonModQuikIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveModQuik(ReadOnlyModQuik modQuik, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
