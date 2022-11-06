package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddMemberCommand.MESSAGE_ADD_MEMBER_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */

public class AddMemberCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private final Command commandToBeTested = new AddMemberCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_memberAcceptedByModel_addSuccessful() {
        commandLine.parseArgs(new String[] {"1"});
        expectedModel.getTeam().addMember(ALICE);
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_ADD_MEMBER_SUCCESS, ALICE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_memberAlreadyInTeam_throwsCommandException() {
        model.getTeam().addMember(ALICE);
        commandLine.parseArgs(new String[] {"1"});
        assertThrows(CommandException.class, AddMemberCommand.MESSAGE_DUPLICATE_PERSON, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_indexOutOfBounds_throwsCommandException() {
        Integer outOfBoundsIndex = model.getFilteredPersonList().size() + 1;
        commandLine.parseArgs(new String[] {outOfBoundsIndex.toString()});
        assertThrows(CommandException.class, AddMemberCommand.MESSAGE_MEMBER_INDEX_OUT_OF_BOUNDS, ()
                -> commandToBeTested.execute(model));
    }
}
