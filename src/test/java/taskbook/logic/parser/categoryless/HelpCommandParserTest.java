package taskbook.logic.parser.categoryless;

import static taskbook.logic.parser.CliSyntax.PREFIX_HELP_COMMAND;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.categoryless.HelpCommand;
import taskbook.logic.commands.categoryless.UndoCommand;

public class HelpCommandParserTest {

    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArgument() {
        HelpCommand expected = new HelpCommand(null);
        assertParseSuccess(parser, "   ", expected);
        assertParseSuccess(parser, "", expected);
    }

    @Test
    public void parse_validArgument() {
        HelpCommand expected = new HelpCommand(HelpCommand.CommandWord.UNDO);
        String userInput = getUserInput(UndoCommand.COMMAND_WORD);
        assertParseSuccess(parser, userInput, expected);
    }

    @Test
    public void parse_invalidArgument() {
        assertParseFailure(parser, getUserInput("  "),
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        assertParseFailure(parser, getUserInput("command_that_does_not_exist"),
            String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    private String getUserInput(String commandWord) {
        return " " + PREFIX_HELP_COMMAND + commandWord;
    }
}
