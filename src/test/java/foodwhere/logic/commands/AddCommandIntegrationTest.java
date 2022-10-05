package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandFailure;
import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.stall.Stall;
import foodwhere.testutil.StallBuilder;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newStall_success() {
        Stall validStall = new StallBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addStall(validStall);

        assertCommandSuccess(new AddCommand(validStall), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStall), expectedModel);
    }

    @Test
    public void execute_duplicateStall_throwsCommandException() {
        Stall stallInList = model.getAddressBook().getStallList().get(0);
        assertCommandFailure(new AddCommand(stallInList), model, AddCommand.MESSAGE_DUPLICATE_STALL);
    }

}
