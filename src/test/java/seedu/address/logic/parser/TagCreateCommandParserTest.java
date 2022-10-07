package seedu.address.logic.parser;


import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TagCreateCommand;
import seedu.address.model.tag.Tag;

class TagCreateCommandParserTest {

    private TagCreateCommandParser parser = new TagCreateCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_success() {
        String userInput = TAG_DESC_FRIEND;
        TagCreateCommand expectedCommand = new TagCreateCommand(new Tag(VALID_TAG_FRIEND));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
