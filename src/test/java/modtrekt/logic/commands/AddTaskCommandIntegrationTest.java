package modtrekt.logic.commands;

import static modtrekt.logic.commands.CommandTestUtil.assertCommandFailure;
import static modtrekt.logic.commands.CommandTestUtil.assertCommandSuccess;
import static modtrekt.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.UserPrefs;
import modtrekt.model.person.Person;
import modtrekt.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddTaskCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddTaskCommand(validPerson), model,
                String.format(AddTaskCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getTaskBook().getPersonList().get(0);
        assertCommandFailure(new AddTaskCommand(personInList), model, AddTaskCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
