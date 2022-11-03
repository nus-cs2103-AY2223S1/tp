package seedu.boba.logic.parser;

import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.boba.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.boba.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.boba.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalEmails.EMAIL_SECOND_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.IncreaseCommand;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;

public class IncreaseCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, IncreaseCommand.MESSAGE_USAGE);

    private static final String MESSAGE_INVALID_REWARD = Reward.MESSAGE_MAX_INTEGER;

    private IncreaseCommandParser parser = new IncreaseCommandParser();

    @Test
    public void parse_validPhone_success() {
        Phone targetPhone = PHONE_SECOND_PERSON;
        String userInput = " 10 " + PREFIX_PHONE + targetPhone;
        IncreaseCommand expectedCommand = new IncreaseCommand(targetPhone, "10");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_moreThanOnePhone_failure() {
        String userInput = " 10 " + PREFIX_PHONE + PHONE_FIRST_PERSON + " " + PREFIX_PHONE + PHONE_SECOND_PERSON;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_moreThanOneEmail_failure() {
        String userInput = " 10 " + PREFIX_EMAIL + EMAIL_FIRST_PERSON + " " + PREFIX_EMAIL + EMAIL_SECOND_PERSON;
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validEmail_success() {
        Email targetEmail = EMAIL_SECOND_PERSON;
        String userInput = " 10 " + PREFIX_EMAIL + targetEmail;
        IncreaseCommand expectedCommand = new IncreaseCommand(targetEmail, "10");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

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
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_REWARD);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_REWARD);
    }

    @Test
    public void parse_invalidIdentifier_failure() {
        assertParseFailure(parser, " " + INVALID_PHONE_DESC,
                MESSAGE_INVALID_FORMAT); // invalid phone
        assertParseFailure(parser, " " + INVALID_EMAIL_DESC,
                MESSAGE_INVALID_FORMAT); // invalid email
    }
}
