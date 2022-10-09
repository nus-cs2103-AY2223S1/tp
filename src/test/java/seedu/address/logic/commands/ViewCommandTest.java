package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalCompanies;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.model.company.NameEqualsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    private final Index one = Index.fromOneBased(1);
    private final Index two = Index.fromOneBased(2);

    @Test
    public void equals() {

        ViewCommand viewFirstCommand = new ViewCommand(one);
        ViewCommand viewSecondCommand = new ViewCommand(two);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(one);
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
        ViewCommand command = new ViewCommand(one);

        List<Company> companies = getTypicalCompanies();
        Company expectedCompany = companies.get(0);
        String expectedCompanyName = expectedCompany.getName().toString();

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_COMPANY_SUCCESS, expectedCompanyName);

        NameEqualsKeywordPredicate predicate = new NameEqualsKeywordPredicate(expectedCompanyName);
        expectedModel.updateFilteredCompanyList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList().size(), 1);
    }
}
