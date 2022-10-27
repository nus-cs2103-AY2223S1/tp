package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PROFILE_FIRST;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PRACTICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.event.AddProfilesToEventCommand;
import seedu.address.logic.commands.event.EditEventCommand;

public class AddProfilesToEventCommandParserTest {
    private static final String PROFILE_EMPTY = " " + PREFIX_PROFILE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddProfilesToEventCommand.MESSAGE_USAGE);

    private AddProfilesToEventCommandParser parser = new AddProfilesToEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, "pr/1", MESSAGE_INVALID_FORMAT);

        // no profiles specified
        assertParseFailure(parser, "1", AddProfilesToEventCommand.MESSAGE_ATTENDEES_NOT_ADDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PROFILE_FIRST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PROFILE_FIRST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/1", MESSAGE_INVALID_FORMAT);
    }

}
