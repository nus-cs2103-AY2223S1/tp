package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalFindMyIntern;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.InternshipHasApplicationStatusPredicate;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFindMyIntern(), new UserPrefs());

    @Test
    public void execute_validFilter_success() {
        ApplicationStatus applicationStatus = ApplicationStatus.Applied;
        InternshipHasApplicationStatusPredicate predicate = new InternshipHasApplicationStatusPredicate(
                applicationStatus);
        FilterCommand filterCommand = new FilterCommand(predicate);

        String expectedMessage = String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, 3);

        expectedModel.updateFilteredInternshipList(predicate);

        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        InternshipHasApplicationStatusPredicate appliedPredicate = new InternshipHasApplicationStatusPredicate(
                ApplicationStatus.Applied);
        InternshipHasApplicationStatusPredicate rejectedPredicate = new InternshipHasApplicationStatusPredicate(
                ApplicationStatus.Rejected);

        FilterCommand firstFilterCommand = new FilterCommand(appliedPredicate);
        FilterCommand secondFilterCommand = new FilterCommand(rejectedPredicate);

        // same object -> returns true
        assertEquals(firstFilterCommand, firstFilterCommand);

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(appliedPredicate);
        assertEquals(firstFilterCommand, firstFilterCommandCopy);

        // different values -> returns false
        assertNotEquals(1, firstFilterCommand);

        // null -> returns false
        assertNotEquals(null, firstFilterCommand);

        // different filter -> returns false
        assertNotEquals(firstFilterCommand, secondFilterCommand);
    }
}
