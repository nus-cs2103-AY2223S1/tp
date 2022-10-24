package hobbylist.logic.parser;

import static hobbylist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static hobbylist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import hobbylist.logic.commands.FilterTagCommand;
import hobbylist.model.activity.TagMatchesKeywordPredicate;

public class FilterTagCommandParserTest {

    private FilterTagCommandParser parser = new FilterTagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTagCommand() {
        // no leading and trailing whitespaces
        FilterTagCommand expectedFilterTagCommand =
                new FilterTagCommand(new TagMatchesKeywordPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFilterTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFilterTagCommand);
    }

}
