package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;


import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortAppointmentCommand;

public class SortAppointmentParserTest {
    private SortAppointmentCommandParser parser = new SortAppointmentCommandParser();

    @Test
    public void parse_validArgs_returnsSortAppointmentCommand() {
        assertParseFailure(parser, " c/name", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/name o/asc", new SortAppointmentCommand("name", true));
        assertParseSuccess(parser, " c/name o/desc", new SortAppointmentCommand("name", false));
        assertParseFailure(parser, " c/n", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/n o/asc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/n o/desc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/test", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/test o/asc", new SortAppointmentCommand("test", true));
        assertParseSuccess(parser, " c/test o/desc", new SortAppointmentCommand("test", false));
        assertParseFailure(parser, " c/t", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/t o/asc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/t o/desc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/slot", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/slot o/asc", new SortAppointmentCommand("slot", true));
        assertParseSuccess(parser, " c/slot o/desc", new SortAppointmentCommand("slot", false));
        assertParseFailure(parser, " c/s", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/s o/asc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/s o/desc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/doctor", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/doctor o/asc", new SortAppointmentCommand("doctor", true));
        assertParseSuccess(parser, " c/doctor o/desc", new SortAppointmentCommand("doctor", false));
        assertParseFailure(parser, " c/d", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/d o/asc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/d o/desc", SortAppointmentCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/tag", SortAppointmentCommand.MESSAGE_USAGE);
    }
}
