package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_PASSWORD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PasswordCommand;

public class PasswordCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, PasswordCommand.MESSAGE_USAGE);

    private static final String OLD_PASSWORD = "foobar";
    private static final String NEW_PASSWORD = "barfoo";

    private PasswordCommandParser parser = new PasswordCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no new password specified
        assertParseFailure(parser, " " + PREFIX_OLD_PASSWORD + OLD_PASSWORD, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 y/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = " " + PREFIX_OLD_PASSWORD + OLD_PASSWORD + " " + PREFIX_NEW_PASSWORD + NEW_PASSWORD;
        PasswordCommand expectedCommand = new PasswordCommand(OLD_PASSWORD, NEW_PASSWORD);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        String userInput = " " + PREFIX_NEW_PASSWORD + NEW_PASSWORD;
        PasswordCommand expectedCommand = new PasswordCommand(NEW_PASSWORD);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = " " + PREFIX_OLD_PASSWORD + "password " + PREFIX_OLD_PASSWORD + OLD_PASSWORD + " "
                + PREFIX_NEW_PASSWORD + "password " + PREFIX_NEW_PASSWORD + NEW_PASSWORD;
        PasswordCommand expectedCommand = new PasswordCommand(OLD_PASSWORD, NEW_PASSWORD);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
