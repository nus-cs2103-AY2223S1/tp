package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.HelpCommand;
import seedu.clinkedin.logic.commands.ListCommand;

public class HelpCommandParserTest {
    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_returnsDefaultHelpCommand() {
        HelpCommand expectedHelpCommand = new HelpCommand();
        assertParseSuccess(parser, "", expectedHelpCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // No valid command word given
        assertParseFailure(parser, "This will fail",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsHelpCommand() {
        // no leading and trailing whitespaces
        HelpCommand expectedHelpCommand = new HelpCommand(ListCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, "list", expectedHelpCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \tlist\t", expectedHelpCommand);
    }

    @Test
    public void parse_everyValidCommand_returnsHelpCommand() {
        try {
            // Get list of all possible command words
            Map<String, String> allCommandMessages = CliSyntax.getAllCommandMessages();
            // Create a help command for every possible command word
            for (String commandWord : allCommandMessages.keySet()) {
                // Assert that help command matches command's message usage
                HelpCommand expectedHelpCommand = new HelpCommand(allCommandMessages.get(commandWord));
                assertParseSuccess(parser, commandWord, expectedHelpCommand);
            }
        } catch (Exception e) {
            assert false;
        }
    }
}
