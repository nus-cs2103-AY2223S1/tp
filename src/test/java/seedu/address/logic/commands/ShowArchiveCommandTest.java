package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalArchivedTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ShowArchiveCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalArchivedTaskBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getArchivedAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        CommandResult expectedCommandResult = new CommandResult(ShowArchiveCommand.MESSAGE_SUCCESS, false, false, true);
        assertCommandSuccess(new ShowArchiveCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(ShowArchiveCommand.MESSAGE_SUCCESS, false, false, true);
        assertCommandSuccess(new ShowArchiveCommand(), model, expectedCommandResult, expectedModel);
    }
}
