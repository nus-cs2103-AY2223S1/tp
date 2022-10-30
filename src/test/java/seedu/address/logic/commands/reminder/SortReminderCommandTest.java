package seedu.address.logic.commands.reminder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortReminderCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
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
}
