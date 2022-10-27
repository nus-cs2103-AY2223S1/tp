package seedu.condonery.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX;
import static seedu.condonery.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_ADDRESS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_NAME_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_PRICE_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_STATUS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TAGS_DESC_SCOTTS;
import static seedu.condonery.logic.commands.CommandTestUtil.PROPERTY_TYPE_DESC_SCOTTS;
import static seedu.condonery.testutil.Assert.assertThrows;
import static seedu.condonery.testutil.TypicalProperties.SCOTTS;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.logic.commands.exceptions.CommandException;
import seedu.condonery.logic.commands.property.AddPropertyCommand;
import seedu.condonery.logic.commands.property.ListPropertyCommand;
import seedu.condonery.logic.parser.exceptions.ParseException;
import seedu.condonery.model.Model;
import seedu.condonery.model.ModelManager;
import seedu.condonery.model.UserPrefs;
import seedu.condonery.model.client.ReadOnlyClientDirectory;
import seedu.condonery.model.property.Property;
import seedu.condonery.model.property.ReadOnlyPropertyDirectory;
import seedu.condonery.storage.JsonClientDirectoryStorage;
import seedu.condonery.storage.JsonPropertyDirectoryStorage;
import seedu.condonery.storage.JsonUserPrefsStorage;
import seedu.condonery.storage.StorageManager;
import seedu.condonery.testutil.PropertyBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private final Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonPropertyDirectoryStorage propertyDirectoryStorage =
            new JsonPropertyDirectoryStorage(temporaryFolder.resolve("propertyDirectory.json"));
        JsonClientDirectoryStorage clientDirectoryStorage =
            new JsonClientDirectoryStorage(temporaryFolder.resolve("clientDirectory.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(propertyDirectoryStorage, clientDirectoryStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete -p 9";
        assertCommandException(deleteCommand, MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListPropertyCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListPropertyCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonPropertyDirectoryIoExceptionThrowingStub
        JsonPropertyDirectoryStorage propertyDirectoryStorage =
            new JsonPropertyDirectoryIoExceptionThrowingStub(
                temporaryFolder.resolve("ioExceptionPropertyDirectory.json"));
        JsonClientDirectoryStorage clientDirectoryStorage =
                new JsonClientDirectoryIoExceptionThrowingStub(
                        temporaryFolder.resolve("ioExceptionClientDirectory.json"));
        JsonUserPrefsStorage userPrefsStorage =
            new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(propertyDirectoryStorage, clientDirectoryStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addCommand = AddPropertyCommand.COMMAND_WORD
                + PROPERTY_NAME_DESC_SCOTTS
                + PROPERTY_ADDRESS_DESC_SCOTTS
                + PROPERTY_PRICE_DESC_SCOTTS
                + PROPERTY_TAGS_DESC_SCOTTS
                + PROPERTY_STATUS_DESC_SCOTTS
                + PROPERTY_TYPE_DESC_SCOTTS;
        Property expectedProperty = new PropertyBuilder(SCOTTS).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addProperty(expectedProperty);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredPropertyList().remove(0));
    }

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
        assertEquals(expectedMessage, result.getFeedbackToUser());
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
        Model expectedModel = new ModelManager(model.getPropertyDirectory(),
                model.getClientDirectory(), new UserPrefs());
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
    private static class JsonPropertyDirectoryIoExceptionThrowingStub extends JsonPropertyDirectoryStorage {
        private JsonPropertyDirectoryIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void savePropertyDirectory(ReadOnlyPropertyDirectory propertyDirectory, Path filePath)
                throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonClientDirectoryIoExceptionThrowingStub extends JsonClientDirectoryStorage {
        private JsonClientDirectoryIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveClientDirectory(ReadOnlyClientDirectory clientDirectory, Path filePath)
                throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
