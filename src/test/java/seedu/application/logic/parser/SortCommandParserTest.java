package seedu.application.logic.parser;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.UNKNOWN_PREFIX;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.application.logic.parser.CliSyntax.PREFIX_REVERSE;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.SortByCompanyCommand;
import seedu.application.logic.commands.SortByDateCommand;
import seedu.application.logic.commands.SortByInterviewCommand;
import seedu.application.logic.commands.SortByPositionCommand;
import seedu.application.logic.commands.SortCommand;

class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_noOrder_returnsSortByDateCommand() {
        SortCommand expectedSortCommand = new SortByDateCommand(false);

        assertParseSuccess(parser, "     ", expectedSortCommand);

        expectedSortCommand = new SortByDateCommand(true);

        assertParseSuccess(parser, " " + PREFIX_REVERSE, expectedSortCommand);
    }

    @Test
    public void parse_companyOrder_success() {
        SortCommand expectedSortCommand = new SortByCompanyCommand(false);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "company", expectedSortCommand);

        // Test case-insensitivity
        assertParseSuccess(parser, " " + PREFIX_ORDER + "Company", expectedSortCommand);

        expectedSortCommand = new SortByCompanyCommand(true);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "company " + PREFIX_REVERSE, expectedSortCommand);
    }

    @Test
    public void parse_positionOrder_success() {
        SortCommand expectedSortCommand = new SortByPositionCommand(false);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "position", expectedSortCommand);

        // Test case-insensitivity
        assertParseSuccess(parser, " " + PREFIX_ORDER + "POSITION", expectedSortCommand);

        expectedSortCommand = new SortByPositionCommand(true);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "position " + PREFIX_REVERSE, expectedSortCommand);
    }

    @Test
    public void parse_dateOrder_success() {
        SortCommand expectedSortCommand = new SortByDateCommand(false);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "date", expectedSortCommand);

        // Test case-insensitivity
        assertParseSuccess(parser, " " + PREFIX_ORDER + "dAtE", expectedSortCommand);

        expectedSortCommand = new SortByDateCommand(true);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "date " + PREFIX_REVERSE, expectedSortCommand);
    }

    @Test
    public void parse_interviewOrder_success() {
        SortCommand expectedSortCommand = new SortByInterviewCommand(false);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "interview", expectedSortCommand);

        // Test case-insensitivity
        assertParseSuccess(parser, " " + PREFIX_ORDER + "InTeRvIeW", expectedSortCommand);

        expectedSortCommand = new SortByInterviewCommand(true);

        assertParseSuccess(parser, " " + PREFIX_ORDER + "interview " + PREFIX_REVERSE, expectedSortCommand);
    }

    @Test
    public void parse_invalidOrder_failure() {
        assertParseFailure(parser, " " + PREFIX_ORDER + "email",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        assertParseFailure(parser, " " + PREFIX_ORDER + " " + PREFIX_REVERSE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_unexpectedPrefix_failure() {

        // with order specified
        assertParseFailure(parser, " " + PREFIX_ORDER + "company" + UNKNOWN_PREFIX,
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + SortCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " " + UNKNOWN_PREFIX + PREFIX_ORDER + "company",
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + SortCommand.MESSAGE_USAGE);

        // without order specified
        assertParseFailure(parser, UNKNOWN_PREFIX,
                Parser.MESSAGE_UNKNOWN_PREFIX_FOUND + SortCommand.MESSAGE_USAGE);
    }
}
