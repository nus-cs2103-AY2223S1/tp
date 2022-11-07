package foodwhere.logic.parser;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseFailure;
import static foodwhere.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import foodwhere.logic.commands.SSortCommand;
import foodwhere.model.stall.comparator.StallsComparatorList;

public class SSortCommandParserTest {

    private SSortCommandParser parser = new SSortCommandParser();

    @Test
    public void parse_validArgs_returnsSSortCommand() {
        assertParseSuccess(parser, "name", new SSortCommand(StallsComparatorList.valueOf("NAME")));
        assertParseSuccess(parser, "Name", new SSortCommand(StallsComparatorList.valueOf("NAME")));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SSortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "notSupportedCriterion",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SSortCommand.MESSAGE_USAGE));
    }
}
