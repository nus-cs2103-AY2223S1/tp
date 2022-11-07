package tuthub.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tuthub.commons.core.Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX;
import static tuthub.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static tuthub.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.MODULE_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.RATING_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.TEACHINGNOMINATION_DESC_AMY;
import static tuthub.logic.commands.CommandTestUtil.YEAR_DESC_AMY;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalTutors.AMY;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import tuthub.logic.commands.AddCommand;
import tuthub.logic.commands.CommandResult;
import tuthub.logic.commands.ListCommand;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.logic.parser.exceptions.ParseException;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.ReadOnlyTuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;
import tuthub.storage.JsonTuthubStorage;
import tuthub.storage.JsonUserPrefsStorage;
import tuthub.storage.StorageManager;
import tuthub.testutil.TutorBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTuthubStorage tuthubStorage =
                new JsonTuthubStorage(temporaryFolder.resolve("tuthub.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(tuthubStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }


    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTuthubIoExceptionThrowingStub
        JsonTuthubStorage tuthubStorage =
                new JsonTuthubIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTuthub.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(tuthubStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + MODULE_DESC_AMY + YEAR_DESC_AMY + STUDENTID_DESC_AMY + TEACHINGNOMINATION_DESC_AMY + RATING_DESC_AMY;
        Tutor expectedTutor = new TutorBuilder(AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTutor(expectedTutor);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredTutorList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTutorList().remove(0));
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
        Model expectedModel = new ModelManager(model.getTuthub(), new UserPrefs());
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
    private static class JsonTuthubIoExceptionThrowingStub extends JsonTuthubStorage {
        private JsonTuthubIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTuthub(ReadOnlyTuthub tuthub, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
