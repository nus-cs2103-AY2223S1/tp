package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;


import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortPatientCommand;

public class SortPatientCommandParserTest {
    private SortPatientCommandParser parser = new SortPatientCommandParser();

    @Test
    public void parse_validArgs_returnsSortPatientCommand() {
        assertParseFailure(parser, " c/name", SortPatientCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/name o/asc", new SortPatientCommand("name", true));
        assertParseSuccess(parser, " c/name o/desc", new SortPatientCommand("name", false));
        assertParseFailure(parser, " c/n", SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/n o/asc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/n o/desc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/phone", SortPatientCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/phone o/asc", new SortPatientCommand("phone", true));
        assertParseSuccess(parser, " c/phone o/desc", new SortPatientCommand("phone", false));
        assertParseFailure(parser, " c/p", SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/p o/asc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/p o/desc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/email", SortPatientCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/email o/asc", new SortPatientCommand("email", true));
        assertParseSuccess(parser, " c/email o/desc", new SortPatientCommand("email", false));
        assertParseFailure(parser, " c/e", SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/e o/asc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/e o/desc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/address", SortPatientCommand.MESSAGE_USAGE);
        assertParseSuccess(parser, " c/address o/asc", new SortPatientCommand("address", true));
        assertParseSuccess(parser, " c/address o/desc", new SortPatientCommand("address", false));
        assertParseFailure(parser, " c/a", SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/a o/asc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/a o/desc",  SortPatientCommand.MESSAGE_USAGE);
        assertParseFailure(parser, " c/tag",  SortPatientCommand.MESSAGE_USAGE);
    }
}
