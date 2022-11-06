package seedu.address.logic.commands.schedules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSchedules.getTypicalModuleSet;
import static seedu.address.testutil.TypicalSchedules.getTypicalProfNusWithSchedules;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.schedule.ViewScheduleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.schedule.Schedule;
import seedu.address.model.module.schedule.ScheduleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewScheduleCommand.
 */
public class ViewScheduleCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalProfNusWithSchedules(), new UserPrefs());
        expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
    }

    @Test
    public void equals() {
        ScheduleContainsKeywordsPredicate firstPredicate =
                new ScheduleContainsKeywordsPredicate(Collections.singletonList("first"));
        ScheduleContainsKeywordsPredicate secondPredicate =
                new ScheduleContainsKeywordsPredicate(Collections.singletonList("second"));

        Set<String> first = new HashSet<>();
        first.add("first");

        Set<String> second = new HashSet<>();
        second.add("second");

        ViewScheduleCommand viewFirstCommand = new ViewScheduleCommand(firstPredicate, first);
        ViewScheduleCommand viewFirstCommandCopy = new ViewScheduleCommand(firstPredicate, first);
        ViewScheduleCommand viewSecondCommand = new ViewScheduleCommand(secondPredicate, second);
        ViewScheduleCommand viewEmptyCommand = new ViewScheduleCommand();
        ViewScheduleCommand viewEmptyCommandCopy = new ViewScheduleCommand();


        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));
        assertTrue(viewEmptyCommand.equals(viewEmptyCommand));

        // same values -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));
        assertTrue(viewEmptyCommand.equals(viewEmptyCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));


    }

    @Test
    public void execute_scheduleListIsNotFiltered_showsSameList() {
        CommandTestUtil.assertCommandSuccess(new ViewScheduleCommand(), model,
                new CommandResult(String.format(String.format(
                        Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, model.getFilteredScheduleList().size())),
                false, false, false, false,
                false, false, true, false, false), expectedModel);
    }

    @Test
    public void execute_emptyProfNus_success() {
        Model curModel = new ModelManager();
        Model curExpectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(String.format(
                String.format(Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, curModel.getFilteredScheduleList().size())),
                false, false, false, false,
                false, false, true, false, false);
        assertCommandSuccess(new ViewScheduleCommand(), curModel, expectedCommandResult, curExpectedModel);
    }

    @Test
    public void execute_emptyInput_showAllSchedules() {
        assertCommandSuccess(new ViewScheduleCommand(), model,
                new CommandResult(String.format(String.format(
                        Messages.MESSAGE_SCHEDULES_LISTED_OVERVIEW, model.getFilteredScheduleList().size())),
                        false, false, false, false, false,
                        false, true, false, false), expectedModel);
    }

    @Test
    public void execute_nonExistModule_throwCommandException() {
        Set<String> modules = getTypicalModuleSet();
        List<Schedule> schedules = model.getFilteredScheduleList();



    }

}
