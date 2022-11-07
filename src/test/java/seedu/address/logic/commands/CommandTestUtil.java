package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.question.DescriptionContainsKeywordsPredicate;
import seedu.address.model.question.Question;
import seedu.address.model.student.StuNameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tutorial.TutNameContainsKeywordsPredicate;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_DESCRIPTION_Q1 = "What's the difference between composition and aggregation?";
    public static final String VALID_DESCRIPTION_Q2 = "Why are association roles used?";


    public static final String VALID_GROUP_TUTORIAL1 = "T08";
    public static final String VALID_GROUP_TUTURIAL2 = "T09";
    public static final String VALID_CONTENT_TUTORIAL1 = "UML Diagrams";
    public static final String VALID_CONTENT_TUTORIAL2 = "Developer Guide";
    public static final String VALID_TIME_TUTORIAL1 = "2022-10-01 0800";
    public static final String VALID_TIME_TUTORIAL2 = "2022-10-01 1600";
    public static final String VALID_TELEGRAM_AMY = "@amy";
    public static final String VALID_TELEGRAM_BOB = "@bob_choo";

    public static final String VALID_RESPONSE_AMY = "2";
    public static final String VALID_RESPONSE_BOB = "0";
    public static final String VALID_ATTENDANCE_AMY = "1";
    public static final String VALID_ATTENDANCE_BOB = "0";
    public static final String VALID_NAME_ALICE = "Alice Pauline";
    public static final String VALID_TELEGRAM_ALICE = "@AlicePauline";
    public static final String VALID_EMAIL_ALICE = "alice@example.com";
    public static final String VALID_RESPONSE_ALICE = "2";
    public static final String VALID_ATTENDANCE_ALICE = "1";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String DESCRIPTION_Q1 = " " + VALID_DESCRIPTION_Q1;
    public static final String DESCRIPTION_Q2 = " " + VALID_DESCRIPTION_Q2;

    public static final String GROUP_TUTORIAL1 = " " + PREFIX_GROUP + VALID_GROUP_TUTORIAL1;
    public static final String CONTENT_TUTORIAL1 = " " + PREFIX_CONTENT + VALID_CONTENT_TUTORIAL1;
    public static final String TIME_TUTORIAL1 = " " + PREFIX_TIME + VALID_TIME_TUTORIAL1;

    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_AMY;
    public static final String ATTENDANCE_DESC_BOB = " " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_BOB;
    public static final String GROUP_DESC_TUTORIAL1 = " " + PREFIX_GROUP + VALID_GROUP_TUTORIAL1;
    public static final String GROUP_DESC_TUTORIAL2 = " " + PREFIX_GROUP + VALID_GROUP_TUTURIAL2;
    public static final String CONTENT_DESC_TUTORIAL1 = " " + PREFIX_CONTENT + VALID_CONTENT_TUTORIAL1;
    public static final String CONTENT_DESC_TUTORIAL2 = " " + PREFIX_CONTENT + VALID_CONTENT_TUTORIAL2;
    public static final String TIME_DESC_TUTORIAL1 = " " + PREFIX_TIME + VALID_TIME_TUTORIAL1;
    public static final String TIME_DESC_TUTORIAL2 = " " + PREFIX_TIME + VALID_TIME_TUTORIAL2;


    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "James&"; // Missing '@' before handle
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ATTENDANCE_DESC = " " + PREFIX_ATTENDANCE + "a"; // 'a' not allowed in attendance
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "2022"; // the time format is not correct
    public static final String INVALID_GROUP_DESC = " " + PREFIX_GROUP + "T08*"; // contains non-alphanumeric characters
    public static final String INVALID_CONTENT_DESC = " " + PREFIX_CONTENT + "uml*";
    // contains non-alphanumeric characters
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStuCommand.EditStudentDescriptor DESC_STU_AMY;
    public static final EditStuCommand.EditStudentDescriptor DESC_STU_BOB;

    static {
        DESC_STU_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegram(VALID_TELEGRAM_AMY).withEmail(VALID_EMAIL_AMY)
                .withAttendance(VALID_ATTENDANCE_AMY).build();
        DESC_STU_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB)
                .withAttendance(VALID_ATTENDANCE_BOB).build();
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
     * {@code model}'s student list.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new StuNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tutorial at the given {@code targetIndex} in the
     * {@code model}'s tutorial list.
     */
    public static void showTutorialAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTutorialList().size());

        Tutorial tutorial = model.getFilteredTutorialList().get(targetIndex.getZeroBased());
        final String[] splitContent = tutorial.getContent().content.split("\\s+");
        model.updateFilteredTutorialList(new TutNameContainsKeywordsPredicate(Arrays.asList(splitContent[0])));

        assertEquals(1, model.getFilteredTutorialList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the question at the given {@code targetIndex} in the
     * {@code model}'s question list.
     */
    public static void showQuestionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredQuestionList().size());

        Question question = model.getFilteredQuestionList().get(targetIndex.getZeroBased());
        final String[] splitName = question.getDescription().descriptionString.split("\\s+");
        model.updateFilteredQuestionList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitName)));

        assertEquals(1, model.getFilteredQuestionList().size());
    }


}
