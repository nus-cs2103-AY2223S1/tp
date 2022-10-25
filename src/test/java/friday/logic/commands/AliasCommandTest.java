package friday.logic.commands;

import static friday.logic.commands.CommandTestUtil.assertCommandFailure;
import static friday.logic.commands.CommandTestUtil.assertCommandSuccess;
import static friday.testutil.TypicalStudents.getTypicalFriday;

import org.junit.jupiter.api.Test;

import friday.model.Model;
import friday.model.ModelManager;
import friday.model.UserPrefs;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;

public class AliasCommandTest {

    private static final Alias VALID_ALIAS = new Alias("ls");
    private static final Alias INVALID_ALIAS = new Alias(AddCommand.COMMAND_WORD);
    private static final ReservedKeyword VALID_KEYWORD = new ReservedKeyword(ListCommand.COMMAND_WORD);
    private static final ReservedKeyword INVALID_KEYWORD = new ReservedKeyword("a");
    private Model model = new ModelManager(getTypicalFriday(), new UserPrefs());

    @Test
    public void execute_aliasAcceptedByModel_addSuccessful() {
        AliasCommand aliasCommand = new AliasCommand(VALID_ALIAS, VALID_KEYWORD);

        String expectedMessage = String.format(AliasCommand.MESSAGE_SUCCESS, VALID_ALIAS);

        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        expectedModel.addAlias(VALID_ALIAS, VALID_KEYWORD);

        assertCommandSuccess(aliasCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAlias_throwsCommandException() {
        AliasCommand aliasCommand = new AliasCommand(VALID_ALIAS, VALID_KEYWORD);
        model.addAlias(VALID_ALIAS, VALID_KEYWORD);

        assertCommandFailure(aliasCommand, model, AliasCommand.MESSAGE_DUPLICATE_ALIAS);
    }

    @Test
    public void execute_invalidAlias_throwsCommandException() {
        AliasCommand aliasCommand = new AliasCommand(INVALID_ALIAS, VALID_KEYWORD);

        assertCommandFailure(aliasCommand, model, AliasCommand.MESSAGE_INVALID_ALIAS);
    }

    @Test
    public void execute_invalidKeyword_throwsCommandException() {
        AliasCommand aliasCommand = new AliasCommand(VALID_ALIAS, INVALID_KEYWORD);

        assertCommandFailure(aliasCommand, model, AliasCommand.MESSAGE_INVALID_KEYWORD);
    }
}
