package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.SortCriteria;

public class SortCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validFilter_success() {
        SortCriteria sortCriteria = SortCriteria.Applied;
        SortCommand sortCommand = new SortCommand(sortCriteria);

        String expectedMessage = String.format(Messages.MESSAGE_SORT_SUCCESS, "applied");

        expectedModel.updateSortedInternshipList(SortCriteria.getComparator(sortCriteria));

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCriteria appliedSortCriteria = SortCriteria.Applied;
        SortCriteria interviewSortCriteria = SortCriteria.Interview;

        SortCommand firstSortCommand = new SortCommand(appliedSortCriteria);
        SortCommand secondSortCommand = new SortCommand(interviewSortCriteria);

        // same object -> returns true
        assertEquals(firstSortCommand, firstSortCommand);

        // same values -> returns true
        SortCommand firstSortCommandCopy = new SortCommand(appliedSortCriteria);
        assertEquals(firstSortCommand, firstSortCommandCopy);

        // different values -> returns false
        assertNotEquals(1, firstSortCommand);

        // null -> returns false
        assertNotEquals(null, firstSortCommand);

        // different filter -> returns false
        assertNotEquals(firstSortCommand, secondSortCommand);
    }
}
