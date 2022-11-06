package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.List;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePersonCommand}.
 */
public class DeletePersonCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new DeletePersonCommand();

    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_unfilteredList_success() {
        commandLine.parseArgs(new String[] {"1"});
        expectedModel.deletePerson(ALICE);
        CommandResult expectedResult = new CommandResult(
                String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, ALICE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidMemberIndexUnfilteredList_throwsCommandException() {
        int size = model.getFilteredPersonList().size();
        commandLine.parseArgs(new String[] {String.valueOf(size + 1)});
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_filteredList_success() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Benson"));
        model.updateFilteredPersonList(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        expectedModel.deletePerson(BENSON);
        commandLine.parseArgs(new String[] {"1"});
        CommandResult expectedResult = new CommandResult(
                String.format(DeletePersonCommand.MESSAGE_DELETE_PERSON_SUCCESS, BENSON));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidMemberIndexFilteredList_throwsCommandException() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Benson"));
        model.updateFilteredPersonList(predicate);
        commandLine.parseArgs(new String[] {"2"});
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));

    }

    @Test
    public void equals() {
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
