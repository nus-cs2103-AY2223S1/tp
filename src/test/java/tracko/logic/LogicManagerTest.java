package tracko.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static tracko.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static tracko.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
// import static tracko.tracko.commands.logic.CommandTestUtil.ADDRESS_DESC_AMY;
// import static tracko.tracko.commands.logic.CommandTestUtil.EMAIL_DESC_AMY;
// import static tracko.tracko.commands.logic.CommandTestUtil.NAME_DESC_AMY;
// import static tracko.tracko.commands.logic.CommandTestUtil.PHONE_DESC_AMY;
import static tracko.testutil.Assert.assertThrows;
// import static tracko.testutil.TypicalOrders.ORDER_10;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

// import tracko.logic.commands.order.AddOrderCommand;
import tracko.logic.commands.CommandResult;
// import tracko.logic.commands.order.ListOrdersCommand;
import tracko.logic.commands.exceptions.CommandException;
import tracko.logic.parser.exceptions.ParseException;
import tracko.model.Model;
import tracko.model.ModelManager;
import tracko.model.ReadOnlyTrackO;
import tracko.model.UserPrefs;
// import tracko.model.order.Order;
// import tracko.model.person.Person;
import tracko.storage.JsonTrackOStorage;
import tracko.storage.JsonUserPrefsStorage;
import tracko.storage.StorageManager;
// import tracko.testutil.OrderBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTrackOStorage trackOStorage =
                new JsonTrackOStorage(temporaryFolder.resolve("orders.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(trackOStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    // @Test
    // public void execute_commandExecutionError_throwsCommandException() {
    //     String deleteCommand = "delete 9";
    //     assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    // }

    // @Test
    // public void execute_validCommand_success() throws Exception {
    //     String listCommand = ListOrdersCommand.COMMAND_WORD;
    //     assertCommandSuccess(listCommand, ListOrdersCommand.MESSAGE_SUCCESS, model);
    // }

    // @Test
    // public void execute_storageThrowsIoException_throwsCommandException() {
    //     // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
    //     JsonTrackOStorage trackOStorage =
    //             new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionAddressBook.json"));
    //     JsonUserPrefsStorage userPrefsStorage =
    //             new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
    //     StorageManager storage = new StorageManager(trackOStorage, userPrefsStorage);
    //     logic = new LogicManager(model, storage);
    //
    //     // Execute add command
    //     String addCommand = AddOrderCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
    //             + ADDRESS_DESC_AMY;
    //     Order expectedOrder = new OrderBuilder(ORDER_10).build();
    //     ModelManager expectedModel = new ModelManager();
    //     expectedModel.addItem(expectedOrder);
    //     String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
    //     assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    // }

    // @Test
    // public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
    //     assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
    // }

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
        Model expectedModel = new ModelManager(model.getTrackO(), new UserPrefs());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonTrackOStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTrackO(ReadOnlyTrackO trackO, Path trackOFilePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
