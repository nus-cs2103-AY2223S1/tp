package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.model.tag.TagsContainsKeywordsPredicate;

public class FilterTagCommandParserTest {

    private FilterTagCommandParser parser = new FilterTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFilterTagCommand() {
        // no leading and trailing whitespaces
        FilterTagCommand expectedFilterTagCommand =
                new FilterTagCommand(new TagsContainsKeywordsPredicate("Alice"));
        assertParseSuccess(parser, "Alice", expectedFilterTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t", expectedFilterTagCommand);
    }

}
