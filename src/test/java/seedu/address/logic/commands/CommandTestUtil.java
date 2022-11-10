package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.task.Description;
import seedu.address.model.task.Priority;
import seedu.address.model.task.PriorityEnum;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskCategory;
import seedu.address.model.task.TaskCategoryType;
import seedu.address.model.task.TaskDeadline;
import seedu.address.model.task.TaskName;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    // --------------
    // Persons
    // --------------

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

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    // --------------
    // Tasks
    // --------------

    public static final String VALID_TASK_NAME_A = "A";
    public static final String VALID_TASK_NAME_B = "B";
    public static final String VALID_DESCRIPTION_A = "Do A";
    public static final String VALID_DESCRIPTION_B = "Do B";
    public static final String VALID_PRIORITY_LOW = "low";
    public static final String VALID_PRIORITY_MEDIUM = "medium";
    public static final String VALID_CATEGORY_FRONTEND = "frontend";
    public static final String VALID_CATEGORY_OTHERS = "others";
    public static final String VALID_DEADLINE_A = "2023-01-01";
    public static final String VALID_DEADLINE_B = "2023-01-03";

    public static final String TASK_NAME_DESC_A = " " + PREFIX_NAME + VALID_TASK_NAME_A;
    public static final String TASK_NAME_DESC_B = " " + PREFIX_NAME + VALID_TASK_NAME_B;
    public static final String DESCRIPTION_DESC_A = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_A;
    public static final String DESCRIPTION_DESC_B = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_B;
    public static final String PRIORITY_DESC_LOW = " " + PREFIX_PRIORITY + VALID_PRIORITY_LOW;
    public static final String PRIORITY_DESC_MEDIUM = " " + PREFIX_PRIORITY + VALID_PRIORITY_MEDIUM;
    public static final String CATEGORY_DESC_FRONTEND = " " + PREFIX_CATEGORY + VALID_CATEGORY_FRONTEND;
    public static final String CATEGORY_DESC_OTHERS = " " + PREFIX_CATEGORY + VALID_CATEGORY_OTHERS;
    public static final String DEADLINE_DESC_A = " " + PREFIX_DEADLINE + VALID_DEADLINE_A;
    public static final String DEADLINE_DESC_B = " " + PREFIX_DEADLINE + VALID_DEADLINE_B;

    public static final String INVALID_PRIORITY = "medium-high";
    public static final String INVALID_CATEGORY = "infrastructure";
    public static final String INVALID_DEADLINE = "2022-13-01";

    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + INVALID_PRIORITY;
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + INVALID_CATEGORY;
    public static final String INVALID_DEADLINE_DESC = " " + PREFIX_DEADLINE + INVALID_DEADLINE;

    public static final EditTaskCommand.EditTaskDescriptor DESC_ONE;
    public static final EditTaskCommand.EditTaskDescriptor DESC_TWO;
    public static final TaskCategory TEST_CATEGORY_OTHERS = new TaskCategory(TaskCategoryType.OTHERS);
    public static final TaskCategory TEST_CATEGORY_FRONTEND = new TaskCategory(TaskCategoryType.FRONTEND);
    public static final Description TEST_DESCRIPTION_ONE = new Description("Test");
    public static final Description TEST_DESCRIPTION_TWO = new Description("Test test");
    public static final Priority TEST_PRIORITY_MEDIUM = new Priority(PriorityEnum.MEDIUM);
    public static final Priority TEST_PRIORITY_LOW = new Priority(PriorityEnum.LOW);
    public static final TaskDeadline TEST_DEADLINE_NOW = new TaskDeadline(LocalDate.now());
    public static final TaskDeadline TEST_DEADLINE_TOMORROW = new TaskDeadline(LocalDate.now().plusDays(1));
    private static final Person testPerson = new Person(new Name("test"), new Phone("99999999"),
            new Email("test@gmail.com"), new Address("test"), new HashSet(), new ArrayList<>());

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        Task a = new Task(new TaskName("A"), TEST_DESCRIPTION_ONE, TEST_PRIORITY_MEDIUM, TEST_CATEGORY_OTHERS,
                TEST_DEADLINE_NOW,
                testPerson, false);

        DESC_ONE = new EditTaskDescriptorBuilder(a).withName("A").build();
        DESC_TWO = new EditTaskDescriptorBuilder(a).withName("B").build();

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
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());

        final String[] name = new String[]{task.getName().toString()};
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(name)));
        assertEquals(1, model.getFilteredTaskList().size());
    }

}
