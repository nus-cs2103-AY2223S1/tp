package seedu.trackascholar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_MAJOR;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackascholar.logic.parser.CliSyntax.PREFIX_SCHOLARSHIP;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.TrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.testutil.EditApplicantDescriptorBuilder;

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
    public static final String VALID_SCHOLARSHIP_AMY = "Arts scholarship";
    public static final String VALID_SCHOLARSHIP_BOB = "Sports scholarship";
    public static final String VALID_APPLICATION_STATUS_AMY = "pending";
    public static final String VALID_APPLICATION_STATUS_BOB = "accepted";
    public static final String VALID_MAJOR_COMPUTER_SCIENCE = "Computer Science";
    public static final String VALID_MAJOR_MATHEMATICS = "Mathematics";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String SCHOLARSHIP_DESC_AMY = " " + PREFIX_SCHOLARSHIP + VALID_SCHOLARSHIP_AMY;
    public static final String SCHOLARSHIP_DESC_BOB = " " + PREFIX_SCHOLARSHIP + VALID_SCHOLARSHIP_BOB;
    public static final String APPLICATION_STATUS_DESC_AMY = " " + PREFIX_APPLICATION_STATUS
            + VALID_APPLICATION_STATUS_AMY;
    public static final String APPLICATION_STATUS_DESC_BOB = " " + PREFIX_APPLICATION_STATUS
            + VALID_APPLICATION_STATUS_BOB;
    public static final String MAJOR_DESC_MATHEMATICS = " " + PREFIX_MAJOR + VALID_MAJOR_MATHEMATICS;
    public static final String MAJOR_DESC_COMPUTER_SCIENCE = " " + PREFIX_MAJOR + VALID_MAJOR_COMPUTER_SCIENCE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_SCHOLARSHIP_DESC = " "
            + PREFIX_SCHOLARSHIP; // empty string not allowed for scholarships
    public static final String INVALID_APPLICATION_STATUS_DESC = " "
            + PREFIX_APPLICATION_STATUS + "reject"; // wrong status
    public static final String INVALID_MAJOR_DESC = " " + PREFIX_MAJOR + "Mathematics*"; // '*' not allowed in majors

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicantDescriptor DESC_AMY;
    public static final EditCommand.EditApplicantDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withScholarship(VALID_SCHOLARSHIP_AMY)
                .withMajors(VALID_MAJOR_MATHEMATICS).build();
        DESC_BOB = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withScholarship(VALID_SCHOLARSHIP_BOB)
                .withMajors(VALID_MAJOR_COMPUTER_SCIENCE, VALID_MAJOR_MATHEMATICS).build();
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
     * - TrackAScholar, filtered applicant list and selected applicant in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TrackAScholar expectedTrackAScholar = new TrackAScholar(actualModel.getTrackAScholar());
        List<Applicant> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicantList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTrackAScholar, actualModel.getTrackAScholar());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicantList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s TrackAScholar.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicantList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getFullName().split("\\s+");
        model.updateFilteredApplicantList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

}
