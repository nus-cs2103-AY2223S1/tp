package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ELEMENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteMeetingCommand;

public class DeleteMeetingCommandParserTest {

    private DeleteMeetingCommandParser parser = new DeleteMeetingCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser,
                " i/a",
                ParserUtil.MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " i/1", new DeleteMeetingCommand(INDEX_FIRST_ELEMENT));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        //Negative index
        assertParseFailure(parser, " i/-1", ParserUtil.MESSAGE_INVALID_INDEX);

        //Zero index
        assertParseFailure(parser, " i/0", ParserUtil.MESSAGE_INVALID_INDEX);

        //Empty index
        assertParseFailure(parser, " i/", ParserUtil.MESSAGE_INVALID_INDEX);
    }

}
