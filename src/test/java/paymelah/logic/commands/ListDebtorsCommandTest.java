package paymelah.logic.commands;

import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.logic.commands.CommandTestUtil.showPersonAtIndex;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
