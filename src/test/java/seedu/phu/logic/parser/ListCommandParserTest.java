package seedu.phu.logic.parser;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.ListCommand;
import seedu.phu.model.internship.ComparableCategory;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;


public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg() {
        assertParseSuccess(parser, "", new ListCommand());
    }

    @Test
    public void parse_validArgs() {
        assertParseSuccess(parser, "c/n true", new ListCommand(ComparableCategory.NAME, true));
        assertParseSuccess(parser, "c/p true", new ListCommand(ComparableCategory.POSITION, true));
        assertParseSuccess(parser, "c/pr", new ListCommand(ComparableCategory.APPLICATION_PROCESS, false));
        assertParseSuccess(parser, "c/date", new ListCommand(ComparableCategory.DATE, false));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid category
        assertParseFailure(parser, "c/Random 123", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        // No category Given
        assertParseFailure(parser, "c/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));

        // Additional arguments given
        assertParseFailure(parser, "c/n true something",  String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

}
