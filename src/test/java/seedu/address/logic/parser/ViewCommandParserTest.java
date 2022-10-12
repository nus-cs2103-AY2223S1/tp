package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.student.Id;
import seedu.address.model.student.IdPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        ViewCommand expectedViewCommand =
                new ViewCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, " nm/Alice Bob", expectedViewCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " nm/\n Alice \n \t Bob  \t", expectedViewCommand);
    }

    @Test
    public void parse_validIdArgs_returnsViewCommand() {
        ViewCommand expectedViewCommand =
                new ViewCommand(new IdPredicate(new Id("123A")));
        assertParseSuccess(parser, " id/123a", expectedViewCommand);

        assertParseSuccess(parser, " id/ \t 123a \n", expectedViewCommand);
    }

}
