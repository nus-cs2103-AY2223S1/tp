package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STATS_DESC_AGE;
import static seedu.address.logic.commands.CommandTestUtil.STATS_DESC_GENDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MakeStatsCommand;

public class MakeStatsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeStatsCommand.MESSAGE_USAGE);

    private MakeStatsCommandParser parser = new MakeStatsCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, STATS_DESC_AGE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STATS_DESC_AGE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + STATS_DESC_AGE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid type
        assertParseFailure(parser, "1" + INVALID_TYPE_DESC, MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_success() {
        MakeStatsCommand expectedCommand = new MakeStatsCommand(INDEX_FIRST, Boolean.TRUE);

        //gender type
        assertParseSuccess(parser, INDEX_FIRST.getOneBased() + STATS_DESC_GENDER, expectedCommand);

        //age type
        assertParseSuccess(parser, INDEX_FIRST.getOneBased() + STATS_DESC_AGE,
                new MakeStatsCommand(INDEX_FIRST, Boolean.FALSE));

        //additional whitespace at preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased()
                + PREAMBLE_WHITESPACE + STATS_DESC_GENDER, expectedCommand);
    }
}
