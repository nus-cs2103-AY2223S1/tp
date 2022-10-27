package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TEXT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEXT_GOOD_BUYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemarkCommand;

class RemarkCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

    private RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_invalidIndexPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // missing index in preamble
        assertParseFailure(parser, "Bad Buyer", MESSAGE_INVALID_FORMAT);

        // missing keyword in preamble
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidRemarkFields_failure() {
        // index but incorrect text
        assertParseFailure(parser, "1" + INVALID_TEXT, MESSAGE_INVALID_FORMAT);
        // only text
        assertParseFailure(parser, VALID_TEXT_GOOD_BUYER, MESSAGE_INVALID_FORMAT);
        // only index
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
        // invalid tag
        assertParseFailure(parser, "1" + VALID_TEXT_GOOD_BUYER + INVALID_TAG_DESC, MESSAGE_INVALID_FORMAT);
    }

}
