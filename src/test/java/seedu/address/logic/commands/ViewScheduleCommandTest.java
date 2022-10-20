package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSchedules.getTypicalAddressBookWithSchedules;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.schedule.ScheduleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewModuleScheduleCommand.
 */
public class ViewScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithSchedules(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ScheduleContainsKeywordsPredicate firstPredicate =
                new ScheduleContainsKeywordsPredicate(Collections.singletonList("first"));
        ScheduleContainsKeywordsPredicate secondPredicate =
                new ScheduleContainsKeywordsPredicate(Collections.singletonList("second"));

        ViewModuleScheduleCommand viewFirstCommand = new ViewModuleScheduleCommand(firstPredicate);
        ViewModuleScheduleCommand viewFirstCommandCopy = new ViewModuleScheduleCommand(firstPredicate);
        ViewModuleScheduleCommand viewSecondCommand = new ViewModuleScheduleCommand(secondPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true

        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));


    }

    @Test
    public void execute_scheduleListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ViewModuleScheduleCommand(), model,
                new CommandResult(String.format(String.format(
                        Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, model.getFilteredScheduleList().size())),
                false, false, false, false,
                false, true), expectedModel);
    }
}
