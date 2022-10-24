package seedu.address.logic.parser.profile;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.profile.DeleteProfileCommand.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.profile.DeleteProfileCommand;

public class DeleteProfileCommandParserTest {

    private DeleteProfileCommandParser parser = new DeleteProfileCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteProfileCommand() {
        assertParseSuccess(parser, "1", new DeleteProfileCommand(INDEX_FIRST_PROFILE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProfileCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingArgs_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_MISSING_INDEX, DeleteProfileCommand.MESSAGE_USAGE));
    }
}
