package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_BOTH_EMAIL_AND_PHONE;
import static seedu.boba.commons.core.Messages.MESSAGE_EMPTY_EMAIL_AND_PHONE;
import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.commons.core.Messages.MESSAGE_NO_UNIQUE_PREFIX_IDENTIFIER;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.DeleteCommand;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the CommandParserUtilTest.
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
                String.format(MESSAGE_EMPTY_EMAIL_AND_PHONE, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPhoneArg_throwsParseException() {
        assertParseFailure(parser, " p/",
                String.format(Phone.MESSAGE_CONSTRAINTS, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyEmailArg_throwsParseException() {
        assertParseFailure(parser, " e/",
                String.format(Email.MESSAGE_CONSTRAINTS, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "testing123",
                String.format(MESSAGE_EMPTY_EMAIL_AND_PHONE, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_bothArgs_throwsParseException() {
        assertParseFailure(parser, " p/12345678 e/test@test.com",
                String.format(MESSAGE_BOTH_EMAIL_AND_PHONE, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multiplePhoneArgs_throwsParseException() {
        assertParseFailure(parser, " p/12345678 p/87654321",
                String.format(MESSAGE_NO_UNIQUE_PREFIX_IDENTIFIER, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleEmailArgs_throwsParseException() {
        assertParseFailure(parser, " e/test@test.com e/test123@test.com",
                String.format(MESSAGE_NO_UNIQUE_PREFIX_IDENTIFIER, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preambleAndPhone_throwsParseException() {
        assertParseFailure(parser, " test p/12345678",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_preambleAndEmail_throwsParseException() {
        assertParseFailure(parser, " test e/test@test.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
