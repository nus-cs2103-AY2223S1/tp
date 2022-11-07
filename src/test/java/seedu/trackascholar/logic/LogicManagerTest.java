package seedu.trackascholar.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX;
import static seedu.trackascholar.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.trackascholar.logic.commands.CommandTestUtil.APPLICATION_STATUS_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.trackascholar.logic.commands.CommandTestUtil.SCHOLARSHIP_DESC_AMY;
import static seedu.trackascholar.testutil.Assert.assertThrows;
import static seedu.trackascholar.testutil.TypicalApplicants.AMY;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.logic.commands.AddCommand;
import seedu.trackascholar.logic.commands.CommandResult;
import seedu.trackascholar.logic.commands.ListCommand;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.storage.JsonTrackAScholarStorage;
import seedu.trackascholar.storage.JsonUserPrefsStorage;
import seedu.trackascholar.storage.StorageManager;
import seedu.trackascholar.testutil.ApplicantBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTrackAScholarStorage trackAScholarStorage =
                new JsonTrackAScholarStorage(temporaryFolder.resolve("trackAScholar.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(trackAScholarStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTrackAScholarIoExceptionThrowingStub
        JsonTrackAScholarStorage trackAScholarStorage =
                new JsonTrackAScholarIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTrackAScholar.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(trackAScholarStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + SCHOLARSHIP_DESC_AMY + APPLICATION_STATUS_DESC_AMY;
        Applicant expectedApplicant = new ApplicantBuilder(AMY).withMajors().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addApplicant(expectedApplicant);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredApplicantList().remove(0));
    }

    @Test
    public void getPinnedApplicantList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getPinnedApplicantList().remove(0));
    }

    @Test
    public void getTrackAScholar_validSetUp_getsTrackAScholar() {
        TrackAScholar trackAScholar = new TrackAScholar();
        assertEquals(trackAScholar, logic.getTrackAScholar());
    }

    @Test
    public void getTrackAScholarFilePath_validSetUp_getsTrackAScholarFilePath() {
        Path path = Paths.get("data", "trackAScholar.json");
        assertEquals(path, logic.getTrackAScholarFilePath());
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        logic.setGuiSettings(guiSettings);
        assertEquals(guiSettings, logic.getGuiSettings());
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
        Model expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());
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
    private static class JsonTrackAScholarIoExceptionThrowingStub extends JsonTrackAScholarStorage {
        private JsonTrackAScholarIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTrackAScholar(ReadOnlyTrackAScholar trackAScholar, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
