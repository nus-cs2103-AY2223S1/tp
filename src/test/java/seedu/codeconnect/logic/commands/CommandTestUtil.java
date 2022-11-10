package seedu.codeconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.codeconnect.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.codeconnect.commons.core.index.Index;
import seedu.codeconnect.logic.commands.exceptions.CommandException;
import seedu.codeconnect.model.AddressBook;
import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.person.NameContainsKeywordsPredicate;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.model.task.Task;
import seedu.codeconnect.model.task.TaskContainsKeywordsPredicate;
import seedu.codeconnect.testutil.EditPersonDescriptorBuilder;
import seedu.codeconnect.testutil.EditTaskDescriptorBuilder;

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
    public static final String VALID_MODULE_CS1101 = "CS1101";
    public static final String VALID_MODULE_CS2030S = "CS2030S";
    public static final String VALID_GITHUB_AMY = "AMYBEE";
    public static final String VALID_GITHUB_BOB = "BOB5";
    public static final String VALID_TELEGRAM_AMY = "AMYBEEE";
    public static final String VALID_TELEGRAM_BOB = "BOB555";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String MODULE_DESC_CS1101 = " " + PREFIX_MODULE + VALID_MODULE_CS1101;
    public static final String MODULE_DESC_CS2030S = " " + PREFIX_MODULE + VALID_MODULE_CS2030S;
    public static final String MODULE_DESC_COMBINED = " " + PREFIX_MODULE + VALID_MODULE_CS1101
            + "  \t " + VALID_MODULE_CS2030S;
    public static final String GITHUB_DESC_AMY = " " + PREFIX_GITHUB + VALID_GITHUB_AMY;
    public static final String GITHUB_DESC_BOB = " " + PREFIX_GITHUB + VALID_GITHUB_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_MODS_DESC = " " + PREFIX_MODULE + "cs****"; // space not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_GITHUB_DESC = " " + PREFIX_GITHUB + "amy bee"; // only one word allowed
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "box lee lee"; // only one word allowed

    public static final String VALID_TASK_NAME_FINISH_TP = "Finish TP";
    public static final String VALID_DEADLINE_FINISH_TP = "2022-11-15 00:00";
    public static final String HUMAN_DEADLINE_FINISH_TP = "15/11/2022 00:00";
    public static final String VALID_MODULE_FINISH_TP = "CS2103T";
    public static final String VALID_TASK_NAME_LAB_2 = "Lab 2";
    public static final String VALID_DEADLINE_LAB_2 = "2022-02-02 23:59";
    public static final String HUMAN_DEADLINE_LAB_2 = "2 Feb 2022 23:59";
    public static final String VALID_MODULE_LAB_2 = "CS2030S";
    public static final String VALID_TASK_NAME_BUY_GROCERIES = "Buy Groceries";
    public static final String HUMAN_DEADLINE_BUY_GROCERIES = "15/07/2022 00:00";

    public static final String TASK_NAME_DESC_FINISH_TP = " " + PREFIX_NAME + VALID_TASK_NAME_FINISH_TP;
    public static final String MODULE_DESC_FINISH_TP = " " + PREFIX_MODULE + VALID_MODULE_FINISH_TP;
    public static final String DEADLINE_DESC_FINISH_TP = " " + PREFIX_DEADLINE + HUMAN_DEADLINE_FINISH_TP;
    public static final String TASK_NAME_DESC_LAB_2 = " " + PREFIX_NAME + VALID_TASK_NAME_LAB_2;
    public static final String MODULE_DESC_LAB_2 = " " + PREFIX_MODULE + VALID_MODULE_LAB_2;
    public static final String DEADLINE_DESC_LAB_2 = " " + PREFIX_DEADLINE + HUMAN_DEADLINE_LAB_2;
    public static final String DEADLINE_DESC_BUY_GROCERIES = " " + PREFIX_DEADLINE + HUMAN_DEADLINE_BUY_GROCERIES;
    public static final String INVALID_TASK_NAME_DESC = "John\nEvelyn\tTan";
    public static final String INVALID_TASK_NAME_PREF = " " + PREFIX_NAME + " ";
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "Software Engineering";
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + "foobar";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditContactCommand.EditPersonDescriptor DESC_AMY;
    public static final EditContactCommand.EditPersonDescriptor DESC_BOB;

    public static final EditTaskCommand.EditTaskDescriptor DESC_FINISH_TP;
    public static final EditTaskCommand.EditTaskDescriptor DESC_LAB_2;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withModules(VALID_MODULE_CS1101)
                .withGithub(VALID_GITHUB_AMY).withTelegram(VALID_TELEGRAM_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withModules(VALID_MODULE_CS2030S)
                .withGithub(VALID_GITHUB_BOB).withTelegram(VALID_TELEGRAM_BOB).build();
        DESC_FINISH_TP = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_FINISH_TP)
                .withModule(VALID_MODULE_FINISH_TP).withDeadline(VALID_DEADLINE_FINISH_TP).build();
        DESC_LAB_2 = new EditTaskDescriptorBuilder().withName(VALID_TASK_NAME_LAB_2)
                .withModule(VALID_MODULE_LAB_2).withDeadline(VALID_DEADLINE_LAB_2).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task list.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getSortedTaskList().size());

        Task task = model.getSortedTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new TaskContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getSortedTaskList().size());
    }
}
