package coydir.logic;

import static coydir.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static coydir.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static coydir.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.DEPARTMENT_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static coydir.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static coydir.testutil.Assert.assertThrows;
import static coydir.testutil.TypicalPersons.AMY;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import coydir.logic.commands.AddCommand;
import coydir.logic.commands.CommandResult;
import coydir.logic.commands.ListCommand;
import coydir.logic.commands.exceptions.CommandException;
import coydir.logic.parser.exceptions.ParseException;
import coydir.model.Model;
import coydir.model.ModelManager;
import coydir.model.ReadOnlyDatabase;
import coydir.model.UserPrefs;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;
import coydir.storage.JsonDatabaseStorage;
import coydir.storage.JsonUserPrefsStorage;
import coydir.storage.StorageManager;
import coydir.testutil.PersonBuilder;


public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonDatabaseStorage databaseStorage =
                new JsonDatabaseStorage(temporaryFolder.resolve("database.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(databaseStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, '"' + invalidCommand + '"' + MESSAGE_UNKNOWN_COMMAND);
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
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonDatabaseIoExceptionThrowingStub
        JsonDatabaseStorage databaseStorage =
                new JsonDatabaseIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionDatabase.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(databaseStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + POSITION_DESC_AMY + DEPARTMENT_DESC_AMY + ADDRESS_DESC_AMY;
        PersonBuilder amyBuilder = new PersonBuilder(AMY);
        String newId = String.valueOf(EmployeeId.getCount());
        Person expectedPerson = amyBuilder.withEmployeeId(newId).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedPerson);
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
        Model expectedModel = new ModelManager(model.getDatabase(), new UserPrefs());
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
        System.out.println(expectedModel.getFilteredPersonList());
        System.out.println(model.getFilteredPersonList());
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonDatabaseIoExceptionThrowingStub extends JsonDatabaseStorage {
        private JsonDatabaseIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveDatabase(ReadOnlyDatabase database, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
