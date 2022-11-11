package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swift.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static swift.testutil.TypicalPersonIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import swift.logic.commands.DeleteContactCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteContactCommandParserTest {

    private DeleteContactCommandParser parser = new DeleteContactCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteContactCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteContactCommand.MESSAGE_USAGE));
    }
}
