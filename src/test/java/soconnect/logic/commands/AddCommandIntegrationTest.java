package soconnect.logic.commands;

import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;
import soconnect.model.person.Person;
import soconnect.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalSoConnect(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getSoConnect(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getSoConnect().getPersonList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
