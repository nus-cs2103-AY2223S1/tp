package seedu.pennywise.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pennywise.commons.core.Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX;
import static seedu.pennywise.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.pennywise.logic.commands.CommandTestUtil.AMT_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.DATE_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.DESC_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.TAG_LUNCH;
import static seedu.pennywise.logic.commands.CommandTestUtil.TYPE_EXPENDITURE;
import static seedu.pennywise.testutil.Assert.assertThrows;
import static seedu.pennywise.testutil.TypicalEntry.LUNCH;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.pennywise.logic.commands.AddCommand;
import seedu.pennywise.logic.commands.CommandResult;
import seedu.pennywise.logic.commands.DeleteCommand;
import seedu.pennywise.logic.commands.exceptions.CommandException;
import seedu.pennywise.logic.parser.CliSyntax;
import seedu.pennywise.logic.parser.exceptions.ParseException;
import seedu.pennywise.model.Model;
import seedu.pennywise.model.ModelManager;
import seedu.pennywise.model.ReadOnlyPennyWise;
import seedu.pennywise.model.UserPrefs;
import seedu.pennywise.model.entry.Entry;
import seedu.pennywise.model.entry.EntryType;
import seedu.pennywise.storage.JsonPennyWiseStorage;
import seedu.pennywise.storage.JsonUserPrefsStorage;
import seedu.pennywise.storage.StorageManager;
import seedu.pennywise.testutil.ExpenditureBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonPennyWiseStorage pennyWiseStorage =
                new JsonPennyWiseStorage(temporaryFolder.resolve("pennywise.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(pennyWiseStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = DeleteCommand.COMMAND_WORD
            + " 10 "
            + CliSyntax.PREFIX_TYPE
            + EntryType.ENTRY_TYPE_EXPENDITURE;
        assertCommandException(deleteCommand, MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }


    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonPennyWiseIoExceptionThrowingStub
        JsonPennyWiseStorage pennyWiseStorage =
                new JsonPennyWiseIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionPennyWise.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(pennyWiseStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD
                + TYPE_EXPENDITURE
                + DESC_LUNCH
                + AMT_LUNCH
                + DATE_LUNCH
                + TAG_LUNCH;
        Entry expectedExpenditure = new ExpenditureBuilder(LUNCH).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addExpenditure(expectedExpenditure);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredExpenditureList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredExpenditureList().remove(0));
    }

    @Test
    public void getFilteredIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredIncomeList().remove(0));
    }

    @Test
    public void getExpensePieChartData_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getExpensePieChartData().remove(0));
    }

    @Test
    public void getIncomePieChartData_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getIncomePieChartData().remove(0));
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
        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
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
    private static class JsonPennyWiseIoExceptionThrowingStub extends JsonPennyWiseStorage {
        private JsonPennyWiseIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePennyWise(ReadOnlyPennyWise pennyWise, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
