package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateCommand;

class CreateCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateCommand.MESSAGE_USAGE);

    private CreateCommandParser parser = new CreateCommandParser();

    @Test
    public void parse_invalidIndexPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidCompanyFields_failure() {
        // name but no phone and email
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, CreateCommand.MESSAGE_COMPANY_INVALID);
        // only phone
        assertParseFailure(parser, "1 p/912988", CreateCommand.MESSAGE_COMPANY_INVALID);
        // only email
        assertParseFailure(parser, "1 e/james@gmail.com", CreateCommand.MESSAGE_COMPANY_INVALID);
        // name and phone but no email
        assertParseFailure(parser, "1 n/james p/99191919", CreateCommand.MESSAGE_COMPANY_INVALID);
        // name and email but no phone
        assertParseFailure(parser, "1 n/james e/james@gmail.com", CreateCommand.MESSAGE_COMPANY_INVALID);
        // phone and email but no name
        assertParseFailure(parser, "1 p/91298129 e/james@gmail.com", CreateCommand.MESSAGE_COMPANY_INVALID);
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, CreateCommand.MESSAGE_COMPANY_INVALID);
    }

    @Test
    public void parse_invalidFormat_failure() {
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, INVALID_ADDRESS_DESC, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_NAME_AMY + VALID_EMAIL_AMY,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_NAME_AMY + VALID_EMAIL_AMY,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_NAME_AMY + VALID_PHONE_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_NAME_AMY + VALID_PHONE_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1" + VALID_NAME_AMY + VALID_PHONE_AMY + VALID_EMAIL_AMY,
                MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, VALID_NAME_AMY + VALID_PHONE_AMY + VALID_EMAIL_AMY,
                MESSAGE_INVALID_FORMAT);

    }
}
