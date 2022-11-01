package taskbook.logic.commands;

import static taskbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static taskbook.testutil.TypicalTaskBook.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import taskbook.logic.commands.contacts.ContactAddCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.model.person.Person;
import taskbook.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class ContactAddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new ContactAddCommand(validPerson), model,
                String.format(ContactAddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getTaskBook().getPersonList().get(0);
        assertCommandFailure(new ContactAddCommand(personInList), model, ContactAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
