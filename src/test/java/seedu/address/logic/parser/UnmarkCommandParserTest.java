package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnmarkCommand;
import seedu.address.model.person.Survey;

class UnmarkCommandParserTest {

    private UnmarkCommandParser parser = new UnmarkCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkCommand() {
        assertParseSuccess(parser, "1 s/Academic Survey",
                new UnmarkCommand(INDEX_FIRST_PERSON, new Survey("Academic Survey")));
    }

    @Test
    public void parse_invalidArgs_returnsUnmarkCommand() {
        assertParseFailure(parser, "vwqrrqsa",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkCommand.MESSAGE_USAGE));
    }
}
