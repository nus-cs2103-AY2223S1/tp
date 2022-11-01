package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProfiles.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.profile.AddProfileCommand;
import seedu.address.logic.commands.profile.ViewProfilesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyNuScheduler;
import seedu.address.model.UserPrefs;
import seedu.address.model.profile.Profile;
import seedu.address.storage.JsonNuSchedulerStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;
import seedu.address.testutil.ProfileBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonNuSchedulerStorage nuSchedulerStorage =
                new JsonNuSchedulerStorage(temporaryFolder.resolve("nuscheduler.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(nuSchedulerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    /* This test has been commented out as the delete command has been temporarily
    disabled during option flag implementation. */

    //    @Test
    //    public void execute_commandExecutionError_throwsCommandException() {
    //        String deleteCommand = "delete 9";
    //        assertCommandException(deleteCommand, MESSAGE_INVALID_PROFILE_DISPLAYED_INDEX);
    //    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String viewProfilesCommand = ViewProfilesCommand.COMMAND_WORD + " "
                + PREFIX_OPTION + ViewProfilesCommand.COMMAND_OPTION;
        assertCommandSuccess(viewProfilesCommand, ViewProfilesCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonNuSchedulerIoExceptionThrowingStub
        JsonNuSchedulerStorage nuSchedulerStorage =
                new JsonNuSchedulerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionNuScheduler.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(nuSchedulerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addProfileCommand = AddProfileCommand.COMMAND_WORD + " " + PREFIX_OPTION
                + AddProfileCommand.COMMAND_OPTION
                + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + TELEGRAM_DESC_AMY;
        Profile expectedProfile = new ProfileBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addProfile(expectedProfile);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addProfileCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredProfileList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredProfileList().remove(0));
    }

    @Test
    public void getFilteredEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEventList().remove(0));
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
        Model expectedModel = new ModelManager(model.getNuScheduler(), new UserPrefs());
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
    private static class JsonNuSchedulerIoExceptionThrowingStub extends JsonNuSchedulerStorage {
        private JsonNuSchedulerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveNuScheduler(ReadOnlyNuScheduler nuScheduler, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
