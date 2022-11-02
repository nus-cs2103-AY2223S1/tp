package hobbylist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.logic.parser.CliSyntax;
import hobbylist.model.HobbyList;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.NameContainsKeywordsPredicate;
import hobbylist.testutil.Assert;
import hobbylist.testutil.EditActivityDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_ANIME = "Anime";
    public static final String VALID_NAME_BOXING = "Boxing";
    public static final String VALID_DESCRIPTION_ANIME = "I'm tired";
    public static final String VALID_DESCRIPTION_BOXING = "When will this be done";
    public static final String VALID_TAG_ENTERTAINMENT = "entertainment";
    public static final String VALID_TAG_EXERCISE = "exercise";

    public static final String NAME_DESC_ANIME = " " + CliSyntax.PREFIX_NAME + VALID_NAME_ANIME;
    public static final String NAME_DESC_BOXING = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOXING;
    public static final String DESCRIPTION_DESC_ANIME = " " + CliSyntax.PREFIX_DESCRIPTION + VALID_DESCRIPTION_ANIME;
    public static final String DESCRIPTION_DESC_BOXING = " " + CliSyntax.PREFIX_DESCRIPTION + VALID_DESCRIPTION_BOXING;
    public static final String TAG_DESC_ENTERTAINMENT = " " + CliSyntax.PREFIX_TAG + VALID_TAG_ENTERTAINMENT;
    public static final String TAG_DESC_EXERCISE = " " + CliSyntax.PREFIX_TAG + VALID_TAG_EXERCISE;

    // '&' not allowed in names
    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&";
    // empty string not allowed for descriptions
    public static final String INVALID_DESCRIPTION_DESC = " " + CliSyntax.PREFIX_DESCRIPTION;
    // '*' not allowed in tags
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*";
    public static final String TOO_LONG_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "a_long_tag_to_test";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditActivityDescriptor DESC_ANIME;
    public static final EditCommand.EditActivityDescriptor DESC_BOXING;

    static {
        DESC_ANIME = new EditActivityDescriptorBuilder().withName(VALID_NAME_ANIME)
                .withDescription(VALID_DESCRIPTION_ANIME)
                .withTags(VALID_TAG_EXERCISE).build();
        DESC_BOXING = new EditActivityDescriptorBuilder().withName(VALID_NAME_BOXING)
                .withDescription(VALID_DESCRIPTION_BOXING)
                .withTags(VALID_TAG_ENTERTAINMENT, VALID_TAG_EXERCISE).build();
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
     * - the HobbyList, filtered activity list and selected activity in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        HobbyList expectedHobbyList = new HobbyList(actualModel.getHobbyList());
        List<Activity> expectedFilteredList = new ArrayList<>(actualModel.getFilteredActivityList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedHobbyList, actualModel.getHobbyList());
        assertEquals(expectedFilteredList, actualModel.getFilteredActivityList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the activity at the given {@code targetIndex} in the
     * {@code model}'s HobbyList.
     */
    public static void showActivityAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredActivityList().size());

        Activity activity = model.getFilteredActivityList().get(targetIndex.getZeroBased());
        final String[] splitName = activity.getName().fullName.split("\\s+");
        model.updateFilteredActivityList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredActivityList().size());
    }

}
