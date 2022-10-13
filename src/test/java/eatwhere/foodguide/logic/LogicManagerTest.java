package eatwhere.foodguide.logic;

import static eatwhere.foodguide.commons.core.Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX;
import static eatwhere.foodguide.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import eatwhere.foodguide.logic.commands.AddCommand;
import eatwhere.foodguide.logic.commands.CommandResult;
import eatwhere.foodguide.logic.commands.CommandTestUtil;
import eatwhere.foodguide.logic.commands.ListCommand;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.logic.parser.exceptions.ParseException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ModelManager;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.UserPrefs;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.storage.JsonFoodGuideStorage;
import eatwhere.foodguide.storage.JsonUserPrefsStorage;
import eatwhere.foodguide.storage.StorageManager;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EateryBuilder;
import eatwhere.foodguide.testutil.TypicalEateries;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonFoodGuideStorage foodGuideStorage =
                new JsonFoodGuideStorage(temporaryFolder.resolve("foodGuide.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(foodGuideStorage, userPrefsStorage);
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
        assertCommandException(deleteCommand, MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFoodGuideIoExceptionThrowingStub
        JsonFoodGuideStorage foodGuideStorage =
                new JsonFoodGuideIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionFoodGuide.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(foodGuideStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddCommand.COMMAND_WORD
                + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.CUISINE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY;
        Eatery expectedEatery = new EateryBuilder(TypicalEateries.AMY).withTags().build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addEatery(expectedEatery);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredEateryList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredEateryList().remove(0));
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
        Model expectedModel = new ModelManager(model.getFoodGuide(), new UserPrefs());
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
        Assert.assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonFoodGuideIoExceptionThrowingStub extends JsonFoodGuideStorage {
        private JsonFoodGuideIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFoodGuide(ReadOnlyFoodGuide foodGuide, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
