package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TaskUtil;
import seedu.address.testutil.TypicalTasks;

public class TasksSummaryCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new TasksSummaryCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested);

    @Test
    public void execute_success() {
        model.getTeam().addTask(TypicalTasks.TASK_3);
        CommandResult expectedResult = new CommandResult(String.format(TasksSummaryCommand.MESSAGE_TASK_SUMMARY,
                TaskUtil.getTaskSummary(model)));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
}
