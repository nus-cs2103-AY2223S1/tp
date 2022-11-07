package seedu.application.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.application.commons.core.Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX;
import static seedu.application.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.application.logic.commands.CommandTestUtil.COMPANY_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.CONTACT_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.POSITION_DESC_GOOGLE;
import static seedu.application.logic.commands.CommandTestUtil.STATUS_DESC_GOOGLE;
import static seedu.application.testutil.Assert.assertThrows;
import static seedu.application.testutil.TypicalApplications.GOOGLE;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.application.logic.commands.AddCommand;
import seedu.application.logic.commands.CommandResult;
import seedu.application.logic.commands.ListCommand;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;
import seedu.application.storage.JsonApplicationBookStorage;
import seedu.application.storage.JsonUserPrefsStorage;
import seedu.application.storage.StorageManager;
import seedu.application.testutil.ApplicationBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFer;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonApplicationBookStorage applicationBookStorage =
                new JsonApplicationBookStorage(temporaryFer.resolve("applicationBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFer.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(applicationBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonApplicationBookIoExceptionThrowingStub
        JsonApplicationBookStorage applicationBookStorage =
                new JsonApplicationBookIoExceptionThrowingStub(temporaryFer.resolve("ioExceptionApplicationBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFer.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(applicationBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + COMPANY_DESC_GOOGLE + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE + STATUS_DESC_GOOGLE;
        Application expectedApplication = new ApplicationBuilder(GOOGLE).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addApplication(expectedApplication);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredApplicationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredApplicationList().remove(0));
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
        Model expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
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
    private static class JsonApplicationBookIoExceptionThrowingStub extends JsonApplicationBookStorage {
        private JsonApplicationBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveApplicationBook(ReadOnlyApplicationBook applicationBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
