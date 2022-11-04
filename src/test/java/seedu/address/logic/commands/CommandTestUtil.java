package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.task.NameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditPersonTagsBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_TASK_ALPHA = "Homework Assignment 1";
    public static final String VALID_NAME_TASK_BETA = "Tutorial 2";
    public static final String VALID_MODULE_ALPHA = "CS2105";
    public static final String VALID_MODULE_BETA = "CS2103T";
    public static final String VALID_DEADLINE_ALPHA = "2022-10-12";
    public static final String VALID_DEADLINE_BETA = "2022-11-18";
    public static final String VALID_TAG_HIGH_PRIORITY = "high";
    public static final String VALID_TAG_TUTORIAL = "tutorial";

    public static final String NAME_DESC_ALPHA = " " + PREFIX_NAME + " " + VALID_NAME_TASK_ALPHA;
    public static final String NAME_DESC_BETA = " " + PREFIX_NAME + " " + VALID_NAME_TASK_BETA;
    public static final String MODULE_DESC_ALPHA = " " + PREFIX_MODULE + " " + VALID_MODULE_ALPHA;
    public static final String MODULE_DESC_BETA = " " + PREFIX_MODULE + " " + VALID_MODULE_BETA;
    public static final String DEADLINE_DESC_ALPHA = " " + PREFIX_DEADLINE + " " + VALID_DEADLINE_ALPHA;
    public static final String DEADLINE_DESC_BETA = " " + PREFIX_DEADLINE + " " + VALID_DEADLINE_BETA;
    public static final String TAG_DESC_TUTORIAL = " " + PREFIX_TAG + " " + VALID_TAG_TUTORIAL;
    public static final String TAG_DESC_HIGH_PRIORITY = " " + PREFIX_TAG + " " + VALID_TAG_HIGH_PRIORITY;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + " " + "Assignment&Homework";

    // Only alphanumeric allowed
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + " " + "CS2103T-";

    // Must be in YYYY-MM-DD
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + " " + "23-06-2022";

    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + " " + "tutorial*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_ALPHA;
    public static final EditCommand.EditPersonDescriptor DESC_BETA;

    public static final TagCommand.EditPersonTags TAG_ALPHA =
            new EditPersonTagsBuilder().withTags(VALID_TAG_HIGH_PRIORITY).build();
    public static final TagCommand.EditPersonTags TAG_BETA =
            new EditPersonTagsBuilder().withTags(VALID_TAG_TUTORIAL).build();

    static {
        DESC_ALPHA = new EditPersonDescriptorBuilder().withName(VALID_NAME_TASK_ALPHA)
                .withModule(VALID_MODULE_ALPHA).withDeadline(VALID_DEADLINE_ALPHA)
                .withTags(VALID_TAG_TUTORIAL).build();
        DESC_BETA = new EditPersonDescriptorBuilder().withName(VALID_NAME_TASK_BETA)
                .withModule(VALID_MODULE_BETA).withDeadline(VALID_DEADLINE_BETA)
                .withTags(VALID_TAG_HIGH_PRIORITY, VALID_TAG_TUTORIAL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered task list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Task task = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
