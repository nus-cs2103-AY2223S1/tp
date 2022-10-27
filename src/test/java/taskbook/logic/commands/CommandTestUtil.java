package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import taskbook.commons.core.index.Index;
import taskbook.logic.commands.contacts.ContactEditCommand;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.CliSyntax;
import taskbook.model.Model;
import taskbook.model.TaskBook;
import taskbook.model.person.NameContainsKeywordsPredicate;
import taskbook.model.person.Person;
import taskbook.model.task.EditTaskDescriptor;
import taskbook.model.task.Task;
import taskbook.model.task.enums.Assignment;
import taskbook.testutil.Assert;
import taskbook.testutil.EditPersonDescriptorBuilder;
import taskbook.testutil.EditTaskDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_DESCRIPTION_WORK = "work";
    public static final String VALID_DESCRIPTION_STUDY = "study";
    public static final String VALID_DATE_2022 = "2022-01-01";
    public static final String VALID_DATE_1999 = "1999-12-31";

    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String DESCRIPTION_DESC_WORK = " " + CliSyntax.PREFIX_DESCRIPTION + VALID_DESCRIPTION_WORK;
    public static final String DESCRIPTION_DESC_STUDY = " " + CliSyntax.PREFIX_DESCRIPTION + VALID_DESCRIPTION_STUDY;
    public static final String ASSIGN_FROM_DESC_AMY = " " + CliSyntax.PREFIX_ASSIGN_FROM + VALID_NAME_AMY;
    public static final String ASSIGN_FROM_DESC_BOB = " " + CliSyntax.PREFIX_ASSIGN_FROM + VALID_NAME_BOB;
    public static final String ASSIGN_TO_DESC_AMY = " " + CliSyntax.PREFIX_ASSIGN_TO + VALID_NAME_AMY;
    public static final String ASSIGN_TO_DESC_BOB = " " + CliSyntax.PREFIX_ASSIGN_TO + VALID_NAME_BOB;
    public static final String DATE_DESC_2022 = " " + CliSyntax.PREFIX_DATE + VALID_DATE_2022;
    public static final String DATE_DESC_1999 = " " + CliSyntax.PREFIX_DATE + VALID_DATE_1999;

    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + CliSyntax.PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + CliSyntax.PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + CliSyntax.PREFIX_ADDRESS; // empty string not allowed
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_ASSIGN_FROM_DESC =
            " " + CliSyntax.PREFIX_ASSIGN_FROM + "James&"; // '&' not allowed in names
    public static final String INVALID_ASSIGN_TO_DESC =
            " " + CliSyntax.PREFIX_ASSIGN_TO + "James&"; // '&' not allowed in names

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final ContactEditCommand.EditPersonDescriptor DESC_AMY;
    public static final ContactEditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    public static final EditTaskDescriptor TASK_WORK;
    public static final EditTaskDescriptor TASK_STUDY;

    static {
        TASK_WORK = new EditTaskDescriptorBuilder()
            .withName(VALID_NAME_AMY)
            .withAssignment(Assignment.FROM)
            .withIsDone(false)
            .withDate(VALID_DATE_1999)
            .build();
        TASK_STUDY = new EditTaskDescriptorBuilder()
            .withName(VALID_NAME_BOB)
            .withAssignment(Assignment.TO)
            .withIsDone(true)
            .withDate(VALID_DATE_2022)
            .build();
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
     * - the task book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskBook expectedTaskBook = new TaskBook(actualModel.getTaskBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTaskBook, actualModel.getTaskBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s task book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonListPredicate(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        String name = task.getName().toString();
        String desc = task.getDescription().toString();
        model.updateFilteredTaskListPredicate((t) -> t.isQueryInTask(name) || t.isQueryInTask(desc));

        assertEquals(1, model.getFilteredTaskList().size());
    }
}
