package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAttributeCommand;

public class DeleteAttributeCommandParserTest {
    private final DeleteAttributeCommandParser deleteAttributeCommandParser = new DeleteAttributeCommandParser();

    @Test
    public void parse_noInput_failure() {
        assertParseFailure(deleteAttributeCommandParser, "",
                DeleteAttributeCommandParser.MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidArgument_failure() {
        assertParseFailure(deleteAttributeCommandParser, "bitbucket",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteAttributeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleArguments_success() {
        // Providing multiple arguments will result in only the last argument being deleted.
        String multipleArgs = "delete github email role";
        assertParseSuccess(deleteAttributeCommandParser, multipleArgs,
                new DeleteAttributeCommand(PREFIX_ROLE));
    }

    @Test
    public void parse_singleArgument_success() {
        // Single Argument results in success
        String arg = "delete role";
        assertParseSuccess(deleteAttributeCommandParser, arg,
                new DeleteAttributeCommand(PREFIX_ROLE));
    }
}
