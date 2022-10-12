package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static soconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import soconnect.commons.core.index.Index;
import soconnect.logic.commands.TagAddCommand;
import soconnect.model.tag.Tag;


class TagAddCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagAddCommand.MESSAGE_USAGE);

    private TagAddCommandParser parser = new TagAddCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TAG_DESC_FRIEND , MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", TagAddCommand.MESSAGE_NO_TAG);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);

        //zero index
        assertParseFailure(parser, "0" + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);

        // missing tag
        assertParseFailure(parser, "1", TagAddCommand.MESSAGE_NO_TAG);
    }

    @Test
    @Disabled
    public void parse_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        TagAddCommand expectedCommand = new TagAddCommand(targetIndex, new Tag(VALID_TAG_FRIEND));

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

}
