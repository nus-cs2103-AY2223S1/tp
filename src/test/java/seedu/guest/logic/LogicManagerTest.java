package seedu.guest.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_GUEST_DISPLAYED_INDEX;
import static seedu.guest.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.guest.logic.commands.CommandTestUtil.DATE_RANGE_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.NUMBER_OF_GUESTS_DESC_AMY;
import static seedu.guest.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.guest.testutil.Assert.assertThrows;
import static seedu.guest.testutil.TypicalGuests.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.guest.commons.core.GuiSettings;
import seedu.guest.logic.commands.AddCommand;
import seedu.guest.logic.commands.CommandResult;
import seedu.guest.logic.commands.ListCommand;
import seedu.guest.logic.commands.exceptions.CommandException;
import seedu.guest.logic.parser.exceptions.ParseException;
import seedu.guest.model.Model;
import seedu.guest.model.ModelManager;
import seedu.guest.model.ReadOnlyGuestBook;
import seedu.guest.model.UserPrefs;
import seedu.guest.model.guest.Guest;
import seedu.guest.storage.JsonGuestBookStorage;
import seedu.guest.storage.JsonUserPrefsStorage;
import seedu.guest.storage.StorageManager;
import seedu.guest.testutil.GuestBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonGuestBookStorage guestBookStorage =
                new JsonGuestBookStorage(temporaryFolder.resolve("guestBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(guestBookStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_GUEST_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonGuestBookIoExceptionThrowingStub
        JsonGuestBookStorage guestBookStorage =
                new JsonGuestBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionGuestBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(guestBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + DATE_RANGE_DESC_AMY + NUMBER_OF_GUESTS_DESC_AMY;
        Guest expectedGuest = new GuestBuilder(AMY).build();

        ModelManager expectedModel = new ModelManager();
        expectedModel.addGuest(expectedGuest);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getGuestBook_success() {
        assertEquals(logic.getGuestBook(), model.getGuestBook());
    }

    @Test
    public void getGuestBookFilePath_success() {
        assertEquals(logic.getGuestBookFilePath(), model.getGuestBookFilePath());
    }

    @Test
    public void getGuiSettings_success() {
        assertEquals(logic.getGuiSettings(), model.getGuiSettings());
    }

    @Test
    public void setGuiSettings_success() {
        GuiSettings temp = new GuiSettings();
        logic.setGuiSettings(temp);
        assertEquals(logic.getGuiSettings(), temp);
    }

    @Test
    public void getFilteredGuestList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredGuestList().remove(0));
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
        Model expectedModel = new ModelManager(model.getGuestBook(), new UserPrefs());
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
    private static class JsonGuestBookIoExceptionThrowingStub extends JsonGuestBookStorage {
        private JsonGuestBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveGuestBook(ReadOnlyGuestBook guestBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
