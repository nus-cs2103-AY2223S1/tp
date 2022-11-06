package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPersonsCommand.
 */
public class ListPersonsCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new ListPersonsCommand();

    private final CommandLine commandLine = new CommandLine(commandToBeTested);

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        commandLine.parseArgs(new String[] {});
        assertCommandSuccess(commandToBeTested, model, ListPersonsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Benson"));
        model.updateFilteredPersonList(predicate);
        commandLine.parseArgs(new String[] {});
        assertCommandSuccess(commandToBeTested, model, ListPersonsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
