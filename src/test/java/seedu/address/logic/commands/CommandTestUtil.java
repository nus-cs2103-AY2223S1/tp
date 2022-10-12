package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectNameContainsKeywordsPredicate;
import seedu.address.testutil.EditProjectDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_STAFFNAME_AMY = "Amy Bee";
    public static final String VALID_STAFFNAME_BOB = "Bob Choo";
    public static final String VALID_STAFFCONTACT_AMY = "11111111";
    public static final String VALID_STAFFCONTACT_BOB = "22222222";
    public static final String VALID_STAFFTITLE_AMY = "Senior Software Engineer";
    public static final String VALID_STAFFTITLE_BOB = "Software Engineer";
    public static final String VALID_STAFFDEPARTMENT_AMY = "IT Department";
    public static final String VALID_STAFFDEPARTMENT_BOB = "idk Department";
    public static final String VALID_STAFFINSURANCE_AMY = "true";
    public static final String VALID_STAFFINSURANCE_BOB = "false";

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_BUDGET_AMY = "11111111";
    public static final String VALID_BUDGET_BOB = "22222222";
    public static final String VALID_DEADLINE_AMY = "2022-01-01";
    public static final String VALID_DEADLINE_BOB = "2022-12-28";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_PROJECT_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_PROJECT_NAME + VALID_NAME_BOB;
    public static final String BUDGET_DESC_AMY = " " + PREFIX_BUDGET + VALID_BUDGET_AMY;
    public static final String BUDGET_DESC_BOB = " " + PREFIX_BUDGET + VALID_BUDGET_BOB;
    public static final String DEADLINE_DESC_AMY = " " + PREFIX_DEADLINE + VALID_DEADLINE_AMY;
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + VALID_DEADLINE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_PROJECT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_BUDGET_DESC = " " + PREFIX_BUDGET + "911a"; // 'a' not allowed in budget
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "aaaa"; // wrong date format
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditProjectDescriptor DESC_AMY;
    public static final EditCommand.EditProjectDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditProjectDescriptorBuilder().withName(VALID_NAME_AMY)
                .withBudget(VALID_BUDGET_AMY).withDeadline(VALID_DEADLINE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditProjectDescriptorBuilder().withName(VALID_NAME_BOB)
                .withBudget(VALID_BUDGET_BOB).withDeadline(VALID_DEADLINE_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the address book, filtered project list and selected project in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Project> expectedFilteredList = new ArrayList<>(actualModel.getFilteredProjectList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredProjectList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the project at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showProjectAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProjectList().size());

        Project project = model.getFilteredProjectList().get(targetIndex.getZeroBased());
        final String[] splitName = project.getProjectName().fullName.split("\\s+");
        model.updateFilteredProjectList(new ProjectNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProjectList().size());
    }

}
