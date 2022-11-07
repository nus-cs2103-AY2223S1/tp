package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_FACEBOOK;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

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

class DeleteLinkCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new DeleteLinkCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.addLink(LINK_GOOGLE);
        model.addLink(LINK_FACEBOOK);
        expectedModel.addLink(LINK_GOOGLE);
        expectedModel.addLink(LINK_FACEBOOK);
    }
    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                DeleteLinkCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_unfilteredList_success() {
        commandLine.parseArgs(new String[] {"1"});
        expectedModel.deleteLink(LINK_GOOGLE);
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteLinkCommand.MESSAGE_DELETE_LINK_SUCCESS, LINK_GOOGLE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidLinkIndexUnfilteredList_throwsCommandException() {
        commandLine.parseArgs(new String[] {"3"});
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_LINK_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_filteredList_success() {

    }

    @Test
    public void execute_invalidLinkIndexFilteredList_throwsCommandException() {

    }

    @Test
    public void testEquals() {
    }
}
