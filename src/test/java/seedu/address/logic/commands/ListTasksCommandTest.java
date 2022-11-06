package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTasks.TASK_REVIEW;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

class ListTasksCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new ListTasksCommand();

    private final CommandLine commandLine = new CommandLine(commandToBeTested);

    @BeforeEach
    public void setUp() {
        model.getTeam().addTask(TASK_CODE);
        model.getTeam().addTask(TASK_REVIEW);
        expectedModel.getTeam().addTask(TASK_CODE);
        expectedModel.getTeam().addTask(TASK_REVIEW);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        commandLine.parseArgs(new String[] {});
        CommandResult expectedResult = new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 2));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("review"));
        model.updateFilteredTaskList(predicate);
        commandLine.parseArgs(new String[] {});
        CommandResult expectedResult = new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, 2));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_hasIncompleteFlag_showIncompleteTasks() {
        Task codeDone = TASK_CODE.mark(true);
        model.getTeam().setTask(TASK_CODE, codeDone);
        expectedModel.getTeam().setTask(TASK_CODE, codeDone);
        expectedModel.updateFilteredTaskList((task) -> !task.isComplete());
        commandLine.parseArgs(new String[] {"-i"});
        CommandResult expectedResult = new CommandResult(
                String.format(ListTasksCommand.MESSAGE_LIST_INCOMPLETE_TASKS_SUCCESS, 1));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_hasIncompleteFlag_showCompleteTasks() {
        Task codeDone = TASK_CODE.mark(true);
        model.getTeam().setTask(TASK_CODE, codeDone);
        expectedModel.getTeam().setTask(TASK_CODE, codeDone);
        expectedModel.updateFilteredTaskList((task) -> task.isComplete());
        commandLine.parseArgs(new String[] {"-c"});
        CommandResult expectedResult = new CommandResult(
                String.format(ListTasksCommand.MESSAGE_LIST_COMPLETE_TASKS_SUCCESS, 1));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

}