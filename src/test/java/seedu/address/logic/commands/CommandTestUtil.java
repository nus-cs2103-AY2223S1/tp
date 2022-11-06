package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.grade.GradeEditCommand;
import seedu.address.logic.commands.student.StudentEditCommand;
import seedu.address.logic.commands.student.StudentEnrollCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.grade.Grade;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.TutorialGroup;
import seedu.address.model.student.TutorialNameContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.EditDescriptorBuilder;
import seedu.address.testutil.EditGradeDescriptorBuilder;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EnrollStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_TIFFANI = "Tiffani Tiffany";
    public static final String VALID_NAME_DAVE = "Dave Bag";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_TIFFANI = "33333333";
    public static final String VALID_PHONE_DAVE = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_TIFFANI = "tiffani@example.com";
    public static final String VALID_EMAIL_DAVE = "dave@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_ADDRESS_TIFFANI = "Block 123, Tiffani Street 2";
    public static final String VALID_ADDRESS_DAVE = "Block 123, Dave Street 4";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_BESTIE = "bestie";
    public static final String VALID_TUTORIAL_GROUP_AMY = "T01";
    public static final String VALID_TUTORIAL_GROUP_BOB = "T01";
    public static final String VALID_TUTORIAL_GROUP_TIFFANI = "T01";
    public static final String VALID_TUTORIAL_GROUP_DAVE = "T02";
    public static final String VALID_TASK_NAME = "Task 1";
    public static final String VALID_TASK_DESCRIPTION = "DUE TODAY";
    public static final String VALID_TASK_DEADLINE = "10/10/2020";
    public static final Grade VALID_GRADE_STATE_AMY = Grade.UNGRADED;
    public static final Grade VALID_GRADE_STATE_BOB = Grade.GRADED;

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
    public static final String TUTORIAL_GROUP_DESC_AMY = " " + PREFIX_TUTORIAL_GROUP + VALID_TUTORIAL_GROUP_AMY;
    public static final String TUTORIAL_GROUP_DESC_BOB = " " + PREFIX_TUTORIAL_GROUP + VALID_TUTORIAL_GROUP_BOB;
    public static final String TUTORIAL_GROUP_DESC_TIFFANI = " " + PREFIX_TUTORIAL_GROUP + VALID_TUTORIAL_GROUP_TIFFANI;
    public static final String TUTORIAL_GROUP_DESC_DAVE = " " + PREFIX_TUTORIAL_GROUP + VALID_TUTORIAL_GROUP_DAVE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TUTORIAL_GROUP_DESC = " " + PREFIX_TUTORIAL_GROUP + "Tab";
    // alphabets not allowed in tutorial group

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final StudentEditCommand.EditStudentDescriptor DESC_AMY;
    public static final StudentEditCommand.EditStudentDescriptor DESC_BOB;

    public static final StudentEditCommand.EditStudentDescriptor DESC_STUDENT_AMY;
    public static final StudentEditCommand.EditStudentDescriptor DESC_STUDENT_BOB;

    public static final GradeEditCommand.EditGradeDescriptor DESC_GRADE_AMY;
    public static final GradeEditCommand.EditGradeDescriptor DESC_GRADE_BOB;

    public static final StudentEnrollCommand.EditStudentDescriptor DESC_STUDENT_AMY_WITH_TUTORIAL;
    public static final StudentEnrollCommand.EditStudentDescriptor DESC_STUDENT_BOB_WITH_TUTORIAL;

    static {
        DESC_AMY = new EditDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_STUDENT_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTutorialGroup(VALID_TUTORIAL_GROUP_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_STUDENT_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTutorialGroup(VALID_TUTORIAL_GROUP_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_STUDENT_BOB_WITH_TUTORIAL = new EnrollStudentDescriptorBuilder()
                .withTutorialGroup(VALID_TUTORIAL_GROUP_BOB)
                .build();
        DESC_STUDENT_AMY_WITH_TUTORIAL = new EnrollStudentDescriptorBuilder()
                .withTutorialGroup(VALID_TUTORIAL_GROUP_AMY)
                .build();
        DESC_GRADE_AMY = new EditGradeDescriptorBuilder().withGradeState(VALID_GRADE_STATE_AMY).build();
        DESC_GRADE_BOB = new EditGradeDescriptorBuilder().withGradeState(VALID_GRADE_STATE_BOB).build();
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
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTutorialGroupAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTutorialGroupList().size());

        TutorialGroup tutorialGroup = model.getFilteredTutorialGroupList().get(targetIndex.getZeroBased());
        final String[] splitName = tutorialGroup.toString().split("\\s+");
        model.updateFilteredTutorialGroupList(new TutorialNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredTutorialGroupList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.toString().split("\\s+");
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
        assertEquals(1, model.getFilteredTaskList().size());
    }
}
