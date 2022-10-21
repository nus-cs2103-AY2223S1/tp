package seedu.address.logic.commands.profile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PROFILES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.profile.FindProfileCommand.MESSAGE_NO_MATCH;
import static seedu.address.logic.commands.profile.FindProfileCommand.MESSAGE_PROFILE_LISTED_OVERVIEW;
import static seedu.address.testutil.TypicalProfiles.CARL;
import static seedu.address.testutil.TypicalProfiles.ELLE;
import static seedu.address.testutil.TypicalProfiles.FIONA;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.profile.NameContainsKeywordsPredicate;
import seedu.address.testutil.TypicalNuScheduler;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindProfileCommandTest {
    private Model model = new ModelManager(TypicalNuScheduler.getTypicalNuScheduler(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalNuScheduler.getTypicalNuScheduler(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindProfileCommand findProfileFirstCommand = new FindProfileCommand(firstPredicate);
        FindProfileCommand findProfileSecondCommand = new FindProfileCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findProfileFirstCommand.equals(findProfileFirstCommand));

        // same values -> returns true
        FindProfileCommand findProfileFirstCommandCopy = new FindProfileCommand(firstPredicate);
        assertTrue(findProfileFirstCommand.equals(findProfileFirstCommandCopy));

        // different types -> returns false
        assertFalse(findProfileFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findProfileFirstCommand.equals(null));

        // different profile -> returns false
        assertFalse(findProfileFirstCommand.equals(findProfileSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noProfileFound() {
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindProfileCommand command = new FindProfileCommand(predicate);
        expectedModel.updateFilteredProfileList(predicate);
        assertCommandSuccess(command, model, MESSAGE_NO_MATCH, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredProfileList());
    }

    @Test
    public void execute_singleKeyword_singleProfileFound() {
        NameContainsKeywordsPredicate predicate = preparePredicate("Elle");
        FindProfileCommand command = new FindProfileCommand(predicate);
        expectedModel.updateFilteredProfileList(predicate);
        assertCommandSuccess(command, model, MESSAGE_PROFILE_LISTED_OVERVIEW, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredProfileList());
    }

    @Test
    public void execute_multipleKeywords_multipleProfilesFound() {
        String expectedMessage = String.format(MESSAGE_PROFILES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindProfileCommand command = new FindProfileCommand(predicate);
        expectedModel.updateFilteredProfileList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredProfileList());
    }

    @Test
    public void execute_partialKeywords_multipleProfilesFound() {
        String expectedMessage = String.format(MESSAGE_PROFILES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kur Elle unz");
        FindProfileCommand command = new FindProfileCommand(predicate);
        expectedModel.updateFilteredProfileList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredProfileList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}
