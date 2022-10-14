package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.student.EditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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


    public static final String VALID_NAME_TUT1 = "F01";
    public static final String VALID_NAME_TUT2 = "F02";
    public static final String VALID_MODULE_TUT1 = "CS2103T";
    public static final String VALID_MODULE_TUT2 = "CS2101";
    public static final String VALID_VENUE_TUT1 = "COM1-0203";
    public static final String VALID_VENUE_TUT2 = "I3";
    public static final String VALID_TIMESLOT_TUT1 = "10:00-12:00";
    public static final String VALID_TIMESLOT_TUT2 = "14:00-15:00";

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

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ID_DESC = " " + PREFIX_ID; // empty string not allowed for ID
    public static final String INVALID_ATTENDANCE_DESC = " "
            + PREFIX_ATTENDANCE; // empty string not allowed for attendance
    public static final String INVALID_PARTICIPATION_DESC = " "
            + PREFIX_PARTICIPATION; // empty string not allowed for participation
    public static final String INVALID_GRADE_DESC = "Z" + PREFIX_GRADE; // only A, B, C, D, F allowed for grade
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

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
