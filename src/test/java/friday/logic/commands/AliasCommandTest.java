package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;
import static friday.testutil.Assert.assertThrows;
import static friday.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;

public class AliasCommandTest {

    private static final String VALID_ALIAS = "ls";
    private static final String INVALID_KEYWORD = "a";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_aliasAcceptedByModel_addSuccessful() {
        Alias alias = new Alias(VALID_ALIAS);
        ReservedKeyword keyword = new ReservedKeyword(ListCommand.COMMAND_WORD);
        AliasCommand aliasCommand = new AliasCommand(alias, keyword);

        String expectedMessage = String.format(AliasCommand.MESSAGE_SUCCESS, alias);

        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.addAlias(alias, keyword);

        assertCommandSuccess(aliasCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAlias_throwsCommandException() {
        Alias alias = new Alias(VALID_ALIAS);
        ReservedKeyword keyword = new ReservedKeyword(ListCommand.COMMAND_WORD);
        AliasCommand aliasCommand = new AliasCommand(alias, keyword);
        model.addAlias(alias, keyword);

        assertThrows(CommandException.class, AliasCommand.MESSAGE_DUPLICATE_ALIAS, () -> aliasCommand.execute(model));
    }

    @Test
    public void execute_invalidAlias_throwsCommandException() {
        Alias alias = new Alias(AddCommand.COMMAND_WORD);
        ReservedKeyword keyword = new ReservedKeyword(ListCommand.COMMAND_WORD);
        AliasCommand aliasCommand = new AliasCommand(alias, keyword);

        assertThrows(CommandException.class, AliasCommand.MESSAGE_INVALID_ALIAS, () -> aliasCommand.execute(model));
    }

    @Test
    public void execute_invalidKeyword_throwsCommandException() {
        Alias alias = new Alias(VALID_ALIAS);
        ReservedKeyword keyword = new ReservedKeyword(INVALID_KEYWORD);
        AliasCommand aliasCommand = new AliasCommand(alias, keyword);

        assertThrows(CommandException.class, AliasCommand.MESSAGE_INVALID_KEYWORD, () -> aliasCommand.execute(model));
    }
}
