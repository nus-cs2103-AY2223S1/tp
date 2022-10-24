package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static friday.logic.parser.CommandParserTestUtil.assertParseFailure;
import static friday.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import friday.logic.commands.UnaliasCommand;
import friday.model.alias.Alias;

public class UnaliasCommandParserTest {

    private static final String VALID_ALIAS = "ls";
    private static final String INVALID_PREFIX = "g/";
    private static final String VALID_ARGS = " " + PREFIX_ALIAS + VALID_ALIAS;
    private static final String INVALID_ARGS = " " + INVALID_PREFIX + VALID_ALIAS;

    private UnaliasCommandParser parser = new UnaliasCommandParser();
    private UnaliasCommand expectedCommand = new UnaliasCommand(new Alias(VALID_ALIAS));

    @Test
    public void parse_validArgs_returnsUnaliasCommand() {
        assertParseSuccess(parser, VALID_ARGS, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_ARGS, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnaliasCommand.MESSAGE_USAGE));
    }
}
