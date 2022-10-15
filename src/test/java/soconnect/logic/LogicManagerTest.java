package soconnect.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static soconnect.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static soconnect.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static soconnect.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static soconnect.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static soconnect.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalPersons.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import soconnect.logic.commands.AddCommand;
import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.ListCommand;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.UserPrefs;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.storage.JsonSoConnectStorage;
import soconnect.storage.JsonTodoListStorage;
import soconnect.storage.JsonUserPrefsStorage;
import soconnect.storage.StorageManager;
import soconnect.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonSoConnectStorage soConnectStorage =
                new JsonSoConnectStorage(temporaryFolder.resolve("soConnect.json"));
        JsonTodoListStorage todoListStorage =
            new JsonTodoListStorage(temporaryFolder.resolve("todoList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(soConnectStorage, todoListStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    @Disabled
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonSoConnectIoExceptionThrowingStub
        JsonSoConnectStorage soConnectStorage =
                new JsonSoConnectIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionSoConnect.json"));
        JsonTodoListStorage todoListStorage =
            new JsonToDoListStorageIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTodoList.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(soConnectStorage, todoListStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY;
        Person expectedPerson = new PersonBuilder(AMY).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
        expectedModel.addTag(new Tag("friend"));
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
        Model expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
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
    private static class JsonSoConnectIoExceptionThrowingStub extends JsonSoConnectStorage {
        private JsonSoConnectIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveSoConnect(ReadOnlySoConnect soConnect, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonToDoListStorageIoExceptionThrowingStub extends JsonTodoListStorage {
        private JsonToDoListStorageIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTodoList(ReadOnlyTodoList todoList, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
