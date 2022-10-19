package soconnect.logic.parser.tagcommandparser;

import static soconnect.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static soconnect.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static soconnect.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static soconnect.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static soconnect.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import soconnect.logic.commands.tagcommands.TagEditCommand;
import soconnect.logic.parser.tagcommandparsers.TagEditCommandParser;
import soconnect.model.tag.Tag;

class TagEditCommandParserTest {

    private TagEditCommandParser parser = new TagEditCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        // Invalid old Tag.
        assertParseFailure(parser, INVALID_TAG_DESC + TAG_DESC_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // Invalid new Tag.
        assertParseFailure(parser, TAG_DESC_FRIEND + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // Invalid Tags
        assertParseFailure(parser, INVALID_TAG_DESC + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    @Disabled
    public void parse_success() {
        String userInput = TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        TagEditCommand expectedCommand =
                new TagEditCommand(new Tag(VALID_TAG_FRIEND), new Tag(VALID_TAG_HUSBAND));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
