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

import org.junit.jupiter.api.Test;

import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindNameCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInTrack(), new UserPrefs());

    @Test
    public void equals() {
        StatusIsKeywordPredicate firstPredicate = new StatusIsKeywordPredicate("Rejected");
        StatusIsKeywordPredicate secondPredicate = new StatusIsKeywordPredicate("Offered");

        FilterCommand findFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand findSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different internship -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_offered_multipleInternshipsFound() {
        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 3);
        StatusIsKeywordPredicate predicate = new StatusIsKeywordPredicate("Offered");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredInternshipList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredInternshipList());
    }

}
