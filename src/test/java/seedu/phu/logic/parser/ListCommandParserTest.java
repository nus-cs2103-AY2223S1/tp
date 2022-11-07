package seedu.phu.logic.parser;

import static seedu.phu.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.phu.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.phu.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.phu.logic.commands.ListCommand;
import seedu.phu.model.internship.ComparableCategory;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg() {
        assertParseSuccess(parser, "", new ListCommand());
    }

    @Test
    public void parse_validArgs() {
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "n",
                new ListCommand(ComparableCategory.NAME, false));
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "p",
                new ListCommand(ComparableCategory.POSITION, false));
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "pr",
                new ListCommand(ComparableCategory.APPLICATION_PROCESS, false));
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "d",
                new ListCommand(ComparableCategory.DATE, false));
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "company_name true",
                new ListCommand(ComparableCategory.NAME, true));
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "Company_Name true",
                new ListCommand(ComparableCategory.NAME, true));
        // Additional arguments given
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "n true something",
                new ListCommand(ComparableCategory.NAME, false));
        // More than one category given (last category is chosen)
        assertParseSuccess(parser, " " + PREFIX_CATEGORY + "n " + PREFIX_CATEGORY + "pr true",
                new ListCommand(ComparableCategory.APPLICATION_PROCESS, true));
    }


    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Invalid category
        assertParseFailure(parser, " " + PREFIX_CATEGORY + "Random 123",
                ComparableCategoryParser.EXCEPTION_MESSAGE);
        // Last category is invalid
        assertParseFailure(parser, " " + PREFIX_CATEGORY + "n " + PREFIX_CATEGORY + "web",
                ComparableCategoryParser.EXCEPTION_MESSAGE);
        // No category Given
        assertParseFailure(parser, " " + PREFIX_CATEGORY,
                ComparableCategoryParser.EXCEPTION_MESSAGE);
        // Invalid preamble
        assertParseFailure(parser, " 1234 " + PREFIX_CATEGORY + "n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }
}
