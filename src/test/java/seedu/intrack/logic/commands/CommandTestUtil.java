package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_WEBSITE;
import static seedu.intrack.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.NameContainsKeywordsPredicate;
import seedu.intrack.model.internship.Task;
import seedu.intrack.testutil.EditInternshipDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AAPL = "Apple";
    public static final String VALID_NAME_MSFT = "Microsoft";
    public static final String VALID_POSITION_AAPL = "Software Engineer";
    public static final String VALID_POSITION_MSFT = "Frontend Developer";
    public static final String VALID_STATUS_AAPL = "Offered";
    public static final String VALID_STATUS_MSFT = "Progress";
    public static final String VALID_PHONE_AAPL = "11111111";
    public static final String VALID_PHONE_MSFT = "22222222";
    public static final String VALID_EMAIL_AAPL = "hr@apple.com";
    public static final String VALID_EMAIL_MSFT = "hr@microsoft.com";
    public static final String VALID_WEBSITE_AAPL = "https://www.apple.com/careers/sg/";
    public static final String VALID_WEBSITE_MSFT = "https://careers.microsoft.com/us/en";
    public static final String VALID_TASK_AAPL = "Application submitted /at "
            + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(Task.FORMATTER);
    public static final String VALID_TASK_MSFT = "Application submitted /at "
            + LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).format(Task.FORMATTER);
    public static final String VALID_TAG_REMOTE = "Remote";
    public static final String VALID_TAG_URGENT = "Urgent";
    public static final String VALID_REMARK_AAPL = "Revise Trees";
    public static final String VALID_REMARK_MSFT = "Revise Graphs";

    public static final String NAME_DESC_AAPL = " " + PREFIX_NAME + VALID_NAME_AAPL;
    public static final String NAME_DESC_MSFT = " " + PREFIX_NAME + VALID_NAME_MSFT;
    public static final String POSITION_DESC_AAPL = " " + PREFIX_POSITION + VALID_POSITION_AAPL;
    public static final String POSITION_DESC_MSFT = " " + PREFIX_POSITION + VALID_POSITION_MSFT;
    public static final String PHONE_DESC_AAPL = " " + PREFIX_PHONE + VALID_PHONE_AAPL;
    public static final String PHONE_DESC_MSFT = " " + PREFIX_PHONE + VALID_PHONE_MSFT;
    public static final String EMAIL_DESC_AAPL = " " + PREFIX_EMAIL + VALID_EMAIL_AAPL;
    public static final String EMAIL_DESC_MSFT = " " + PREFIX_EMAIL + VALID_EMAIL_MSFT;
    public static final String WEBSITE_DESC_AAPL = " " + PREFIX_WEBSITE + VALID_WEBSITE_AAPL;
    public static final String WEBSITE_DESC_MSFT = " " + PREFIX_WEBSITE + VALID_WEBSITE_MSFT;
    public static final String TAG_DESC_REMOTE = " " + PREFIX_TAG + VALID_TAG_REMOTE;
    public static final String TAG_DESC_URGENT = " " + PREFIX_TAG + VALID_TAG_URGENT;
    public static final String REMARK_DESC_AAPL = " " + PREFIX_REMARK + VALID_REMARK_AAPL;
    public static final String REMARK_DESC_MSFT = " " + PREFIX_REMARK + VALID_REMARK_MSFT;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Apple&"; // '&' not allowed in names
    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION; // empty string not allowed in positions
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "11111111a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "hr!apple"; // missing '@' symbol
    public static final String INVALID_WEBSITE_DESC = " " + PREFIX_WEBSITE; // empty string not allowed for websites
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "remote*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditInternshipDescriptor DESC_AAPL;
    public static final EditCommand.EditInternshipDescriptor DESC_MSFT;

    static {
        DESC_AAPL = new EditInternshipDescriptorBuilder().withName(VALID_NAME_AAPL)
                .withPhone(VALID_PHONE_AAPL).withEmail(VALID_EMAIL_AAPL).withWebsite(VALID_WEBSITE_AAPL)
                .withTags(VALID_TAG_REMOTE).build();
        DESC_MSFT = new EditInternshipDescriptorBuilder().withName(VALID_NAME_MSFT)
                .withPhone(VALID_PHONE_MSFT).withEmail(VALID_EMAIL_MSFT).withWebsite(VALID_WEBSITE_MSFT)
                .withTags(VALID_TAG_REMOTE, VALID_TAG_URGENT).build();
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
     * - the tracker, filtered internship list and selected internship in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InTrack expectedInTrack = new InTrack(actualModel.getInTrack());
        List<Internship> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInternshipList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInTrack, actualModel.getInTrack());
        assertEquals(expectedFilteredList, actualModel.getFilteredInternshipList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the internship at the given {@code targetIndex} in the
     * {@code model}'s internship tracker.
     */
    public static void showInternshipAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInternshipList().size());

        Internship internship = model.getFilteredInternshipList().get(targetIndex.getZeroBased());
        final String[] splitName = internship.getName().fullName.split("\\s+");
        model.updateFilteredInternshipList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredInternshipList().size());
    }

}
