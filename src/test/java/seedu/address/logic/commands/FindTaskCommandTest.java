package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;
import static seedu.address.testutil.TypicalTasks.TASK_1;
import static seedu.address.testutil.TypicalTasks.TASK_CODE;
import static seedu.address.testutil.TypicalTasks.TASK_REVIEW;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.team.TaskNameContainsKeywordsPredicate;

class FindTaskCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new FindTaskCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addTask(TASK_1);
        model.getTeam().addTask(TASK_CODE);
        model.getTeam().addTask(TASK_REVIEW);
        expectedModel.getTeam().addTask(TASK_1);
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
    public void execute_multipleNameKeywords_multipleTasksFound() {
        String[] keywords = {"code", "review"};
        commandLine.parseArgs(keywords);
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(List.of(keywords));
        model.updateFilteredTaskList(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        CommandResult expectedResult = new CommandResult(String.format(FindTaskCommand.MESSAGE_SUCCESS,
                model.getFilteredTaskList().size(), predicate.getKeywordsAsString()));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
}
