package seedu.modquik.logic.commands.reminder;

import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.modquik.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.modquik.testutil.TypicalPersons.getTypicalModQuik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelManager;
import seedu.modquik.model.UserPrefs;

public class SortReminderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModQuik(), new UserPrefs());
        expectedModel = new ModelManager(model.getModQuik(), new UserPrefs());
    }

    @Test
    public void execute_listIsSortedByPriority_showsSameList() {
        assertCommandSuccess(new SortReminderCommand("priority"), model,
                String.format(SortReminderCommand.MESSAGE_SUCCESS_TEMPLATE, "priority"), expectedModel);
    }

    @Test
    public void execute_listIsSortedByDeadline_showsSameList() {
        assertCommandSuccess(new SortReminderCommand("deadline"), model,
                String.format(SortReminderCommand.MESSAGE_SUCCESS_TEMPLATE, "deadline"), expectedModel);
    }

    @Test
    public void execute_listIsSortedByInvalidWord_showsSameList() {
        assertCommandFailure(new SortReminderCommand("invalid"), model,
                Messages.MESSAGE_INVALID_SORTING_CRITERIA);
    }
}
