package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_PLAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.RemovePersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final Index VALID_INDEX = Index.fromZeroBased(0);
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_HOMEWORK_AMY = "English editing worksheet";
    public static final String VALID_HOMEWORK_BOB = "Chemistry worksheet 14";
    public static final String VALID_ATTENDANCE_AMY = "2022-07-07";
    public static final String VALID_ATTENDANCE_BOB = "2022-08-08";
    public static final String VALID_SESSION_AMY = "Tue 09:00";
    public static final String VALID_SESSION_BOB = "Mon 08:30";
    public static final String VALID_GRADE_PROGRESS_AMY = "Math: A";
    public static final String VALID_GRADE_PROGRESS_BOB = "Math: B";
    public static final String VALID_LESSON_PLAN_AMY = "Amy's course basics";
    public static final String VALID_LESSON_PLAN_BOB = "Bob's course basics";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String LESSON_PLAN_DESC_AMY = " " + PREFIX_LESSON_PLAN + VALID_LESSON_PLAN_AMY;
    public static final String LESSON_PLAN_DESC_BOB = " " + PREFIX_LESSON_PLAN + VALID_LESSON_PLAN_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    //used in remove
    public static final String HOMEWORK_DESC_AMY_REM = " " + PREFIX_HOMEWORK + "1";
    public static final String GRADE_PROGRESS_DESC_AMY_REM = " " + PREFIX_GRADE_PROGRESS + "1";
    public static final String ATTENDANCE_DESC_AMY_REM = " " + PREFIX_ATTENDANCE + "1";
    public static final String SESSION_DESC_AMY_REM = " " + PREFIX_SESSION + "1";
    public static final String FIRST_PERSON_NAME = "alice Pauline";
    public static final String FIRST_PERSON_PHONE = "94351253";
    public static final String FIRST_PERSON_LESSON_PLAN = "Algorithms";
    public static final String FIRST_PERSON_TAGS = "friends";

    // used in edit, hence index is included
    public static final String HOMEWORK_DESC_AMY = " " + PREFIX_HOMEWORK + "1 " + VALID_HOMEWORK_AMY;
    public static final String GRADE_PROGRESS_DESC_AMY = " " + PREFIX_GRADE_PROGRESS
            + "1 " + VALID_GRADE_PROGRESS_AMY;
    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_ATTENDANCE + "1 " + VALID_ATTENDANCE_AMY;
    public static final String SESSION_DESC_AMY = " " + PREFIX_SESSION + "1 " + VALID_SESSION_AMY;

    public static final String INVALID_ATTENDANCE_DESC = " "
            + PREFIX_ATTENDANCE + "1 5 June 2022"; // date format not uuuu-mm-dd
    public static final String INVALID_SESSION_DESC =
            " " + PREFIX_SESSION + "1 Monday 09:00"; // "Monday" should be "Mon"

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final RemoveCommand.RemovePersonDescriptor DESC_AMY_REM;
    public static final RemoveCommand.RemovePersonDescriptor DESC_BOB_REM;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withLessonPlan(VALID_LESSON_PLAN_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withLessonPlan(VALID_LESSON_PLAN_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    static {
        DESC_AMY_REM = new RemovePersonDescriptorBuilder().withSession(VALID_SESSION_AMY)
                .withSessionIndex(VALID_INDEX).withHomework(VALID_HOMEWORK_AMY)
                .withHomeworkIndex(VALID_INDEX).withGradeProgress(VALID_GRADE_PROGRESS_AMY)
                .withGradeProgressIndex(VALID_INDEX).withAttendance(VALID_ATTENDANCE_AMY)
                .withAttendanceIndex(VALID_INDEX).build();
        DESC_BOB_REM = new RemovePersonDescriptorBuilder().withSession(VALID_SESSION_BOB)
                .withSessionIndex(VALID_INDEX).withHomework(VALID_HOMEWORK_BOB)
                .withHomeworkIndex(VALID_INDEX).withGradeProgress(VALID_GRADE_PROGRESS_BOB)
                .withGradeProgressIndex(VALID_INDEX).withAttendance(VALID_ATTENDANCE_BOB)
                .withAttendanceIndex(VALID_INDEX).build();
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
     * Sets model to full view mode with the given name in focus.
     */
    public static void setModelFullView(Model model, String name) {
        String[] newNameKeywords = {name};
        model.updateFilteredPersonList(new NameIsKeywordsPredicate(Arrays.asList(newNameKeywords)));
    }

}
