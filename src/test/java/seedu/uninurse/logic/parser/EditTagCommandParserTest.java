package seedu.uninurse.logic.parser;

import static seedu.uninurse.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_ATTRIBUTE;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalTags.TAG_ELDERLY;
import static seedu.uninurse.testutil.TypicalTags.TYPICAL_TAG_ELDERLY;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.EditTagCommand;

/**
 * Contains unit tests for {@code EditTagCommandParser}.
 */
public class EditTagCommandParserTest {
    private final EditTagCommandParser parser = new EditTagCommandParser();

    @Test
    public void parse_nullArgs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_validArgs_success() {
        String args = INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " "
                + PREFIX_TAG + TYPICAL_TAG_ELDERLY;
        assertParseSuccess(parser, args,
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY));
        assertParseSuccess(parser, " " + args + " ",
                new EditTagCommand(INDEX_FIRST_PERSON, INDEX_FIRST_ATTRIBUTE, TAG_ELDERLY));

    }

    @Test
    public void parse_invalidPatientIndex_failure() {
        String userInput = " " + INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_TAG
                + TYPICAL_TAG_ELDERLY;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "a" + userInput, expectedMessage);
        assertParseFailure(parser, "0" + userInput, expectedMessage);
    }

    @Test
    public void parse_missingPatientIndex_failure() {
        String userInput = INDEX_FIRST_ATTRIBUTE.getOneBased() + " " + PREFIX_TAG + TYPICAL_TAG_ELDERLY;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidTagIndex_failure() {
        String userInput = " " + PREFIX_TAG + TYPICAL_TAG_ELDERLY;
        assertParseFailure(parser, "1 a" + userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 0" + userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingTagIndex_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + TYPICAL_TAG_ELDERLY;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_emptyTag_failure() {
        String userInput = "1 1 " + PREFIX_TAG;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingTagPrefix_failure() {
        String expectedMessage = EditTagCommandParser.MESSAGE_MISSING_TAG;
        assertParseFailure(parser, "1 1" , expectedMessage);
    }
}
