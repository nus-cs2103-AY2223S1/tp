package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_DESC_ALPHA;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ALPHA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALPHA;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ArchivedTaskList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonArchivedTaskListStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.PersonBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookStorage(temporaryFolder.resolve("taskList.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        JsonArchivedTaskListStorage archivedTaskBookStorage =
                new JsonArchivedTaskListStorage(temporaryFolder.resolve("archivedTaskList.json"));
        StorageManager storage = new StorageManager(addressBookStorage, archivedTaskBookStorage, userPrefsStorage);
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
        String listAllCommand = ListCommand.COMMAND_WORD + " " + ListAllCommand.COMMAND_WORD;
        assertCommandSuccess(listAllCommand,
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()), model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonAddressBookIoExceptionThrowingStub
        JsonAddressBookStorage addressBookStorage =
                new JsonAddressBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTaskBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        JsonArchivedTaskListStorage archivedTaskBookStorage =
                new JsonArchivedTaskListStorage(temporaryFolder.resolve("archivedTaskList.json"));
        StorageManager storage = new StorageManager(addressBookStorage, archivedTaskBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_ALPHA + MODULE_DESC_ALPHA + DEADLINE_DESC_ALPHA;
        Task expectedTask = new PersonBuilder(ALPHA).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addPerson(expectedTask);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPersonList().remove(0));
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredArchivedTaskList().remove(0));
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
        Model expectedModel = new ModelManager(model.getAddressBook(), new ArchivedTaskList(), new UserPrefs());
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
    private static class JsonAddressBookIoExceptionThrowingStub extends JsonAddressBookStorage {
        private JsonAddressBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveAddressBook(ReadOnlyTaskList addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonArchivedTaskListIoExceptionThrowingStub extends JsonArchivedTaskListStorage {
        private JsonArchivedTaskListIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveArchivedTaskList(ReadOnlyTaskList archivedTaskBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
