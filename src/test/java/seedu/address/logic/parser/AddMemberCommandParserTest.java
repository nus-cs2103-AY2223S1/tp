package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.model.person.Name;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the AddMemberCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AddMemberCommandParserTest {

    private AddMemberCommandParser parser = new AddMemberCommandParser();

    @Test
    public void parse_validArgs_returnsAddMemberCommand() {
        String name = "Firstname Lastname";
        assertParseSuccess(parser, name, new AddMemberCommand(new Name(name)));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Name.MESSAGE_CONSTRAINTS));
    }
}
