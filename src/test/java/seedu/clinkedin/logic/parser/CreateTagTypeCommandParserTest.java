package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.model.tag.TagType;

public class CreateTagTypeCommandParserTest {

    private final CreateTagTypeCommandParser parser = new CreateTagTypeCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void stringLengthIsZero() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagTypeCommand.MESSAGE_USAGE));
    }

    @Test
    public void invalidArgs() {
        // Only tag type
        assertParseFailure(parser, "GPA",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagTypeCommand.MESSAGE_USAGE));

        // Only tag alias
        assertParseFailure(parser, "gpat", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CreateTagTypeCommand.MESSAGE_USAGE));
    }


    @Test
    public void validArgs() {
        Prefix newPrefix = new Prefix("newt/");
        TagType newTag = new TagType("NewTag", newPrefix);
        CreateTagTypeCommand expected = new CreateTagTypeCommand(newTag, newPrefix);
        assertParseSuccess(parser, "NewTag newt", expected);
    }
}
