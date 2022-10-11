package seedu.rc4hdb.logic.commands.modelcommands;

import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandFailure;
import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.testutil.ResidentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
    }

    @Test
    public void execute_newResident_success() {
        Resident validResident = new ResidentBuilder().build();

        Model expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
        expectedModel.addResident(validResident);

        assertCommandSuccess(new AddCommand(validResident), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validResident), expectedModel);
    }

    @Test
    public void execute_duplicateResident_throwsCommandException() {
        Resident personInList = model.getResidentBook().getResidentList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_RESIDENT);
    }

}
