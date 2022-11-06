package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_INDEX_NUMBER_AMY;
import static seedu.watson.logic.commands.CommandTestUtil.VALID_REMARK_COFFEE;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.watson.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.RemarkCommand;

public class RemarkCommandParserTest {

    private final RemarkCommandParser parser = new RemarkCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // missing remark
        assertParseFailure(parser, VALID_INDEX_NUMBER_AMY, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = MESSAGE_INVALID_INDEX;

        assertParseFailure(parser, VALID_REMARK_COFFEE, expectedMessage);
        assertParseFailure(parser, "0" + " " + VALID_REMARK_COFFEE, expectedMessage);
    }
}
