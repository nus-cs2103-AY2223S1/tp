package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntry.getTypicalPennyWise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.EntryType;
import seedu.address.testutil.ExpenditureBuilder;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPennyWise(), new UserPrefs());
    }

    @Test
    public void execute_newExpenditure_success() {
        Entry validExpenditure = new ExpenditureBuilder().build();
        Model expectedModel = new ModelManager(model.getPennyWise(), new UserPrefs());
        expectedModel.addExpenditure(validExpenditure);
        assertCommandSuccess(new AddCommand(validExpenditure, new EntryType("e")), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validExpenditure), expectedModel);
    }

    @Test
    public void execute_duplicateExpenditure_throwsCommandException() {
        Entry expenditureInList = model.getPennyWise().getExpenditureList().get(0);
        assertCommandFailure(
                new AddCommand(expenditureInList, new EntryType("e")),
                model,
                AddCommand.MESSAGE_DUPLICATE_ENTRY);
    }

    // @Test
    // public void execute_newPerson_success() {
    //     Person validPerson = new PersonBuilder().build();
    //     Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    //     expectedModel.addPerson(validPerson);
    //     assertCommandSuccess(new AddCommand(validPerson), model,
    //             String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    // }
    // @Test
    // public void execute_duplicatePerson_throwsCommandException() {
    //     Person personInList = model.getAddressBook().getPersonList().get(0);
    //     assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    // }

}
