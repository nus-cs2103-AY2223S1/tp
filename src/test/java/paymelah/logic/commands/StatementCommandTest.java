package paymelah.logic.commands;

import static paymelah.logic.commands.CommandTestUtil.assertCommandSuccess;
import static paymelah.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import paymelah.model.Model;
import paymelah.model.ModelManager;
import paymelah.model.UserPrefs;
import paymelah.model.person.PersonMatchesDescriptorPredicate;
import paymelah.testutil.DebtsDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * StatementCommand.
 */
class StatementCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_statementOnUnfilteredList_success() {
        String expectedMessage = String.format(StatementCommand.MESSAGE_SUCCESS, "94.32");
        assertCommandSuccess(new StatementCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_statementOnFilteredList_success() {
        String expectedMessage = String.format(StatementCommand.MESSAGE_SUCCESS, "9.00");
        PersonMatchesDescriptorPredicate predicate = new PersonMatchesDescriptorPredicate(
                new DebtsDescriptorBuilder().withDescriptions("supper jio").build());
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(new StatementCommand(), model, expectedMessage, expectedModel);
    }
}
