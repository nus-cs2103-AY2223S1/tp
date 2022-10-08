package paymelah.logic.commands;

import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StatementCommand.
 */
class StatementCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    //TODO add test cases

}
