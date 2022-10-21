package swift.logic.commands;

import org.junit.jupiter.api.Test;
import swift.logic.commands.exceptions.CommandException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static swift.logic.parser.CliSyntax.PREFIX_CONTACT;
import static swift.logic.parser.CliSyntax.PREFIX_NAME;

public class CommandSuggestorTest {
    private final CommandSuggestor commandSuggestor = new CommandSuggestor();
    
    @Test
    public void autocomplete_validCommand_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " " + PREFIX_CONTACT + "<contact_index>" + " " + PREFIX_NAME + "<name>";
        try {
            System.out.println(expectedSuggestion);
            System.out.println(commandSuggestor.suggestCommand("add_t"));
            assertEquals(expectedSuggestion, commandSuggestor.suggestCommand("add_t"));
        } catch (CommandException e) {
            throw new AssertionError("Autocomplete should not fail.", e);
        }
    }

    @Test
    public void autocomplete_invalidCommand_failure() {
        assertThrows(CommandException.class, () -> commandSuggestor.suggestCommand("asdfdf"));
    }

    @Test
    public void autocomplete_validCommandWithPrefix_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " " + PREFIX_NAME + " " + PREFIX_CONTACT               + "<contact_index>";
        try {
            assertEquals(expectedSuggestion, commandSuggestor.suggestCommand("add_task n/ "));
        } catch (CommandException e) {
            throw new AssertionError("Autocomplete should not fail.", e);
        }
    }

    @Test
    public void autocomplete_validCommandWithPrefixAndValue_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " " + PREFIX_NAME + "abc " + PREFIX_CONTACT + "<contact_index>";
        try {
            assertEquals(expectedSuggestion, commandSuggestor.suggestCommand("add_task n/abc "));
        } catch (CommandException e) {
            throw new AssertionError("Autocomplete should not fail.", e);
        }
    }

    @Test
    public void autocomplete_validCommandWithInvalidPrefix_failure() {
        assertThrows(CommandException.class, () -> commandSuggestor.suggestCommand("add_task a"));
    }
    
}
