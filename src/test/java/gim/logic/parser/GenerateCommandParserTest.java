package gim.logic.parser;

import static gim.commons.core.Messages.MESSAGE_INCORRECT_INDEX_FORMAT;
import static gim.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static gim.commons.core.Messages.MESSAGE_INVALID_LEVEL;
import static gim.commons.core.Messages.MESSAGE_MISSING_LEVEL;
import static gim.logic.parser.CliSyntax.PREFIX_LEVEL;
import static gim.logic.parser.CommandParserTestUtil.assertParseFailure;
import static gim.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static gim.testutil.TypicalIndexes.INDEX_LIST_FIRST_SECOND;

import org.junit.jupiter.api.Test;

import gim.logic.commands.GenerateCommand;
import gim.logic.generators.ValidLevel;


public class GenerateCommandParserTest {
    // mix of upper and lower case letters
    public static final String EASY_STRING = "eaSy";
    // whitespaces between indices
    public static final String INDEX_ONE_TWO_STRING = "1, 2  ";
    private GenerateCommandParser parser = new GenerateCommandParser();


    @Test
    public void parse_indicesSpecified_success() {
        String userInput = INDEX_ONE_TWO_STRING + PREFIX_LEVEL + EASY_STRING;
        GenerateCommand expectedCommand = new GenerateCommand(INDEX_LIST_FIRST_SECOND, ValidLevel.EASY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingAllCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, " ", expectedMessage);
    }

    @Test
    public void parse_indicesWrongFormat_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GenerateCommand.MESSAGE_USAGE
                + "\n\n" + MESSAGE_INCORRECT_INDEX_FORMAT);

        // no index(es)
        assertParseFailure(parser, PREFIX_LEVEL + EASY_STRING, expectedMessage);

        // illegal character in index(es)
        assertParseFailure(parser, "1/2" + PREFIX_LEVEL + EASY_STRING, expectedMessage);

        // trailing comma
        assertParseFailure(parser, "1,2," + PREFIX_LEVEL + EASY_STRING, expectedMessage);
    }

    @Test
    public void parse_missingLevel_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GenerateCommand.MESSAGE_USAGE + "\n\n" + MESSAGE_MISSING_LEVEL);

        // no level specified
        assertParseFailure(parser, INDEX_ONE_TWO_STRING + PREFIX_LEVEL, expectedMessage);
    }

    @Test
    public void parse_invalidLevel_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                GenerateCommand.MESSAGE_USAGE + "\n\n" + MESSAGE_INVALID_LEVEL);
        String invalidLevel = "ez";
        assertParseFailure(parser, INDEX_ONE_TWO_STRING + PREFIX_LEVEL + invalidLevel, expectedMessage);
    }
}
