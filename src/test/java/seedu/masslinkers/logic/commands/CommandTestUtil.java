package seedu.masslinkers.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.masslinkers.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.MassLinkers;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.DetailsContainsKeywordsPredicate;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.testutil.EditStudentDescriptorBuilder;

//@@author sltsheryl
//@@author chm252
//@@author jonasgwt
/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "+11111111"; // this is a valid number now
    public static final String VALID_PHONE_BOB = "12345678"; // valid
    public static final String VALID_PHONE_BOB_WARN = "this is a phone"; // valid but warn
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_TELEGRAM_AMY = "amy123";
    public static final String VALID_TELEGRAM_BOB = "bobby";
    public static final String VALID_GITHUB_AMY = "amybee";
    public static final String VALID_GITHUB_BOB = "bobbychoo";
    public static final String VALID_INTEREST_SWE = "SWE";
    public static final String VALID_INTEREST_AI = "AI";
    public static final String VALID_MOD_CS2100 = "CS2100";
    public static final String VALID_MOD_CS2103 = "CS2103";
    public static final String INVALID_MOD_CS2103 = "#CS2103";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String GITHUB_DESC_AMY = " " + PREFIX_GITHUB + VALID_GITHUB_AMY;
    public static final String GITHUB_DESC_BOB = " " + PREFIX_GITHUB + VALID_GITHUB_BOB;
    public static final String INTEREST_DESC_AI = " " + PREFIX_INTEREST + VALID_INTEREST_AI;
    public static final String INTEREST_DESC_SWE = " " + PREFIX_INTEREST + VALID_INTEREST_SWE;
    public static final String MOD_DESC_CS2100 = " " + PREFIX_MOD + VALID_MOD_CS2100;
    public static final String MOD_DESC_CS2103 = " " + PREFIX_MOD + VALID_MOD_CS2103;
    public static final String INVALID_MOD_DESC_CS2103 = " " + PREFIX_MOD + INVALID_MOD_CS2103;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "  "; // Blank phone numbers are not allowed
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_GITHUB_DESC = " " + PREFIX_GITHUB;
    // empty string not allowed for GitHub usernames
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM; // empty string not allowed for telegram
    public static final String INVALID_INTEREST_DESC = " " + PREFIX_INTEREST + "AI*"; //'*' not allowed in interests

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
                .withGitHub(VALID_GITHUB_AMY).withInterests(VALID_INTEREST_AI).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withGitHub(VALID_GITHUB_BOB).withInterests(VALID_INTEREST_SWE, VALID_INTEREST_AI).build();
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
                                            Model expectedModel, boolean showHelp, boolean exit,
                                            boolean resetModPanel, boolean updateStudentPanel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, showHelp,
                exit, resetModPanel, updateStudentPanel);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the mass linkers, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        MassLinkers expectedMassLinkers = new MassLinkers(actualModel.getMassLinkers());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedMassLinkers, actualModel.getMassLinkers());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s mass linkers.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new DetailsContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
