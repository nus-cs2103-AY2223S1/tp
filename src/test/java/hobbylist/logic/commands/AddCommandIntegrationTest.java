package hobbylist.logic.commands;

import static hobbylist.logic.commands.CommandTestUtil.assertCommandFailure;
import static hobbylist.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hobbylist.testutil.PersonBuilder;
import hobbylist.testutil.TypicalPersons;
import hobbylist.model.Model;
import hobbylist.model.ModelManager;
import hobbylist.model.UserPrefs;
import hobbylist.model.activity.Activity;

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
        Activity validActivity = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validActivity);

        assertCommandSuccess(new AddCommand(validActivity), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validActivity), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Activity activityInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(activityInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
