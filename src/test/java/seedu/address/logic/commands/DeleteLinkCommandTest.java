package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddMemberCommand.MESSAGE_ADD_MEMBER_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLinks.LINK_FACEBOOK;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.logic.parser.TaskNameConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.team.TaskName;

// TODO: Add implementation for tests
class DeleteLinkCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = model;

    private final Command commandToBeTested = new DeleteLinkCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.addLink(LINK_GOOGLE);
        model.addLink(LINK_FACEBOOK);
    }

    @Test
    public void execute_unfilteredList_success() {
        commandLine.parseArgs(new String[] {"1"});
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteLinkCommand.MESSAGE_DELETE_LINK_SUCCESS, LINK_GOOGLE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidLinkIndexUnfilteredList_throwsCommandException() {
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
