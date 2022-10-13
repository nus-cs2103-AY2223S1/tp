package seedu.rc4hdb.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX;
import static seedu.rc4hdb.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.EMAIL_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.GENDER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.HOUSE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.MATRIC_NUMBER_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.NAME_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.PHONE_DESC_AMY;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.ROOM_DESC_AMY;
import static seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands.FileSwitchCommand.MESSAGE_NON_EXISTENT_FILE;
import static seedu.rc4hdb.testutil.Assert.assertThrows;
import static seedu.rc4hdb.testutil.TypicalResidents.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.rc4hdb.logic.commands.CommandResult;
import seedu.rc4hdb.logic.commands.exceptions.CommandException;
import seedu.rc4hdb.logic.commands.misccommands.HelpCommand;
import seedu.rc4hdb.logic.commands.modelcommands.AddCommand;
import seedu.rc4hdb.logic.commands.modelcommands.ListCommand;
import seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands.FileCommand;
import seedu.rc4hdb.logic.commands.storagemodelcommands.filecommands.FileSwitchCommand;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.ReadOnlyResidentBook;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.storage.JsonResidentBookStorage;
import seedu.rc4hdb.storage.JsonUserPrefsStorage;
import seedu.rc4hdb.storage.Storage;
import seedu.rc4hdb.storage.StorageManager;
import seedu.rc4hdb.testutil.ResidentBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Storage storage;
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonResidentBookStorage residentBookStorage =
                new JsonResidentBookStorage(temporaryFolder.resolve("residentBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        storage = new StorageManager(residentBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertModelCommandException(invalidCommand, MESSAGE_UNKNOWN_COMMAND, ParseException.class);
    }

    @Test
    public void execute_validMiscCommand_success() throws Exception {
        String helpCommand = HelpCommand.COMMAND_WORD;
        assertMiscCommandSuccess(helpCommand, HelpCommand.SHOWING_HELP_MESSAGE);
    }

    @Test
    public void execute_validModelCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertModelCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    // To implement StorageModelCommand execute success test

    @Test
    public void execute_modelCommandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        assertModelCommandException(deleteCommand, MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX, CommandException.class);
    }

    @Test
    public void execute_storageModelCommandExecutionError_throwsCommandException() {
        String fileSwitchCommand = FileCommand.COMMAND_WORD + " " + FileSwitchCommand.COMMAND_WORD + " "
                + " residentBook1";
        assertStorageModelCommandException(fileSwitchCommand, "residentBook1.json" + MESSAGE_NON_EXISTENT_FILE,
                CommandException.class);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonResidentBookIoExceptionThrowingStub
        JsonResidentBookStorage residentBookStorage =
                new JsonResidentBookIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionResidentBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(residentBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ROOM_DESC_AMY + GENDER_DESC_AMY + HOUSE_DESC_AMY + MATRIC_NUMBER_DESC_AMY;
        Resident expectedResident = new ResidentBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addResident(expectedResident);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertModelCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredResidentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredResidentList().remove(0));
    }

    //======================== Start of helper functions ===============================================

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     */
    private void assertMiscCommandSuccess(String inputCommand, String expectedMessage)
            throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertModelCommandFailure(String, Class, String, Model)
     */
    private void assertModelCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal storage manager state is the same as that in {@code expectedStorage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertModelCommandFailure(String, Class, String, Model)
     */
    private void assertStorageModelCommandSuccess(String inputCommand, String expectedMessage,
            Storage expectedStorage, Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedStorage, storage);
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertModelCommandFailure(String, Class, String, Model)
     */
    private void assertModelCommandException(String inputCommand, String expectedMessage,
            Class<? extends Throwable> expectedException) {
        assertModelCommandFailure(inputCommand, expectedException, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertStorageModelCommandFailure(String, Class, String, Storage, Model)
     */
    private void assertStorageModelCommandException(String inputCommand, String expectedMessage,
            Class<? extends Throwable> expectedException) {
        assertStorageModelCommandFailure(inputCommand, expectedException, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertModelCommandFailure(String, Class, String, Model)
     */
    private void assertModelCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
        assertModelCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertModelCommandSuccess(String, String, Model)
     */
    private void assertModelCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal storage manager state is the same as that in {@code expectedStorage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertStorageModelCommandSuccess(String, String, Storage, Model)
     */
    private void assertStorageModelCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Storage expectedStorage = ((StorageManager) storage).getCopy();
        Model expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
        assertStorageModelCommandFailure(inputCommand, expectedException, expectedMessage, expectedStorage,
                expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal storage manager state is the same as that in {@code expectedStorage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertStorageModelCommandSuccess(String, String, Storage, Model)
     */
    private void assertStorageModelCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Storage expectedStorage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedStorage, storage);
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonResidentBookIoExceptionThrowingStub extends JsonResidentBookStorage {
        private JsonResidentBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveResidentBook(ReadOnlyResidentBook addressBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

}
