package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RenameCommand;

public class RenameCommandParserTest {

    private RenameCommandParser parser = new RenameCommandParser();

    @Test
    public void parse_validArgs_returnsRemarkCommand() {
        assertParseSuccess(parser, "cs2103t", new RenameCommand("cs2103t"));
    }


    @Test
    public void parse_noArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, RenameCommand.MESSAGE_USAGE));
    }

}
