package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddMemberCommand.MESSAGE_ADD_MEMBER_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_ONE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EmailConverter;
import seedu.address.logic.parser.IndexConverter;
import seedu.address.logic.parser.NameConverter;
import seedu.address.logic.parser.PhoneConverter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.LinkUtil;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AddMemberCommand}.
 */
// TODO: Add implementation for tests
public class AddMemberCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private Model expectedModel = model;
    private final Command commandToBeTested = new AddMemberCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @Test
    public void execute_memberAcceptedByModel_addSuccessful() {
        commandLine.parseArgs(new String[] {"1"});
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

    @Test
    public void equals() {
    }
}
