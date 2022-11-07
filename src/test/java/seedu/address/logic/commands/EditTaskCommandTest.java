package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_2;
import static seedu.address.testutil.TypicalTasks.TASK_3;
import static seedu.address.testutil.TypicalTasks.TASK_3_NO_DEADLINE;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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
import seedu.address.testutil.TaskBuilder;
import seedu.address.testutil.TaskUtil;
import seedu.address.testutil.TypicalTasks;

class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new EditTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter())
            .registerConverter(TaskName.class, new TaskNameConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(ALICE);
        expectedModel.getTeam().addMember(ALICE);
        model.getTeam().addTask(TASK_1);
        expectedModel.getTeam().addTask(TASK_1);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                EditTaskCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task validTask = new TaskBuilder(TASK_3).build();
        expectedModel.setTask(TASK_1, validTask);
        commandLine.parseArgs(TaskUtil.convertEditTaskToArgs(validTask, 1, 1));
        CommandResult expectedResult = new CommandResult(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                validTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Task validTask = TASK_3_NO_DEADLINE;
        expectedModel.setTask(TASK_1, validTask);
        commandLine.parseArgs(TaskUtil.convertEditPartialTaskToArgs(validTask, 1, 1));
        CommandResult expectedResult = new CommandResult(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                TypicalTasks.TASK_3_NO_DEADLINE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model.getTeam().addTask(TASK_2);
        model.getTeam().addTask(TASK_3);
        expectedModel.getTeam().addTask(TASK_2);
        expectedModel.getTeam().addTask(TASK_3);

        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("three"));
        model.updateFilteredTaskList(predicate);
        Task validTask = model.getFilteredTaskList().get(0);

        expectedModel.setTask(TASK_3, validTask);

        commandLine.parseArgs(TaskUtil.convertEditTaskToArgs(validTask, 1, 1));
        CommandResult expectedResult = new CommandResult(String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS,
                validTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Task validTask = TASK_3;
        commandLine.parseArgs(TaskUtil.convertEditPartialNoNameTaskToArgs(validTask, 3, 1));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        model.getTeam().addTask(TASK_2);
        model.getTeam().addTask(TASK_3);
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("three"));
        model.updateFilteredTaskList(predicate);
        Task validTask = model.getFilteredTaskList().get(0);
        commandLine.parseArgs(TaskUtil.convertEditPartialNoNameTaskToArgs(validTask, 4, 1));
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }
}
