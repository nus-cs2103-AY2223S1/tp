package seedu.rc4hdb.logic.commands.modelcommands;

import static seedu.rc4hdb.logic.commands.modelcommands.ModelCommandTestUtil.assertCommandSuccess;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.rc4hdb.model.Model;
import seedu.rc4hdb.model.ModelManager;
import seedu.rc4hdb.model.UserPrefs;
import static seedu.rc4hdb.testutil.TypicalResidents.getTypicalResidentBook;

public class ShowCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalResidentBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getResidentBook(), new UserPrefs());
    }

    @Test
    public void execute_showCommandWithNonEmptyList_showsNonEmptyListInModel() {
        expectedModel.setObservableFields(List.of("name", "phone"));
        assertCommandSuccess(new ShowCommand(List.of("name", "phone")), model, ShowCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showCommandWithEmptyList_overwritesNonEmptyListInModel() {
        model.setObservableFields(List.of("name", "phone"));

        // already empty during initialisation but added to make test case explicit
        expectedModel.setObservableFields(List.of());

        assertCommandSuccess(new ShowCommand(List.of()), model, ShowCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_showCommandWithNonEmptyList_overwritesEmptyListInModel() {
        // already empty during initialisation but added to make test case explicit
        model.setObservableFields(List.of());

        expectedModel.setObservableFields(List.of("name", "phone"));

        assertCommandSuccess(new ShowCommand(List.of("name", "phone")), model, ShowCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
