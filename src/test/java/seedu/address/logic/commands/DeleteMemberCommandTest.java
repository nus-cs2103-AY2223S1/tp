package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import java.util.List;

import javax.script.AbstractScriptEngine;

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
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMemberCommand}.
 */
// TODO: Add implementation for tests
public class DeleteMemberCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    private final Command commandToBeTested = new DeleteMemberCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter());

    @BeforeEach
    public void setUp() {
        model.getTeam().addMember(ALICE);
        model.getTeam().addMember(BENSON);
        expectedModel.getTeam().addMember(ALICE);
        expectedModel.getTeam().addMember(BENSON);
    }

    @Test
    public void execute_unfilteredList_success() {
        commandLine.parseArgs(new String[] {"1"});
        expectedModel.getTeam().removeMember(ALICE);
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteMemberCommand.MESSAGE_DELETE_PERSON_SUCCESS, ALICE));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidMemberIndexUnfilteredList_throwsCommandException() {
        commandLine.parseArgs(new String[] {"3"});
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));
    }

    @Test
    public void execute_filteredList_success() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Benson"));
        model.updateFilteredMembersList(predicate);
        expectedModel.getTeam().removeMember(BENSON);
        commandLine.parseArgs(new String[] {"1"});
        CommandResult expectedResult = new CommandResult(
                String.format(DeleteMemberCommand.MESSAGE_DELETE_PERSON_SUCCESS, BENSON));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidMemberIndexFilteredList_throwsCommandException() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(List.of("Benson"));
        model.updateFilteredMembersList(predicate);
        commandLine.parseArgs(new String[] {"2"});
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, ()
                -> commandToBeTested.execute(model));

    }
    @Test
    public void equals() {
    }
}
