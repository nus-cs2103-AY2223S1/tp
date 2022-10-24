package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static friday.logic.parser.CliSyntax.PREFIX_RESERVED_KEYWORD;
import static friday.logic.parser.CommandParserTestUtil.assertParseFailure;
import static friday.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import friday.logic.commands.AliasCommand;
import friday.logic.commands.ListCommand;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;

public class AliasCommandParserTest {

    private static final String VALID_ALIAS = "ls";
    private static final String INVALID_PREFIX = "g/ ";
    private static final String VALID_ALIAS_ARG = " " + PREFIX_ALIAS + VALID_ALIAS;
    private static final String VALID_RESERVED_KEYWORD_ARG = " " + PREFIX_RESERVED_KEYWORD + ListCommand.COMMAND_WORD;
    private static final String INVALID_ARGS_1 = INVALID_PREFIX + VALID_ALIAS + VALID_RESERVED_KEYWORD_ARG;
    private static final String INVALID_ARGS_2 = VALID_ALIAS_ARG + INVALID_PREFIX + ListCommand.COMMAND_WORD;
    private AliasCommandParser parser = new AliasCommandParser();
    private AliasCommand expectedCommand = new AliasCommand(new Alias(VALID_ALIAS),
            new ReservedKeyword(ListCommand.COMMAND_WORD));

    @Test
    public void parse_validArgs_returnsAliasCommand() {
        assertParseSuccess(parser, VALID_ALIAS_ARG + VALID_RESERVED_KEYWORD_ARG, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, INVALID_ARGS_1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AliasCommand.MESSAGE_USAGE));
        assertParseFailure(parser, INVALID_ARGS_2, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AliasCommand.MESSAGE_USAGE));
    }
}
