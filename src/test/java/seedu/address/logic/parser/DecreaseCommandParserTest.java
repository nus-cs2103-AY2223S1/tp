package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalPhones.PHONE_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DecreaseCommand;

public class DecreaseCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DecreaseCommand.MESSAGE_USAGE);

    private DecreaseCommandParser parser = new DecreaseCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no reward values specified
        assertParseFailure(parser, VALID_PHONE_AMY, MESSAGE_INVALID_FORMAT);

        // another no reward values specified
        assertParseFailure(parser, " " + PREFIX_PHONE + PHONE_FIRST_PERSON, MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative reward value
        assertParseFailure(parser, "-5" + PHONE_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIdentifier_failure() {
        assertParseFailure(parser, " " + INVALID_PHONE_DESC,
                MESSAGE_INVALID_FORMAT); // invalid phone
        assertParseFailure(parser, " " + INVALID_EMAIL_DESC,
                MESSAGE_INVALID_FORMAT); // invalid email
    }
}
