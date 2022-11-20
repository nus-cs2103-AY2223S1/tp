package bookface.logic.commands.add;

import static bookface.logic.commands.CommandTestUtil.assertCommandFailure;
import static bookface.logic.commands.CommandTestUtil.assertCommandSuccess;
import static bookface.testutil.TypicalPersons.getTypicalBookFaceData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.model.Model;
import bookface.model.ModelManager;
import bookface.model.UserPrefs;
import bookface.model.person.Person;
import bookface.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalBookFaceData(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getBookFace(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddUserCommand(validPerson), model,
                String.format(AddUserCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getBookFace().getPersonList().get(0);
        assertCommandFailure(new AddUserCommand(personInList), model, Messages.MESSAGE_DUPLICATE_PERSON);
    }

}
