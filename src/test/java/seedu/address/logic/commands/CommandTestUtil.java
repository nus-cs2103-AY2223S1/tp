package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_ADD_STUDENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_DELETE_STUDENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentContainsKeywordsPredicate;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

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
    public static final String VALID_CLASS_GROUP_AMY = "CS2030 Lab 31";
    public static final String VALID_CLASS_GROUP_BOB = "CS2040S Tutorial 13";
    public static final String VALID_STUDENTID_AMY = "e0111111";
    public static final String VALID_STUDENTID_BOB = "e0222222";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String CLASS_GROUP_DESC_AMY = " " + PREFIX_CLASS_GROUP + VALID_CLASS_GROUP_AMY;
    public static final String CLASS_GROUP_DESC_BOB = " " + PREFIX_CLASS_GROUP + VALID_CLASS_GROUP_BOB;
    public static final String STUDENTID_DESC_AMY = " " + PREFIX_ID + VALID_STUDENTID_AMY;
    public static final String STUDENTID_DESC_BOB = " " + PREFIX_ID + VALID_STUDENTID_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String VALID_ATTENDANCE_LIST_SIZE = " " + PREFIX_SIZE + "5";

    public static final String VALID_MARK_ATTENDANCE = " " + PREFIX_MARK + "1";

    public static final String VALID_UNMARK_ATTENDANCE = " " + PREFIX_MARK + "0";

    public static final String VALID_LESSON_ONE = " " + PREFIX_LESSON + "1";
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_STUDENTID_DESC = " " + PREFIX_ID + "ee19012"; // format e0xxxxxx not followed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    public static final EditTaskCommand.EditTaskDescriptor DESC_TODO;
    public static final EditTaskCommand.EditTaskDescriptor DESC_DEADLINE;
    public static final EditTaskCommand.EditTaskDescriptor DESC_ASSIGNMENT;

    public static final String VALID_TASK_TITLE = "Grade assignments";
    public static final String VALID_TASK_DESCRIPTION = "Complete by tonight";

    public static final String VALID_DEADLINE_DATE = "2022-09-09";
    public static final String VALID_ASSIGNMENT_STUDENTS = "Adam, Ben, Charles";
    public static final List<String> VALID_ASSIGNMENT_STUDENTS_LIST = Arrays.asList("Adam", "Ben", "Charles");
    public static final String VALID_ASSIGNMENT_STUDENT_ADAM = "Adam";

    public static final String INVALID_TASK_TITLE = "";
    public static final String INVALID_TASK_DESCRIPTION = "";
    public static final String INVALID_DEADLINE_DATE = "09-09-2002";

    public static final String TASK_TITLE_DESC = " " + PREFIX_TASK_TITLE + VALID_TASK_TITLE;
    public static final String TASK_DESCRIPTION_DESC = " " + PREFIX_TASK_DESCRIPTION + VALID_TASK_DESCRIPTION;
    public static final String DEADLINE_DATE_DESC = " " + PREFIX_DEADLINE_DATE + VALID_DEADLINE_DATE;
    public static final String ASSIGNMENT_ADD_STUDENTS_DESC =
            " " + PREFIX_ASSIGNMENT_ADD_STUDENTS + VALID_ASSIGNMENT_STUDENTS;
    public static final String ASSIGNMENT_DELETE_STUDENTS_DESC =
            " " + PREFIX_ASSIGNMENT_DELETE_STUDENTS + VALID_ASSIGNMENT_STUDENT_ADAM;


    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withClassGroup(VALID_CLASS_GROUP_AMY)
                .withStudentId(VALID_STUDENTID_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withClassGroup(VALID_CLASS_GROUP_BOB)
                .withStudentId(VALID_STUDENTID_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_TODO = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).build();
        DESC_DEADLINE = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).withDate(VALID_DEADLINE_DATE).build();
        DESC_ASSIGNMENT = new EditTaskDescriptorBuilder().withTitle(VALID_TASK_TITLE)
                .withDescription(VALID_TASK_DESCRIPTION).withAddStudents(VALID_ASSIGNMENT_STUDENTS).build();
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
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new StudentContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
