package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_CRITERIA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.reminder.EditReminderCommand;
import seedu.address.logic.commands.reminder.SortReminderCommand;
import seedu.address.logic.commands.student.EditStudentCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelType;
import seedu.address.model.student.Student;
import seedu.address.model.student.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditReminderDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    // Students
    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ID_AMY = "A0000000A";
    public static final String VALID_ID_BOB = "A0000000B";
    public static final String VALID_TELEGRAM_AMY = "amybee";
    public static final String VALID_TELEGRAM_BOB = "bobchoo";
    public static final String VALID_ATTENDANCE_AMY = "0";
    public static final String VALID_ATTENDANCE_BOB = "0";
    public static final String VALID_PARTICIPATION_AMY = "0";
    public static final String VALID_PARTICIPATION_BOB = "0";
    public static final String VALID_GRADE_AMY = "PENDING...";
    public static final String VALID_GRADE_BOB = "PENDING...";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    // Tutorial
    public static final String VALID_NAME_TUT1 = "F01";
    public static final String VALID_NAME_TUT2 = "F02";
    public static final String VALID_MODULE_TUT1 = "CS2103T";
    public static final String VALID_MODULE_TUT2 = "CS2101";
    public static final String VALID_VENUE_TUT1 = "COM1-0203";
    public static final String VALID_VENUE_TUT2 = "I3";
    public static final String VALID_TIMESLOT_TUT1_START = "10:00";
    public static final String VALID_TIMESLOT_TUT1_END = "15:00";
    public static final String VALID_TIMESLOT_TUT2_START = "10:00";
    public static final String VALID_TIMESLOT_TUT2_END = "15:00";

    // Consultation
    public static final String VALID_NAME_CONSULT1 = "John";
    public static final String VALID_NAME_CONSULT2 = "Anna";
    public static final String VALID_MODULE_CONSULT1 = "CS2103T";
    public static final String VALID_MODULE_CONSULT2 = "CS2101";
    public static final String VALID_VENUE_CONSULT1 = "COM2-0105";
    public static final String VALID_VENUE_CONSULT2 = "LT27";
    public static final String VALID_TIMESLOT_CONSULT1_START = "2023-10-23 14:00";
    public static final String VALID_TIMESLOT_CONSULT1_END = "2023-10-23 16:00";
    public static final String VALID_TIMESLOT_CONSULT2_START = "2023-10-24 17:00";
    public static final String VALID_TIMESLOT_CONSULT2_END = "2023-10-24 18:00";
    public static final String VALID_DESCRIPTION_CONSULT1 = "Review past year paper";
    public static final String VALID_DESCRIPTION_CONSULT2 = "Consult about product demo and pitch";

    // Reminder
    public static final String VALID_NAME_REMINDER1 = "Set HW 1";
    public static final String VALID_NAME_REMINDER2 = "Mark Finals";
    public static final String VALID_DATE_REMINDER1 = "2023-07-12";
    public static final String VALID_DATE_REMINDER2 = "2023-11-16";
    public static final String VALID_TIME_REMINDER1 = "19:00";
    public static final String VALID_TIME_REMINDER2 = "20:00";
    public static final String VALID_DEADLINE_REMINDER1 = VALID_DATE_REMINDER1 + " " + VALID_TIME_REMINDER1;
    public static final String VALID_DEADLINE_REMINDER2 = VALID_DATE_REMINDER2 + " " + VALID_TIME_REMINDER2;
    public static final String VALID_PRIORITY_REMINDER1 = "MEDIUM";
    public static final String VALID_PRIORITY_REMINDER2 = "HIGH";
    public static final String VALID_DESCRIPTION_REMINDER1 = "5 questions to set";
    public static final String VALID_DESCRIPTION_REMINDER2 = "300 papers to mark";

    public static final String NAME_DESC_REMINDER1 = " " + PREFIX_NAME + VALID_NAME_REMINDER1;
    public static final String NAME_DESC_REMINDER2 = " " + PREFIX_NAME + VALID_NAME_REMINDER2;
    public static final String DATE_DESC_REMINDER1 = " " + PREFIX_DATE_DAY + VALID_DATE_REMINDER1;
    public static final String DATE_DESC_REMINDER2 = " " + PREFIX_DATE_DAY + VALID_DATE_REMINDER2;
    public static final String TIME_DESC_REMINDER1 = " " + PREFIX_TIME + VALID_TIME_REMINDER1;
    public static final String TIME_DESC_REMINDER2 = " " + PREFIX_TIME + VALID_TIME_REMINDER2;
    public static final String PRIORITY_DESC_REMINDER1 = " " + PREFIX_PRIORITY + VALID_PRIORITY_REMINDER1;
    public static final String PRIORITY_DESC_REMINDER2 = " " + PREFIX_PRIORITY + VALID_PRIORITY_REMINDER2;
    public static final String DESCRIPTION_DESC_REMINDER1 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_REMINDER1;
    public static final String DESCRIPTION_DESC_REMINDER2 = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_REMINDER2;
    public static final String SORTING_DESC_PRIORITY = " " + PREFIX_SORT_CRITERIA
            + SortReminderCommand.CRITERIA_PRIORITY;
    public static final String SORTING_DESC_DEADLINE = " " + PREFIX_SORT_CRITERIA
            + SortReminderCommand.CRITERIA_DEADLINE;

    public static final String INVALID_SORT_DESC = " " + PREFIX_SORT_CRITERIA + "apple";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_TUT1;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_TUT1;
    public static final String TUTORIAL_DESC_AMY = " " + PREFIX_TUTORIAL + VALID_NAME_TUT1;
    public static final String TUTORIAL_DESC_BOB = " " + PREFIX_TUTORIAL + VALID_NAME_TUT1;
    public static final String ATTENDANCE_DESC_AMY = " " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_AMY;
    public static final String ATTENDANCE_DESC_BOB = " " + PREFIX_ATTENDANCE + VALID_ATTENDANCE_BOB;
    public static final String PARTICIPATION_DESC_AMY = " " + PREFIX_PARTICIPATION + VALID_PARTICIPATION_AMY;
    public static final String PARTICIPATION_DESC_BOB = " " + PREFIX_PARTICIPATION + VALID_PARTICIPATION_BOB;
    public static final String GRADE_DESC_AMY = " " + PREFIX_GRADE + VALID_GRADE_AMY;
    public static final String GRADE_DESC_BOB = " " + PREFIX_GRADE + VALID_GRADE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    //Model type
    public static final String INVALID_FIELD = " " + PREFIX_FIELD + "invalid";
    public static final String TYPE_STUDENT = " " + PREFIX_FIELD + "student";
    public static final String TYPE_TUTORIAL = " " + PREFIX_FIELD + "tutorial";
    public static final String TYPE_CONSULTATION = " " + PREFIX_FIELD + "consultation";

    // invalid field utils
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ID_DESC = " " + PREFIX_ID; // empty string not allowed for ID
    public static final String INVALID_ATTENDANCE_DESC = " "
            + PREFIX_ATTENDANCE; // empty string not allowed for attendance
    public static final String INVALID_PARTICIPATION_DESC = " "
            + PREFIX_PARTICIPATION; // empty string not allowed for participation
    public static final String INVALID_GRADE_DESC = " " + PREFIX_GRADE + "Z"; // only A, B, C, D, F allowed for grade
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_PRIORITY = " " + PREFIX_PRIORITY + "AP"; // only "HIGH", "MEDIUM", "LOW" allowed
    public static final String INVALID_DATE_FORMAT_DESC = " " + PREFIX_DATE_DAY + "2000-03-";
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE_DAY + "2000-03-33";
    public static final String INVALID_DAY_DESC = " " + PREFIX_DATE_DAY + "9";
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "13:70";
    public static final String INVALID_TIMESLOT_DESC = " " + PREFIX_TIME + "12:00-11:40";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditStudentCommand.EditPersonDescriptor DESC_AMY;
    public static final EditStudentCommand.EditPersonDescriptor DESC_BOB;
    public static final EditReminderCommand.EditReminderDescriptor DESC_REMINDER1;
    public static final EditReminderCommand.EditReminderDescriptor DESC_REMINDER2;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withId(VALID_ID_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withModule(VALID_MODULE_TUT1).withTutorial(VALID_NAME_TUT1)
                .withAttendance(VALID_ATTENDANCE_AMY)
                .withParticipation(VALID_PARTICIPATION_AMY).withGrade(VALID_GRADE_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withModule(VALID_MODULE_TUT1).withTutorial(VALID_NAME_TUT1)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withAttendance(VALID_ATTENDANCE_BOB)
                .withParticipation(VALID_PARTICIPATION_BOB).withGrade(VALID_GRADE_BOB)
                .build();

        DESC_REMINDER1 = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER1)
                .withDeadline(VALID_DEADLINE_REMINDER1)
                .withPriority(VALID_PRIORITY_REMINDER1)
                .withDescription(VALID_DESCRIPTION_REMINDER1)
                .build();
        DESC_REMINDER2 = new EditReminderDescriptorBuilder().withName(VALID_NAME_REMINDER2)
                .withDeadline(VALID_DEADLINE_REMINDER2)
                .withPriority(VALID_PRIORITY_REMINDER2)
                .withDescription(VALID_DESCRIPTION_REMINDER2)
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
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage} and model type {@code expectedModelType}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            ModelType expectedModelType, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, expectedModelType);
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
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

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

        Student student = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }
}
