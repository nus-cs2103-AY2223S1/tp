package seedu.clinkedin.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.clinkedin.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.AddToCommand;
import seedu.clinkedin.logic.commands.AddToCommand.UpdatePersonDescriptor;
import seedu.clinkedin.testutil.UpdatePersonDescriptorBuilder;

public class AddToCommandParserTest {

    private final AddToCommandParser parser = new AddToCommandParser();
    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidIndex_throwsParseException() {
        assertParseFailure(parser, "addTo",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, "addTo 1 n/name",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noAttribute_throwsParserException() {
        assertParseFailure(parser, "addTo",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddToCommand() {
        // no leading and trailing whitespaces
        UpdatePersonDescriptor updatePersonDescriptor = new UpdatePersonDescriptorBuilder().withNote("Test").build();
        AddToCommand expectedAddToCommand = new AddToCommand(Index.fromOneBased(1), updatePersonDescriptor);
        assertParseSuccess(parser, "1 note/Test", expectedAddToCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n \t1 note/Test\t", expectedAddToCommand);
    }
}
