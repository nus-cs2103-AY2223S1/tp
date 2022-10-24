package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.commons.core.Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.testutil.TypicalInternships.CARL;
import static seedu.intrack.testutil.TypicalInternships.ELLE;
import static seedu.intrack.testutil.TypicalInternships.FIONA;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.PositionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPositionCommand}.
 */
public class FindPositionCommandTest {
    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInTrack(), new UserPrefs());

    @Test
    public void equals() {
        PositionContainsKeywordsPredicate firstPredicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("first"));
        PositionContainsKeywordsPredicate secondPredicate =
                new PositionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPositionCommand findFirstCommand = new FindPositionCommand(firstPredicate);
        FindPositionCommand findSecondCommand = new FindPositionCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPositionCommand findFirstCommandCopy = new FindPositionCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noInternshipFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 0);
        PositionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPositionCommand command = new FindPositionCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredInternshipList());
    }

    @Test
    public void execute_multipleKeywords_multipleInternshipsFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 3);
        PositionContainsKeywordsPredicate predicate = preparePredicate("Frontend Stack Security");
        FindPositionCommand command = new FindPositionCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredInternshipList());
    }

    /**
     * Parses {@code userInput} into a {@code PositionContainsKeywordsPredicate}.
     */
    private PositionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PositionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
