package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTasks.TASK_REVIEW;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

class DeleteTaskCommandTest {
    private final Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new DeleteTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

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
        CommandResult expectedResult = new CommandResult(
                DeleteTaskCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_unfilteredList_success() {
        commandLine.parseArgs("1");
        expectedModel.getTeam().removeTask(TASK_CODE);
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, TASK_CODE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidMemberIndexUnfilteredList_throwsCommandException() {
        commandLine.parseArgs("3");
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_filteredList_success() {
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("review"));
        model.updateFilteredTaskList(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        expectedModel.getTeam().removeTask(TASK_REVIEW);
        commandLine.parseArgs("1");
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, TASK_REVIEW));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidMemberIndexFilteredList_throwsCommandException() {
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of("review"));
        model.updateFilteredTaskList(predicate);
        commandLine.parseArgs("2");
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));

    }
}
