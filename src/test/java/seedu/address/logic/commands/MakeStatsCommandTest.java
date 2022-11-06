package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_TOO_LARGE_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class MakeStatsCommandTest {

    @Test
    public void execute_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        //Generate age based statistics
        assertCommandSuccess(new MakeStatsCommand(INDEX_FIRST_EVENT, Boolean.FALSE),
                model, new CommandResult(MakeStatsCommand.SHOWING_STATS_MESSAGE,
                false, true, false), expectedModel);

        //Generate gender based statistics
        assertCommandSuccess(new MakeStatsCommand(INDEX_FIRST_EVENT, Boolean.TRUE),
                model, new CommandResult(MakeStatsCommand.SHOWING_STATS_MESSAGE,
                false, true, false), expectedModel);
    }

    @Test
    public void execute_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        //Index out of bounds
        assertCommandFailure(new MakeStatsCommand(INDEX_TOO_LARGE_EVENT, Boolean.FALSE),
                model, MakeStatsCommand.INDEX_OUT_OF_BOUNDS_MESSAGE);

        //Event has no person tagged to it
        assertCommandFailure(new MakeStatsCommand(INDEX_SECOND_EVENT, Boolean.TRUE),
                model, MakeStatsCommand.NO_STATS_MESSAGE);
    }
}
