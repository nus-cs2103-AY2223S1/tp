package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.travelr.logic.commands.DeleteEventFromTripCommand;
import seedu.travelr.model.component.Title;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteEventFromTripCommandParserTest {

    private DeleteEventFromTripCommandParser parser = new DeleteEventFromTripCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteEventFromTripCommand() {
        assertParseSuccess(parser, " n/asd t/asd", new DeleteEventFromTripCommand(new Title("asd"), new Title("asd")));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteEventFromTripCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_incompleteArgs_throwsParseException() {
        assertParseFailure(parser, "n/asd",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteEventFromTripCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "/n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteEventFromTripCommand.MESSAGE_USAGE));
    }
}
