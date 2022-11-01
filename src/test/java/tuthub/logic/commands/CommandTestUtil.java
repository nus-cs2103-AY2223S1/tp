package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tuthub.logic.parser.CliSyntax.PREFIX_MODULE;
import static tuthub.logic.parser.CliSyntax.PREFIX_NAME;
import static tuthub.logic.parser.CliSyntax.PREFIX_PHONE;
import static tuthub.logic.parser.CliSyntax.PREFIX_RATING;
import static tuthub.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tuthub.logic.parser.CliSyntax.PREFIX_TAG;
import static tuthub.logic.parser.CliSyntax.PREFIX_TEACHINGNOMINATION;
import static tuthub.logic.parser.CliSyntax.PREFIX_YEAR;
import static tuthub.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.Tuthub;
import tuthub.model.tutor.NameContainsKeywordsPredicate;
import tuthub.model.tutor.Tutor;
import tuthub.testutil.EditTutorDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "e1234567";
    public static final String VALID_EMAIL_BOB = "e7654321";
    public static final String VALID_MODULE_AMY = "CS2103T";
    public static final String VALID_MODULE_BOB = "CS2100";
    public static final String VALID_MODULE_BOB2 = "CS2105";
    public static final String VALID_YEAR_AMY = "3";
    public static final String VALID_YEAR_BOB = "4";
    public static final String VALID_STUDENTID_AMY = "A1234567X";
    public static final String VALID_STUDENTID_BOB = "A9876543Y";
    public static final String VALID_TEACHINGNOMINATION_AMY = "1";
    public static final String VALID_TEACHINGNOMINATION_BOB = "2";
    public static final String VALID_RATING_AMY = "4.8";
    public static final String VALID_RATING_BOB = "4.5";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String MODULE_DESC_AMY = " " + PREFIX_MODULE + VALID_MODULE_AMY;
    public static final String MODULE_DESC_BOB = " " + PREFIX_MODULE + VALID_MODULE_BOB;
    public static final String MODULE_DESC_BOB2 = " " + PREFIX_MODULE + VALID_MODULE_BOB2;
    public static final String YEAR_DESC_AMY = " " + PREFIX_YEAR + VALID_YEAR_AMY;
    public static final String YEAR_DESC_BOB = " " + PREFIX_YEAR + VALID_YEAR_BOB;
    public static final String STUDENTID_DESC_AMY = " " + PREFIX_STUDENTID + VALID_STUDENTID_AMY;
    public static final String STUDENTID_DESC_BOB = " " + PREFIX_STUDENTID + VALID_STUDENTID_BOB;
    public static final String TEACHINGNOMINATION_DESC_AMY =
            " " + PREFIX_TEACHINGNOMINATION + VALID_TEACHINGNOMINATION_AMY;
    public static final String TEACHINGNOMINATION_DESC_BOB =
            " " + PREFIX_TEACHINGNOMINATION + VALID_TEACHINGNOMINATION_BOB;
    public static final String RATING_DESC_AMY =
            " " + PREFIX_RATING + VALID_RATING_AMY;
    public static final String RATING_DESC_BOB =
            " " + PREFIX_RATING + VALID_RATING_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_MODULE_DESC = " " + PREFIX_MODULE + "C^1000"; // '^' not allowed in module
    public static final String INVALID_YEAR_DESC = " " + PREFIX_YEAR + "-1"; // negative numbers not allowed in year
    public static final String INVALID_STUDENTID_DESC = " " + PREFIX_STUDENTID + "B1234567X"; // should start with A
    public static final String INVALID_TEACHINGNOMINATION_DESC =
            " " + PREFIX_TEACHINGNOMINATION + "A"; // should be a positive integer including 0
    public static final String INVALID_RATING_DESC =
            " " + PREFIX_RATING + "-1"; // should be a positive decimal
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditTutorDescriptor DESC_AMY;
    public static final EditCommand.EditTutorDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditTutorDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withModule(VALID_MODULE_AMY).withYear(VALID_YEAR_AMY)
                .withStudentId(VALID_STUDENTID_AMY)
                .withTeachingNomination(VALID_TEACHINGNOMINATION_AMY)
                .withRating(VALID_RATING_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withModule(VALID_MODULE_BOB).withYear(VALID_YEAR_BOB)
                .withStudentId(VALID_STUDENTID_BOB)
                .withTeachingNomination(VALID_TEACHINGNOMINATION_BOB)
                .withRating(VALID_RATING_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * that takes a string {@code expectedMessage} and boolean {@code isView}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel, boolean isView) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, isView);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - tuthub, filtered tutor list and selected tutor in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Tuthub expectedTuthub = new Tuthub(actualModel.getTuthub());
        List<Tutor> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTutorList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTuthub, actualModel.getTuthub());
        assertEquals(expectedFilteredList, actualModel.getFilteredTutorList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the tutor at the given {@code targetIndex} in the
     * {@code model}'s tuthub.
     */
    public static void showTutorAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTutorList().size());

        Tutor tutor = model.getFilteredTutorList().get(targetIndex.getZeroBased());
        final String[] splitName = tutor.getName().fullName.split("\\s+");
        model.updateFilteredTutorList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTutorList().size());
    }

}
