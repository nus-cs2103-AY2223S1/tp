package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskPanel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.teammate.Teammate;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalTaskPanel(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Teammate validTeammate = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), model.getTaskPanel(), new UserPrefs());
        expectedModel.addPerson(validTeammate);

        assertCommandSuccess(new AddCommand(validTeammate), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTeammate), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Teammate teammateInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(teammateInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
