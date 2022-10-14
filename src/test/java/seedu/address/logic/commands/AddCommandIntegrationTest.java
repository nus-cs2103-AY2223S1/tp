package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalJeeqTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalJeeqTracker(), new UserPrefs());
    }

    @Test
    public void execute_newCompany_success() {
        Company validCompany = new CompanyBuilder().build();

        Model expectedModel = new ModelManager(model.getJeeqTracker(), new UserPrefs());
        expectedModel.addCompany(validCompany);

        assertCommandSuccess(new AddCommand(validCompany), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validCompany), expectedModel);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        Company companyInList = model.getJeeqTracker().getCompanyList().get(0);
        assertCommandFailure(new AddCommand(companyInList), model, AddCommand.MESSAGE_DUPLICATE_COMPANY);
    }

}
