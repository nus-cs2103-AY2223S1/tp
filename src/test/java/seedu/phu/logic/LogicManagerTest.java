package seedu.phu.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX;
import static seedu.phu.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.phu.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.POSITION_DESC_AMY;
import static seedu.phu.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.phu.testutil.Assert.assertThrows;
import static seedu.phu.testutil.TypicalInternships.AMY;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.phu.logic.commands.AddCommand;
import seedu.phu.logic.commands.CommandResult;
import seedu.phu.logic.commands.ListCommand;
import seedu.phu.logic.commands.exceptions.CommandException;
import seedu.phu.logic.parser.exceptions.ParseException;
import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.ReadOnlyInternshipBook;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.Date;
import seedu.phu.model.internship.Internship;
import seedu.phu.storage.JsonInternshipBookStorage;
import seedu.phu.storage.JsonUserPrefsStorage;
import seedu.phu.storage.StorageManager;
import seedu.phu.testutil.InternshipBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookStorage(temporaryFolder.resolve("internshipBook.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(internshipBookStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonInternshipBookIoExceptionThrowingStub
        JsonInternshipBookStorage internshipBookStorage =
                new JsonInternshipBookIoExceptionThrowingStub(temporaryFolder
                        .resolve("ioExceptionInternshipBook.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(internshipBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + REMARK_DESC_AMY + POSITION_DESC_AMY;
        Internship expectedInternship = new InternshipBuilder(AMY).withTags().withApplicationProcess("apply")
                .withDate(LocalDate.now().format(Date.DEFAULT_FORMATTER)).withWebsite("NA").build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addInternship(expectedInternship);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredInternshipList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredInternshipList().remove(0));
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
        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
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
    private static class JsonInternshipBookIoExceptionThrowingStub extends JsonInternshipBookStorage {
        private JsonInternshipBookIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
