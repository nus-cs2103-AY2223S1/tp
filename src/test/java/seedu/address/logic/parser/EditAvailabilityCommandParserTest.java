package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AvailabilityCommand;
import seedu.address.model.person.position.TeachingAssistant;


public class EditAvailabilityCommandParserTest {

    private AvailabilityCommandParser parser = new AvailabilityCommandParser();

    @Test
    public void parse_validArgs_returnsAvailabilityCommand() {
        assertParseSuccess(parser, "1 avail/Available", new AvailabilityCommand(INDEX_FIRST_PERSON, "Available"));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "1 avail/AAvailable", String.format(TeachingAssistant.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AvailabilityCommand.MESSAGE_USAGE));
    }

}
