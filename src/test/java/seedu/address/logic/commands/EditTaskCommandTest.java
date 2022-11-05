package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.List;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.logic.parser.TaskNameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;
import seedu.address.testutil.TaskUtil;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTasks;

class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new EditTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter())
            .registerConverter(TaskName.class, new TaskNameConverter());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addTask(TypicalTasks.TASK_1);
        Task validTask = TypicalTasks.TASK_3;
        commandLine.parseArgs(TaskUtil.convertEditTaskToArgs(validTask));
        CommandResult expectedResult = new CommandResult(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                validTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addTask(TypicalTasks.TASK_1);
        Task validTask = TypicalTasks.TASK_3;
        commandLine.parseArgs(TaskUtil.convertEditPartialTaskToArgs(validTask));
        CommandResult expectedResult = new CommandResult(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                TypicalTasks.TASK_3_NO_DEADLINE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addTask(TypicalTasks.TASK_2);
        model.getTeam().addTask(TypicalTasks.TASK_3);
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("three"));
        model.updateFilteredTaskList(predicate);
        Task validTask = model.getFilteredTaskList().get(0);
        commandLine.parseArgs(TaskUtil.convertEditPartialNoNameTaskToArgs(validTask));
        CommandResult expectedResult = new CommandResult(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                TypicalTasks.TASK_2_EDITED));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addTask(TypicalTasks.TASK_1);
        Task validTask = TypicalTasks.TASK_3;
        commandLine.parseArgs(TaskUtil.convertEditOutOfBoundsTaskToArgs(validTask));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        model.getTeam().addTask(TypicalTasks.TASK_2);
        model.getTeam().addTask(TypicalTasks.TASK_3);
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("three"));
        model.updateFilteredTaskList(predicate);
        Task validTask = model.getFilteredTaskList().get(0);
        commandLine.parseArgs(TaskUtil.convertEditOutOfBoundsTaskToArgs(validTask));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }
}
