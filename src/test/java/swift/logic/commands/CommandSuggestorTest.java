package swift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static swift.logic.parser.CliSyntax.PREFIX_CONTACT;
import static swift.logic.parser.CliSyntax.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import swift.logic.commands.exceptions.CommandException;

public class CommandSuggestorTest {
    private final CommandSuggestor commandSuggestor = new CommandSuggestor();

    @Test
    public void suggestCommand_emptyString_failure() {
        assertThrows(AssertionError.class, () -> commandSuggestor.suggestCommand(""));
    }

    @Test
    public void suggestCommand_null_failure() {
        assertThrows(AssertionError.class, () -> commandSuggestor.suggestCommand(null));
    }

    @Test
    public void suggestCommand_validCommand_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " "
                                        + PREFIX_CONTACT + "<contact_index>" + " "
                                        + PREFIX_NAME + "<name>";
        try {
            System.out.println(expectedSuggestion);
            System.out.println(commandSuggestor.suggestCommand("add_t"));
            assertEquals(expectedSuggestion, commandSuggestor.suggestCommand("add_t"));
        } catch (CommandException e) {
            throw new AssertionError("Command Suggestion should not fail.", e);
        }
    }

    @Test
    public void suggestCommand_invalidCommand_failure() {
        assertThrows(CommandException.class, () -> commandSuggestor.suggestCommand("asdfdf"));
    }

    @Test
    public void suggestCommand_validCommandWithPrefix_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " "
                                        + PREFIX_NAME + " "
                                        + PREFIX_CONTACT + "<contact_index>";
        try {
            assertEquals(expectedSuggestion, commandSuggestor.suggestCommand("add_task n/ "));
        } catch (CommandException e) {
            throw new AssertionError("Command Suggestion should not fail.", e);
        }
    }

    @Test
    public void suggestCommand_validCommandWithPrefixAndValue_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " "
                                        + PREFIX_NAME + "abc "
                                        + PREFIX_CONTACT + "<contact_index>";
        try {
            assertEquals(expectedSuggestion, commandSuggestor.suggestCommand("add_task n/abc "));
        } catch (CommandException e) {
            throw new AssertionError("Command Suggestion should not fail.", e);
        }
    }

    @Test
    public void suggestCommand_validCommandWithInvalidPrefix_failure() {
        assertThrows(CommandException.class, () -> commandSuggestor.suggestCommand("add_task a"));
    }

    @Test
    public void autocomplete_validCommandWithNoPrefix_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " ";
        try {
            assertEquals(expectedSuggestion, commandSuggestor.autocompleteCommand("add_t",
                commandSuggestor.suggestCommand("add_t")));
        } catch (CommandException e) {
            throw new AssertionError("Autocomplete should not fail.", e);
        }
    }

    @Test
    public void autocomplete_validCommandWithPrefix_success() {
        String expectedSuggestion = AddTaskCommand.COMMAND_WORD + " " + PREFIX_CONTACT;
        try {
            assertEquals(expectedSuggestion, commandSuggestor.autocompleteCommand("add_task ",
                    commandSuggestor.suggestCommand("add_task ")));
        } catch (CommandException e) {
            throw new AssertionError("Autocomplete should not fail.", e);
        }
    }
}
