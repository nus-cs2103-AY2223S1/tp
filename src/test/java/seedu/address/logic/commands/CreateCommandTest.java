package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static seedu.address.testutil.TypicalPoc.ALICE;
import static seedu.address.testutil.TypicalPoc.AMY;
import static seedu.address.testutil.TypicalPoc.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.testutil.CompanyBuilder;

class CreateCommandTest {

    private Model model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());

    @Test
    public void constructor_nullPoc_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(INDEX_FIRST_COMPANY, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null, ALICE));
    }

    @Test
    public void execute_addPoc_success() throws Exception {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company companyInFilteredList = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        // does not contain AMY
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_COMPANY, AMY);
        createCommand.execute(model);
        // check if contains added poc
        assertTrue(companyInFilteredList.containsPoc(AMY));
    }

    @Test
    public void execute_duplicatePoc_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Company companyInFilteredList = model.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased());
        Company editedCompany = new CompanyBuilder(companyInFilteredList).withAddedPoc(ALICE).build();
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);

        assertCommandFailure(createCommand, model, String.format(CreateCommand.MESSAGE_DUPLICATE_POC, ALICE.getName()));

    }

    @Test
    public void execute_invalidCompanyIndex_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);
        Index outOfBoundIndex = INDEX_SECOND_COMPANY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getJeeqTracker().getCompanyList().size());

        CreateCommand createCommand = new CreateCommand(outOfBoundIndex, ALICE);

        assertCommandFailure(createCommand, model, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CreateCommand createCommand = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);

        // same values -> returns true
        CreateCommand createCommandCopy = new CreateCommand(INDEX_FIRST_COMPANY, ALICE);
        assertTrue(createCommand.equals(createCommandCopy));

        // same object -> returns true
        assertTrue(createCommand.equals(createCommand));

        // null -> returns false
        assertFalse(createCommand.equals(null));

        // different types -> returns false
        assertFalse(createCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_SECOND_COMPANY, ALICE)));

        // different Poc -> returns false
        assertFalse(createCommand.equals(new CreateCommand(INDEX_FIRST_COMPANY, BOB)));
    }


}
