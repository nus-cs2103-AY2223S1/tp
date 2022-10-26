package seedu.foodrem.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.foodrem.commons.core.Messages;
import seedu.foodrem.commons.enums.CommandType;
import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.CommandTestUtil;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.logic.parser.exceptions.ParseException;
import seedu.foodrem.model.Model;
import seedu.foodrem.model.ModelManager;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.UserPrefs;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.storage.JsonFoodRemStorage;
import seedu.foodrem.storage.JsonUserPrefsStorage;
import seedu.foodrem.storage.StorageManager;
import seedu.foodrem.testutil.ItemBuilder;
import seedu.foodrem.testutil.TypicalItems;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");
    private static final String EXPECTED_SUCCESS_MESSAGE = "Listed all items";

    @TempDir
    public Path temporaryFolder;
    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonFoodRemStorage foodRemStorage =
                new JsonFoodRemStorage(temporaryFolder.resolve("foodrem.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(foodRemStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "del 9";
        assertCommandException(deleteCommand, Messages.MESSAGE_INVALID_ITEMS_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = CommandType.LIST_COMMAND.getCommandWord();
        assertCommandSuccess(listCommand, EXPECTED_SUCCESS_MESSAGE, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonFoodRemIoExceptionThrowingStub
        JsonFoodRemStorage foodRemStorage =
                new JsonFoodRemIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionFoodRem.json"));
        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(foodRemStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = CommandType.NEW_COMMAND.getCommandWord()
                + CommandTestUtil.VALID_DESC_ITEM_NAME_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_QUANTITY_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_UNIT_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_BOUGHT_DATE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_EXPIRY_DATE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_PRICE_POTATOES
                + CommandTestUtil.VALID_DESC_ITEM_REMARKS_POTATOES;
        Item expectedItem = new ItemBuilder(TypicalItems.POTATOES_WITHOUT_TAG).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addItem(expectedItem);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    //@Test
    //public void getFilteredItemList_modifyList_throwsUnsupportedOperationException() {
    //    assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredItemList().remove(0));
    //}

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
                                      Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getOutput());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     *
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
                                      String expectedMessage) {
        Model expectedModel = new ModelManager(model.getFoodRem(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     *
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
    private static class JsonFoodRemIoExceptionThrowingStub extends JsonFoodRemStorage {
        private JsonFoodRemIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveFoodRem(ReadOnlyFoodRem foodRem, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
