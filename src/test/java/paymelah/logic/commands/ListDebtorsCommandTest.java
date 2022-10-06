package paymelah.logic.commands;

import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;

import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListDebtorsCommand.
 */
class ListDebtorsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    //TODO add test cases

}
