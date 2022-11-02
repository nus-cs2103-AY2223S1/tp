package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.profile.EditProfileCommand;
import seedu.address.model.Model;
import seedu.address.model.NuScheduler;
import seedu.address.model.event.Event;
import seedu.address.model.event.TitleContainsKeywordsPredicate;
import seedu.address.model.profile.NameContainsKeywordsPredicate;
import seedu.address.model.profile.Profile;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditProfileDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BERNICE = "Bernice Yu";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_ROY = "Roy Balakrishnan";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BERNICE = "99272758";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_ROY = "92624417";
    public static final String VALID_EMAIL_AMY = "amy@u.nus.edu";
    public static final String VALID_EMAIL_BERNICE = "berniceyu@u.duke.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@u.nus.edu";
    public static final String VALID_EMAIL_ROY = "royb@u.nus.edu";

    public static final String VALID_TELEGRAM_AMY = "amy_doe";

    public static final String VALID_TELEGRAM_BOB = "bobTheBuilder";

    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_FRIENDS = "friends";
    public static final String VALID_TAG_COLLEAGUES = "colleagues";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "bob__tele"; //consecutive underscores

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String VALID_TITLE_PRESENTATION = "Discuss presentation";
    public static final String VALID_TITLE_PRACTICE = "Practice";
    public static final String VALID_START_PRESENTATION = "11/10/2022 09:00";
    public static final String VALID_START_PRACTICE = "11/10/2022 13:00";
    public static final String VALID_END_PRESENTATION = "11/10/2022 10:00";
    public static final String VALID_END_PRACTICE = "11/10/2022 14:00";

    public static final String VALID_TAG_SWE = "CS2103T";
    public static final String VALID_TAG_CCA = "CCA";

    public static final String TITLE_DESC_PRESENTATION = " " + PREFIX_NAME + VALID_TITLE_PRESENTATION;
    public static final String TITLE_DESC_PRACTICE = " " + PREFIX_NAME + VALID_TITLE_PRACTICE;
    public static final String START_DESC_PRESENTATION = " " + PREFIX_START_DATE + VALID_START_PRESENTATION;
    public static final String START_DESC_PRACTICE = " " + PREFIX_START_DATE + VALID_START_PRACTICE;
    public static final String END_DESC_PRESENTATION = " " + PREFIX_END_DATE + VALID_END_PRESENTATION;
    public static final String END_DESC_PRACTICE = " " + PREFIX_END_DATE + VALID_END_PRACTICE;

    public static final String VALID_PROFILE_FIRST = " " + PREFIX_PROFILE + "1";
    public static final String VALID_PROFILE_SECOND = " " + PREFIX_PROFILE + "2";

    public static final String TAG_DESC_SWE = " " + PREFIX_TAG + VALID_TAG_SWE;
    public static final String TAG_DESC_CCA = " " + PREFIX_TAG + VALID_TAG_CCA;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_NAME + "Lunch&Dinner";
    public static final String INVALID_START_DESC = " " + PREFIX_START_DATE + "2020/02/02 abcd";
    public static final String INVALID_END_DESC = " " + PREFIX_END_DATE + "efgh 2020/15/02";
    public static final String INVALID_PROFILE_INDEX = " " + PREFIX_PROFILE + "10";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditProfileCommand.EditProfileDescriptor DESC_AMY;
    public static final EditProfileCommand.EditProfileDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditProfileDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditProfileDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    public static final EditEventCommand.EditEventDescriptor DESC_PRESENTATION;
    public static final EditEventCommand.EditEventDescriptor DESC_PRACTICE;

    static {
        DESC_PRESENTATION = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_PRESENTATION)
                .withStartDateTime(VALID_START_PRESENTATION).withEndDateTime(VALID_END_PRESENTATION)
                .withTags(VALID_TAG_SWE).build();
        DESC_PRACTICE = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_PRACTICE)
                .withStartDateTime(VALID_START_PRACTICE).withEndDateTime(VALID_END_PRACTICE)
                .withTags(VALID_TAG_CCA).build();
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
     * - the NUScheduler, filtered profile list and selected profile in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        NuScheduler expectedNuScheduler = new NuScheduler(actualModel.getNuScheduler());
        List<Profile> expectedFilteredList = new ArrayList<>(actualModel.getFilteredProfileList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedNuScheduler, actualModel.getNuScheduler());
        assertEquals(expectedFilteredList, actualModel.getFilteredProfileList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the profile at the given {@code targetIndex} in the
     * {@code model}'s NUScheduler.
     */
    public static void showProfileAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredProfileList().size());

        Profile profile = model.getFilteredProfileList().get(targetIndex.getZeroBased());
        final String[] splitName = profile.getName().fullName.split("\\s+");
        model.updateFilteredProfileList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredProfileList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s NUScheduler.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitTitle = event.getTitle().title.split("\\s+");
        model.updateFilteredEventList(new TitleContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }
}
