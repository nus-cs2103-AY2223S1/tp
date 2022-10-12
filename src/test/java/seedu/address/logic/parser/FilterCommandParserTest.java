package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FilterCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    /*@Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        assertParseSuccess(parser, "friends colleagues",
                new FilterCommand(new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"))));

    }*/
}
