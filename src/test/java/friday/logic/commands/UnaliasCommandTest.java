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

public class UnaliasCommandTest {

    private static final Alias VALID_ALIAS = new Alias("ls");
    private static final Alias VALID_ALIAS_2 = new Alias("a");
    private static final ReservedKeyword VALID_KEYWORD = new ReservedKeyword(ListCommand.COMMAND_WORD);
    private static final String INVALID_KEYWORD = "a";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unaliasAcceptedByModel_removeSuccessful() {
        ModelManager expectedModel = new ModelManager(model.getFriday(), new UserPrefs());
        model.addAlias(VALID_ALIAS, VALID_KEYWORD);
        UnaliasCommand unaliasCommand = new UnaliasCommand(VALID_ALIAS);

        String expectedMessage = String.format(UnaliasCommand.MESSAGE_SUCCESS, VALID_ALIAS);

        assertCommandSuccess(unaliasCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_aliasNotFound_throwsCommandException() {
        model.addAlias(VALID_ALIAS, VALID_KEYWORD);
        UnaliasCommand unaliasCommand = new UnaliasCommand(VALID_ALIAS_2);

        assertThrows(CommandException.class,
                UnaliasCommand.MESSAGE_ALIAS_NOT_FOUND, () -> unaliasCommand.execute(model));
    }
}
