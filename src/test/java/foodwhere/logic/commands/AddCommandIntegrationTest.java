package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandFailure;
import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;

import foodwhere.model.stall.Stall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.testutil.PersonBuilder;
import foodwhere.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Stall validStall = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validStall);

        assertCommandSuccess(new AddCommand(validStall), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStall), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Stall stallInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(stallInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
