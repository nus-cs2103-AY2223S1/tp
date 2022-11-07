package seedu.codeconnect.logic.commands;

import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.codeconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.codeconnect.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.codeconnect.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.ModelManager;
import seedu.codeconnect.model.UserPrefs;
import seedu.codeconnect.model.person.Person;
import seedu.codeconnect.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddContactCommand}.
 */
public class AddContactCommandIntegrationTest {

    private Model model;

    //should use getTypicalTaskList() instead of null
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskList(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddContactCommand(validPerson), model,
                String.format(AddContactCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddContactCommand(personInList), model, AddContactCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
