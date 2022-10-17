package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.profile.DeleteProfileCommand.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.profile.DeleteProfileCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteProfileCommandParserTest {

    private DeleteProfileCommandParser parser = new DeleteProfileCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteProfileCommand() {
        assertParseSuccess(parser, "1", new DeleteProfileCommand(INDEX_FIRST_PROFILE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProfileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_MISSING_INDEX, DeleteProfileCommand.MESSAGE_USAGE));
    }
}
