package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.order.Order;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.SortPersonListDescriptor;

/**
 * Test sort parser
 */
class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_invalidPreamble_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "123", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_presentFieldEmptyOrder_throwsParseException() {
        assertParseFailure(parser, "n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "m/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }
    @Test
    public void parse_presentOrderEmptyField_throwsParseException() {
        assertParseFailure(parser, "A-Z", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_NOT_SELECTED));
        assertParseFailure(parser, "Z-A", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MESSAGE_NOT_SELECTED));
    }
    @Test
    public void parse_bothFieldChosen_throwsParseException() {
        assertParseFailure(parser, "A-Z n/ m/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MULTIPLE_SELECTED));
        assertParseFailure(parser, "Z-A n/ m/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MULTIPLE_SELECTED));
        assertParseFailure(parser, "A-Z m/ n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MULTIPLE_SELECTED));
        assertParseFailure(parser, "Z-A m/ n/", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SortCommand.MULTIPLE_SELECTED));
    }
    @Test
    public void parse_validIncreasingArgsName_returnsSortCommand() {
        SortPersonListDescriptor sortPersonListDescriptor = new SortPersonListDescriptor();
        sortPersonListDescriptor.setName(true);
        Order increasing = Order.lexicographicalOrder("A-Z");
        SortCommand expected = new SortCommand(increasing, sortPersonListDescriptor);
        assertParseSuccess(parser, "A-Z n/", expected);
        assertParseSuccess(parser, "A-Z n/abc", expected);
    }
    @Test
    public void parse_validDecreasingArgsName_returnsSortCommand() {
        SortPersonListDescriptor sortPersonListDescriptor = new SortPersonListDescriptor();
        sortPersonListDescriptor.setName(true);
        Order increasing = Order.lexicographicalOrder("Z-A");
        SortCommand expected = new SortCommand(increasing, sortPersonListDescriptor);
        assertParseSuccess(parser, "Z-A n/", expected);
        assertParseSuccess(parser, "Z-A n/abc", expected);
    }
    @Test
    public void parse_validIncreasingArgsModuleCode_returnsSortCommand() {
        SortPersonListDescriptor sortPersonListDescriptor = new SortPersonListDescriptor();
        sortPersonListDescriptor.setModuleCode(true);
        Order increasing = Order.lexicographicalOrder("A-Z");
        SortCommand expected = new SortCommand(increasing, sortPersonListDescriptor);
        assertParseSuccess(parser, "A-Z m/", expected);
        assertParseSuccess(parser, "A-Z m/abc", expected);
    }
    @Test
    public void parse_validDecreasingArgsModuleCode_returnsSortCommand() {
        SortPersonListDescriptor sortPersonListDescriptor = new SortPersonListDescriptor();
        sortPersonListDescriptor.setModuleCode(true);
        Order increasing = Order.lexicographicalOrder("Z-A");
        SortCommand expected = new SortCommand(increasing, sortPersonListDescriptor);
        assertParseSuccess(parser, "Z-A m/", expected);
        assertParseSuccess(parser, "Z-A m/abc", expected);
    }
}
