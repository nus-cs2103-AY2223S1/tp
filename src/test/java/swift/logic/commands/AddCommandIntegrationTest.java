package swift.logic.commands;

import static swift.logic.commands.CommandTestUtil.assertCommandFailure;
import static swift.logic.commands.CommandTestUtil.assertCommandSuccess;
import static swift.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import swift.commons.core.index.Index;
import swift.model.Model;
import swift.model.ModelManager;
import swift.model.UserPrefs;
import swift.model.person.Person;
import swift.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        String expectedMessage = String.format(AddContactCommand.MESSAGE_SUCCESS, validPerson);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, true, false);

        assertCommandSuccess(new AddContactCommand(validPerson), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddContactCommand(personInList), model, AddContactCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
