package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROFILE_SECOND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROFILE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROFILE;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.DeleteProfilesFromEventCommand;

public class DeleteProfilesFromEventCommandParserTest {
    private static final String PROFILE_EMPTY = " " + PREFIX_PROFILE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteProfilesFromEventCommand.MESSAGE_USAGE);

    private DeleteProfilesFromEventCommandParser parser = new DeleteProfilesFromEventCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteProfilesFromEventCommand() {
        assertParseSuccess(parser, "1" + VALID_PROFILE_FIRST,
                new DeleteProfilesFromEventCommand(INDEX_FIRST_EVENT, Set.of(INDEX_FIRST_PROFILE)));
        assertParseSuccess(parser, "1" + VALID_PROFILE_SECOND,
                new DeleteProfilesFromEventCommand(INDEX_FIRST_EVENT, Set.of(INDEX_SECOND_PROFILE)));
    }

    @Test
    public void parse_invalidProfileIndexes_throwsParseException() {
        // empty profile index
        assertParseFailure(parser, "1" + PROFILE_EMPTY,
                DeleteProfilesFromEventCommand.MESSAGE_INVALID_PROFILE_INDEX);

        // invalid profile index
        assertParseFailure(parser, "1 pr/a",
                DeleteProfilesFromEventCommand.MESSAGE_INVALID_PROFILE_INDEX);

        // invalid profile index followed by valid profile index
        assertParseFailure(parser, "1 pr/0" + VALID_PROFILE_FIRST,
                DeleteProfilesFromEventCommand.MESSAGE_INVALID_PROFILE_INDEX);

        // valid profile index followed by invalid profile index
        assertParseFailure(parser, "1" + VALID_PROFILE_SECOND + " pr/-1",
                DeleteProfilesFromEventCommand.MESSAGE_INVALID_PROFILE_INDEX);
    }

    @Test
    public void parse_missingParts_throwsParseException() {
        // no index specified
        assertParseFailure(parser, "pr/1", MESSAGE_INVALID_FORMAT);

        // no profiles specified
        assertParseFailure(parser, "1", DeleteProfilesFromEventCommand.MESSAGE_ATTENDEES_NOT_DELETED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_throwsParseException() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_PROFILE_FIRST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_PROFILE_FIRST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/1", MESSAGE_INVALID_FORMAT);
    }
}
