package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BUYER_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.DESIRED_CHARACTERISTICS_DESC_AMY;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.PRICE_RANGE_DESC_AMY;
import static seedu.address.logic.commands.buyer.BuyerCommandTestUtil.TAG_DESC_PRIORITY_HIGH;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBuyers.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.buyer.AddBuyerCommand;
import seedu.address.logic.commands.buyer.ListBuyersCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.storage.JsonBuyerBookStorage;
import seedu.address.storage.JsonPropertyBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.BuyerBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonBuyerBookStorage buyerBookStorage =
                new JsonBuyerBookStorage(temporaryFolder.resolve("buyerBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonPropertyBookStorage propertyBookStorage = new JsonPropertyBookStorage(
                temporaryFolder.resolve("propertyBook.json"));
        StorageManager storage = new StorageManager(buyerBookStorage, propertyBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteBuyerCommand = "deletebuyer 9";
        assertCommandException(deleteBuyerCommand, MESSAGE_INVALID_BUYER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListBuyersCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListBuyersCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonBuyerBookIoExceptionThrowingStub
        JsonBuyerBookStorage personBookStorage =
                new JsonBuyerBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionPersonBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonPropertyBookStorage propertyBookStorage =
                new JsonPropertyBookStorage(temporaryFolder.resolve("ioExceptionPropertyBook.json"));
        StorageManager storage = new StorageManager(personBookStorage, propertyBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddBuyerCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + PRICE_RANGE_DESC_AMY + DESIRED_CHARACTERISTICS_DESC_AMY
                + TAG_DESC_PRIORITY_HIGH;
        Buyer expectedBuyer = new BuyerBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addBuyer(expectedBuyer);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredBuyerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredBuyerList().remove(0));
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
        Model expectedModel = new ModelManager(model.getBuyerBook(), model.getPropertyBook(), new UserPrefs());
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
    private static class JsonBuyerBookIoExceptionThrowingStub extends JsonBuyerBookStorage {
        private JsonBuyerBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveBuyerBook(ReadOnlyBuyerBook buyerBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
