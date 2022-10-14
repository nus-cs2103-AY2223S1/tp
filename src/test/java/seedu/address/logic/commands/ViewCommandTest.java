package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalCompanies.getTypicalCompanies;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameEqualsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void equals() {

        ViewCommand viewFirstCommand = new ViewCommand(INDEX_FIRST_COMPANY);
        ViewCommand viewSecondCommand = new ViewCommand(INDEX_SECOND_COMPANY);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(INDEX_FIRST_COMPANY);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

    @Test
    public void execute_indexOne_viewsCompanySuccess() {
        ViewCommand command = new ViewCommand(INDEX_FIRST_COMPANY);

        List<Client> companies = getTypicalCompanies();
        Client expectedClient = companies.get(0);
        String expectedCompanyName = expectedClient.getName().toString();

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_COMPANY_SUCCESS, expectedCompanyName);

        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate(expectedCompanyName);
        expectedModel.updateFilteredCompanyList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList().size(), 1);
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Index outOfBoundIndex = INDEX_SECOND_COMPANY;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJeeqTracker().getCompanyList().size());

        ViewCommand viewCommand = new ViewCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }
}
