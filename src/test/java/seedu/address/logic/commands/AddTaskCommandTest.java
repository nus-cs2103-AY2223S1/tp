package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.logic.parser.TaskNameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.Task;
import seedu.address.model.team.TaskName;
import seedu.address.testutil.TaskUtil;
import seedu.address.testutil.TypicalPersons;
import seedu.address.testutil.TypicalTasks;

class AddTaskCommandTest {

    private final Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new AddTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(TaskName.class, new TaskNameConverter())
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(TypicalPersons.ALICE);
        expectedModel.getTeam().addMember(TypicalPersons.ALICE);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                AddTaskCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task validTask = TypicalTasks.TASK_3;
        expectedModel.getTeam().addTask(validTask);
        commandLine.parseArgs(TaskUtil.convertTaskToArgs(validTask, 1, "2022-12-12", "23:59"));
        CommandResult expectedResult = new CommandResult(
                String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                validTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Task validTask = TypicalTasks.TASK_3;
        Task validPartialTask = TypicalTasks.TASK_3_NO_DEADLINE;
        expectedModel.getTeam().addTask(validPartialTask);
        commandLine.parseArgs(TaskUtil.convertPartialTaskToArgs(validTask, 1));
        CommandResult expectedResult = new CommandResult(
                String.format(AddTaskCommand.MESSAGE_ADD_TASK_SUCCESS,
                validPartialTask));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Task validTask = TypicalTasks.TASK_3;
        commandLine.parseArgs(TaskUtil.convertTaskToArgs(validTask, 2, "2022-12-12", "23:59"));
        assertThrows(CommandException.class, AddTaskCommand.MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS, ()
                -> commandToBeTested.execute(model));
    }

}
