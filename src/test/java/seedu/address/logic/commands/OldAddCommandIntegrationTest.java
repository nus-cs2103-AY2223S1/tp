package seedu.address.logic.commands;

import static seedu.address.logic.commands.OldCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.OldCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the OldModel) for {@code OldAddCommand}.
 */
public class OldAddCommandIntegrationTest {

    private OldModel model;

    @BeforeEach
    public void setUp() {
        model = new OldModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        OldModel expectedModel = new OldModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new OldAddCommand(validPerson), model,
                String.format(OldAddCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new OldAddCommand(personInList), model, OldAddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
