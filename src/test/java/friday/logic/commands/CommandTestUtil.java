package friday.logic.commands;

import static friday.logic.parser.CliSyntax.PREFIX_CONSULTATION;
import static friday.logic.parser.CliSyntax.PREFIX_MASTERYCHECK;
import static friday.logic.parser.CliSyntax.PREFIX_NAME;
import static friday.logic.parser.CliSyntax.PREFIX_TAG;
import static friday.logic.parser.CliSyntax.PREFIX_TELEGRAMHANDLE;
import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import friday.commons.core.index.Index;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Friday;
import friday.model.Model;
import friday.model.student.NameContainsKeywordsPredicate;
import friday.model.student.Student;
import friday.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_TELEGRAMHANDLE_AMY = "amy123";
    public static final String VALID_TELEGRAMHANDLE_BOB = "b0b_choo";
    public static final LocalDate VALID_CONSULTATION_AMY = LocalDate.of(2022, 9, 1);
    public static final LocalDate VALID_CONSULTATION_BOB = LocalDate.of(2022, 7, 12);
    public static final LocalDate VALID_MASTERYCHECK_DATE_AMY = LocalDate.of(2022, 11, 2);
    public static final LocalDate VALID_MASTERYCHECK_DATE_BOB = LocalDate.of(2022, 8, 24);
    public static final String VALID_REMARK_AMY = "Like skiing.";
    public static final String VALID_REMARK_BOB = "Favourite pastime: Eating";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_GRADE_SCORE_AMY = "69.99";
    public static final String VALID_GRADE_SCORE_BOB = "90";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TELEGRAMHANDLE_DESC_AMY = " " + PREFIX_TELEGRAMHANDLE + VALID_TELEGRAMHANDLE_AMY;
    public static final String TELEGRAMHANDLE_DESC_BOB = " " + PREFIX_TELEGRAMHANDLE + VALID_TELEGRAMHANDLE_BOB;
    public static final String CONSULTATION_DESC_AMY = " " + PREFIX_CONSULTATION + VALID_CONSULTATION_AMY;
    public static final String CONSULTATION_DESC_BOB = " " + PREFIX_CONSULTATION + VALID_CONSULTATION_BOB;
    public static final String MASTERYCHECK_DESC_AMY = " " + PREFIX_MASTERYCHECK + VALID_MASTERYCHECK_DATE_AMY;
    public static final String MASTERYCHECK_DESC_BOB = " " + PREFIX_MASTERYCHECK + VALID_MASTERYCHECK_DATE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TELEGRAMHANDLE_DESC = " " + PREFIX_TELEGRAMHANDLE
            + "lucy+2"; // '+' not allowed in Telegram handles

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_TELEGRAMHANDLE_AMY).withConsultation(VALID_CONSULTATION_AMY)
                .withMasteryCheck(VALID_MASTERYCHECK_DATE_AMY).withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_TELEGRAMHANDLE_BOB).withConsultation(VALID_CONSULTATION_BOB)
                .withMasteryCheck(VALID_MASTERYCHECK_DATE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
        Friday expectedAddressBook = new Friday(actualModel.getFriday());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getFriday());
        assertEquals(expectedFilteredList, actualModel.getStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getStudentList().size());

        Student student = model.getStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getStudentList().size());
    }

}
