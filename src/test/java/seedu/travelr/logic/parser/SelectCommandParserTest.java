package seedu.travelr.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.SelectCommand;
import seedu.travelr.model.event.EventInItineraryPredicate;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.travelr.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;

class SelectCommandParserTest {

    private SelectCommandParser parser = new SelectCommandParser();

    @Test
    public void parse_validArgs_returnsSelectCommand() {
        assertParseSuccess(parser, "1", new SelectCommand(new EventInItineraryPredicate(Index.fromOneBased(1))));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(
                "Index cannot be empty." + "\n\n" + MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "n/asd", String.format(
                MESSAGE_INVALID_INDEX + "\n\n" + MESSAGE_INVALID_COMMAND_FORMAT, SelectCommand.MESSAGE_USAGE));
    }

}