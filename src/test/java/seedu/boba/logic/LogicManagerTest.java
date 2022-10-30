package seedu.boba.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_PERSON_INFORMATION;
import static seedu.boba.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.boba.logic.commands.CommandTestUtil.BIRTHDAY_MONTH_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.REWARD_DESC_AMY;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalCustomers.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.boba.logic.commands.AddCommand;
import seedu.boba.logic.commands.CommandResult;
import seedu.boba.logic.commands.ListCommand;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.BobaBotModelManager;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.UserPrefs;
import seedu.boba.model.customer.Customer;
import seedu.boba.storage.JsonBobaBotStorage;
import seedu.boba.storage.JsonUserPrefsStorage;
import seedu.boba.storage.StorageManager;
import seedu.boba.testutil.CustomerBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private BobaBotModel bobaBotModel = new BobaBotModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonBobaBotStorage addressBookStorage =
                new JsonBobaBotStorage(temporaryFolder.resolve("addressBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(bobaBotModel, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }


    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete p/00000000"; // No Customer with this number exist
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_INFORMATION);
    }


    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, bobaBotModel);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonBobaBotIoExceptionThrowingStub
        JsonBobaBotStorage addressBookStorage =
                new JsonBobaBotIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(bobaBotModel, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + BIRTHDAY_MONTH_DESC_AMY + REWARD_DESC_AMY;
        Customer expectedCustomer = new CustomerBuilder(AMY).withTags().build();
        BobaBotModelManager expectedModel = new BobaBotModelManager();
        expectedModel.addPerson(expectedCustomer);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal bobaBotModel manager state is the same as that in {@code expectedBobaBotModel} <br>
     * @see #assertCommandFailure(String, Class, String, BobaBotModel)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            BobaBotModel expectedBobaBotModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedBobaBotModel, bobaBotModel);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, BobaBotModel)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, BobaBotModel)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, BobaBotModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        BobaBotModel expectedBobaBotModel = new BobaBotModelManager(bobaBotModel.getBobaBot(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedBobaBotModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal bobaBotModel manager state is the same as that in {@code expectedBobaBotModel} <br>
     * @see #assertCommandSuccess(String, String, BobaBotModel)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, BobaBotModel expectedBobaBotModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedBobaBotModel, bobaBotModel);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonBobaBotIoExceptionThrowingStub extends JsonBobaBotStorage {
        private JsonBobaBotIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveBobaBot(ReadOnlyBobaBot addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
