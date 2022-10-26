package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.SortPersonCommand;

public class SortPersonCommandParserTest {

    private SortPersonCommandParser parser = new SortPersonCommandParser();

    @Test
    public void parse_validArgsPersonName_returnsSortPersonCommand() {
        Command expectedCommand = new SortPersonCommand(SortPersonCommand.Criteria.NAME);
        assertParseSuccess(parser, PREFIX_NAME.getPrefix(), expectedCommand);
    }

    @Test
    public void parse_validArgsCompanyName_returnsSortPersonCommand() {
        Command expectedCommand = new SortPersonCommand(SortPersonCommand.Criteria.COMPANY_NAME);
        assertParseSuccess(parser, PREFIX_COMPANY_NAME.getPrefix(), expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortPersonCommand.MESSAGE_USAGE));
    }
}
