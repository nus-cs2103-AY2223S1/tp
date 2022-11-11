package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getNoInterviewFindMyIntern;
import static seedu.address.testutil.TypicalInternships.getTypicalFindMyIntern;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.SortCriteria;

public class SortCommandTest {
    private Model appliedModel = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());
    private Model expectedAppliedModel = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());
    private Model interviewModel = new ModelManager(getNoInterviewFindMyIntern(), new UserPrefs());
    private Model expectedInterviewModel = new ModelManager(getNoInterviewFindMyIntern(), new UserPrefs());

    @Test
    public void execute_validSortApplied_success() {
        SortCriteria sortCriteria = SortCriteria.Applied;
        SortCommand sortCommand = new SortCommand(sortCriteria);

        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "applied");

        expectedAppliedModel.updateSortedInternshipList(SortCriteria.getComparator(sortCriteria));

        assertCommandSuccess(sortCommand, appliedModel, expectedMessage, expectedAppliedModel);
    }

    @Test
    public void execute_validSortInterview_success() {
        SortCriteria sortCriteria = SortCriteria.Interview;
        SortCommand sortCommand = new SortCommand(sortCriteria);

        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "interview");

        expectedInterviewModel.updateSortedInternshipList(SortCriteria.getComparator(sortCriteria));

        assertCommandSuccess(sortCommand, interviewModel, expectedMessage, expectedInterviewModel);
    }

    @Test
    public void equals() {
        SortCriteria appliedSortCriteria = SortCriteria.Applied;
        SortCriteria interviewSortCriteria = SortCriteria.Interview;

        SortCommand firstSortCommand = new SortCommand(appliedSortCriteria);
        SortCommand secondSortCommand = new SortCommand(interviewSortCriteria);

        // same object -> returns true
        assertTrue(firstSortCommand.equals(firstSortCommand));

        // same values -> returns true
        SortCommand firstSortCommandCopy = new SortCommand(appliedSortCriteria);
        assertTrue(firstSortCommand.equals(firstSortCommandCopy));

        // different values -> returns false
        assertNotEquals(1, firstSortCommand);
        assertFalse(firstSortCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortCommand.equals(null));

        // different filter -> returns false
        assertFalse(firstSortCommand.equals(secondSortCommand));

    }
}
