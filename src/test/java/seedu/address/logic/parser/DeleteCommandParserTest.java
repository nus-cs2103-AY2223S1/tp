package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validPhoneArg_returnsDeleteCommand() {
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("12345678"));
        assertParseSuccess(parser, " p/12345678", new DeleteCommand(deletePersonDescriptor));
    }

    @Test
    public void parse_validEmailArg_returnsDeleteCommand() {
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setEmail(new Email("test@test.com"));
        assertParseSuccess(parser, " e/test@test.com", new DeleteCommand(deletePersonDescriptor));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPhoneArg_throwsParseException() {
        assertParseFailure(parser, " p/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyEmailArg_throwsParseException() {
        assertParseFailure(parser, " e/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "testing123",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_bothArgs_throwsParseException() {
        assertParseFailure(parser, " p/12345678 e/test@test.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiplePhoneArgs_throwsParseException() {
        assertParseFailure(parser, " p/12345678 p/87654321",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleEmailArgs_throwsParseException() {
        assertParseFailure(parser, " e/test@test.com e/test123@test.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
