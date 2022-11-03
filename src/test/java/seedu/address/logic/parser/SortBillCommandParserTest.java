package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortBillCommand;

public class SortBillCommandParserTest {
    private SortBillCommandParser parser = new SortBillCommandParser();

    @Test
    public void parse_validArgs_returnsSortBillCommand() {
        assertParseFailure(parser, " c/name", SortBillCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/name o/asc", new SortBillCommand("name", true));
        assertParseSuccess(parser, " c/name o/desc", new SortBillCommand("name", false));
        assertParseFailure(parser, " c/n", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/n o/asc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/n o/desc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/amount", SortBillCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/amount o/asc", new SortBillCommand("amount", true));
        assertParseSuccess(parser, " c/amount o/desc", new SortBillCommand("amount", false));
        assertParseFailure(parser, " c/a", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/a o/asc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/a o/desc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/date", SortBillCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/date o/asc", new SortBillCommand("date", true));
        assertParseSuccess(parser, " c/date o/desc", new SortBillCommand("date", false));
        assertParseFailure(parser, " c/d", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/d o/asc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/d o/desc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/status", SortBillCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/status o/asc", new SortBillCommand("status", true));
        assertParseSuccess(parser, " c/status o/desc", new SortBillCommand("status", false));
        assertParseFailure(parser, " c/s", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/s o/asc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/s o/desc", SortBillCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/tag", SortBillCommand.MESSAGE_USAGE);
    }
}
