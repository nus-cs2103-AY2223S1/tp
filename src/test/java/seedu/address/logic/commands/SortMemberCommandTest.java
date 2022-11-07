package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.FLAG_HELP_STR;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.logic.parser.Order;
import seedu.address.logic.parser.OrderConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class SortMemberCommandTest {
    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new SortMemberCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Order.class, new OrderConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(ALICE);
        model.getTeam().addMember(CARL);
        model.getTeam().addMember(BENSON);
        expectedModel.getTeam().addMember(ALICE);
        expectedModel.getTeam().addMember(CARL);
        expectedModel.getTeam().addMember(BENSON);
    }

    @Test
    public void execute_helpFlagSupplied_success() {
        commandLine.parseArgs(FLAG_HELP_STR);
        CommandResult expectedResult = new CommandResult(
                SortMemberCommand.HELP_MESSAGE + commandLine.getUsageMessage());
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }
    @Test
    public void execute_unsortedMembersToAscending_success() {
        commandLine.parseArgs(new String[] {"asc"});
        expectedModel.getTeam().sortMembers((t1, t2) -> t1.getName().toString().compareTo(t2.getName().toString()));
        assertCommandSuccess(commandToBeTested, model, SortMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void execute_unsortedMembersToDescending_success() {
        commandLine.parseArgs(new String[] {"dsc"});
        expectedModel.getTeam().sortMembers((t1, t2) -> t2.getName().toString().compareTo(t1.getName().toString()));
        assertCommandSuccess(commandToBeTested, model, SortMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }
    @Test
    public void execute_sortedAscendingMembersToUnsorted_success() {
        model.getTeam().sortMembers((t1, t2) -> t1.getName().toString().compareTo(t2.getName().toString()));
        commandLine.parseArgs(new String[] {"res"});
        assertCommandSuccess(commandToBeTested, model, SortMemberCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
