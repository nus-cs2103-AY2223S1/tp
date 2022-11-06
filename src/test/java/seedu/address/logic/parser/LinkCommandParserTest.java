package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.LinkCommand;

public class LinkCommandParserTest {

    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_validPersonIndexInternshipIndex_returnsLinkCommand() {
        Command expectedCommand = new LinkCommand(INDEX_FIRST_PERSON, INDEX_FIRST_INTERNSHIP);
        assertParseSuccess(parser, " p/1 i/1", expectedCommand);
        assertParseSuccess(parser, " i/1 p/1", expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkCommand.MESSAGE_USAGE));
    }
}
