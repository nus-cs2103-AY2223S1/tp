package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.model.tag.Tag.MESSAGE_CONSTRAINTS;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalTags.TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_ELDERLY;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.AddTagCommand;

/**
 * Contains unit tests for AddTagCommandParser.
 */
public class AddTagCommandParserTest {
    private final AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        String args = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + TYPICAL_TAG_ELDERLY;
        assertParseSuccess(parser, args, new AddTagCommand(INDEX_FIRST_PERSON, TAG_ELDERLY));
        assertParseSuccess(parser, " " + args + " ",
                new AddTagCommand(INDEX_FIRST_PERSON, TAG_ELDERLY));
    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        assertParseFailure(parser, "a " + PREFIX_TAG + TYPICAL_TAG_ELDERLY, MESSAGE_INVALID_INDEX);
        assertParseFailure(parser, "0 " + PREFIX_TAG + TYPICAL_TAG_ELDERLY, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_missingPatientIndex_failure() {
        assertParseFailure(parser, PREFIX_TAG + TYPICAL_TAG_ELDERLY, MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_emptyTag_failure() {
        String args = "1 " + PREFIX_TAG;
        assertParseFailure(parser, args, MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_missingTagPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1" , expectedMessage);
    }
}
