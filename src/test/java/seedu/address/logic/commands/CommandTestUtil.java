package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDITIONAL_NOTES_APPEND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_PAID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOK_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATES_PER_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TeachersPet;
import seedu.address.model.student.Student;
import seedu.address.model.student.predicate.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "81111111";
    public static final String VALID_PHONE_BOB = "92222222";
    public static final String VALID_NOK_PHONE_AMY = "99998877";
    public static final String VALID_NOK_PHONE_BOB = "98341256";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_CLASS_AMY = "2022-09-10 0000-1200";
    public static final String VALID_CLASS_BOB = "2022-09-10 0000-1200";
    public static final Integer VALID_MONEY_OWED_AMY = 10;
    public static final Integer VALID_MONEY_OWED_BOB = 20;
    public static final Integer VALID_MONEY_PAID_AMY = 234;
    public static final Integer VALID_MONEY_PAID_BOB = 345;
    public static final Integer VALID_RATES_PER_CLASS_AMY = 50;
    public static final Integer VALID_RATES_PER_CLASS_BOB = 50;
    public static final String VALID_ADDITIONAL_NOTES_AMY = "alive student";
    public static final String VALID_DIFFERENT_ADDITIONAL_NOTES_AMY = "dead student";
    public static final String VALID_ADDITIONAL_NOTES_BOB = "alive student";
    public static final String VALID_TAG_INTERMEDIATE = "intermediate";
    public static final String VALID_TAG_BEGINNER = "beginner";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String NOK_PHONE_DESC_AMY = " " + PREFIX_NOK_PHONE + VALID_NOK_PHONE_AMY;
    public static final String NOK_PHONE_DESC_BOB = " " + PREFIX_NOK_PHONE + VALID_NOK_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String CLASS_DESC_AMY = " " + PREFIX_CLASS_DATE_TIME + VALID_CLASS_AMY;
    public static final String CLASS_DESC_BOB = " " + PREFIX_CLASS_DATE_TIME + VALID_CLASS_BOB;
    public static final String MONEY_OWED_DESC_AMY = " " + PREFIX_MONEY_OWED + VALID_MONEY_OWED_AMY.toString();
    public static final String MONEY_OWED_DESC_BOB = " " + PREFIX_MONEY_OWED + VALID_MONEY_OWED_BOB.toString();
    public static final String MONEY_PAID_DESC_AMY = " " + PREFIX_MONEY_PAID + VALID_MONEY_PAID_AMY.toString();
    public static final String MONEY_PAID_DESC_BOB = " " + PREFIX_MONEY_PAID + VALID_MONEY_PAID_BOB.toString();
    public static final String RATES_PER_CLASS_DESC_AMY =
            " " + PREFIX_RATES_PER_CLASS + VALID_RATES_PER_CLASS_AMY.toString();
    public static final String RATES_PER_CLASS_DESC_BOB =
            " " + PREFIX_RATES_PER_CLASS + VALID_RATES_PER_CLASS_BOB.toString();
    public static final String ADDITIONAL_NOTE_DESC_AMY =
            " " + PREFIX_ADDITIONAL_NOTES + VALID_ADDITIONAL_NOTES_AMY.toString();
    public static final String ADDITIONAL_NOTE_DESC_BOB =
            " " + PREFIX_ADDITIONAL_NOTES + VALID_ADDITIONAL_NOTES_BOB.toString();
    public static final String APPENDED_ADDITIONAL_NOTE_DESC_BOB =
            " " + PREFIX_ADDITIONAL_NOTES_APPEND + VALID_ADDITIONAL_NOTES_BOB.toString();
    public static final String TAG_DESC_BEGINNER = " " + PREFIX_TAG + VALID_TAG_BEGINNER;
    public static final String TAG_DESC_INTERMEDIATE = " " + PREFIX_TAG + VALID_TAG_INTERMEDIATE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_NOK_PHONE_DESC = " " + PREFIX_NOK_PHONE + "9b11"; // 'b' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_CLASS_DATE_TIME_DESC =
            " " + PREFIX_CLASS_DATE_TIME + "2134"; // '2134' not valid class format
    public static final String INVALID_MONEY_OWED_DESC = " " + PREFIX_MONEY_OWED + "-1"; // money cannot be negative
    public static final String INVALID_MONEY_PAID_DESC = " " + PREFIX_MONEY_PAID + "-10"; // money cannot be negative
    public static final String INVALID_RATES_PER_CLASS_DESC =
            " " + PREFIX_RATES_PER_CLASS + "-200"; // money cannot be negative
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withNokPhone(VALID_NOK_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY).withMoneyOwed(VALID_MONEY_OWED_AMY)
                .withMoneyPaid(VALID_MONEY_PAID_AMY).withRatesPerClass(VALID_RATES_PER_CLASS_AMY)
                .withAdditionalNotes(VALID_ADDITIONAL_NOTES_AMY)
                .withTags(VALID_TAG_INTERMEDIATE, VALID_TAG_BEGINNER).build();
    }

    static {
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withNokPhone(VALID_NOK_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withMoneyOwed(VALID_MONEY_OWED_BOB)
                .withMoneyPaid(VALID_MONEY_PAID_BOB).withRatesPerClass(VALID_RATES_PER_CLASS_BOB)
                .withAdditionalNotes(VALID_ADDITIONAL_NOTES_BOB)
                .withTags(VALID_TAG_INTERMEDIATE, VALID_TAG_BEGINNER).build();
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
        TeachersPet expectedTeachersPet = new TeachersPet(actualModel.getTeachersPet());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTeachersPet, actualModel.getTeachersPet());
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
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
