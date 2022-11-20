package seedu.address.logic.commands.schedules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.getTypicalProfNusWithSchedules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedule.ViewTimeTableCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ViewTimetableCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalProfNusWithSchedules(), new UserPrefs());
        expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
    }

    @Test
    public void equals() {
        int zero = 0;
        int one = 1;

        ViewTimeTableCommand viewFirstTimeTableCommand = new ViewTimeTableCommand(one);
        ViewTimeTableCommand viewFirstTimeTableCommandCopy = new ViewTimeTableCommand(one);
        ViewTimeTableCommand viewSecondTimeTableCommand = new ViewTimeTableCommand(zero);

        // same object -> returns true
        assertTrue(viewFirstTimeTableCommand.equals(viewFirstTimeTableCommand));

        // same values -> returns true
        assertTrue(viewFirstTimeTableCommand.equals(viewFirstTimeTableCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstTimeTableCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstTimeTableCommand.equals(null));

        // different values -> returns false
        assertFalse(viewFirstTimeTableCommand.equals(viewSecondTimeTableCommand));

    }

    @Test
    public void execute_inputZero_showHorizontalSchedule() {

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewTimeTableCommand.SHOWING_HORIZONTAL_TIMETABLE_MESSAGE),
                false, false, false, false, false,
                false, false, true, false,
                false, false);
        CommandTestUtil.assertCommandSuccess(new ViewTimeTableCommand(0), model, expectedCommandResult,
                expectedModel);
    }

    @Test
    public void execute_inputOne_showVerticalSchedule() {

        CommandResult expectedCommandResult = new CommandResult(
                String.format(ViewTimeTableCommand.SHOWING_VERTICAL_TIMETABLE_MESSAGE),
                false, false, false, false, false,
                false, false, false, true,
                false, false);
        CommandTestUtil.assertCommandSuccess(new ViewTimeTableCommand(1), model, expectedCommandResult,
                expectedModel);
    }


    @Test
    public void execute_invalidInput_throwCommandException() {
        ViewTimeTableCommand viewTimeTableCommand = new ViewTimeTableCommand(-1);
        assertThrows(CommandException.class, () -> viewTimeTableCommand.execute(model));
    }

    @Test
    public void execute_emptyInput_showHorizontalSchedule() {
        CommandResult expectedCommandResult = new CommandResult(
                    String.format(ViewTimeTableCommand.SHOWING_HORIZONTAL_TIMETABLE_MESSAGE),
                    false, false, false, false, false,
                    false, false, true, false,
                    false, false);
        CommandTestUtil.assertCommandSuccess(new ViewTimeTableCommand(0), model, expectedCommandResult,
                    expectedModel);
    }

}
