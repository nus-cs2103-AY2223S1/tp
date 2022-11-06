package seedu.foodrem.logic.parser.tagcommandparser;

import static seedu.foodrem.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.foodrem.logic.commands.CommandTestUtil.VALID_DESC_TAG_NAME_FRUITS;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.foodrem.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.tagcommands.DeleteTagCommand;
import seedu.foodrem.testutil.TypicalTags;

/**
 * A class to test the DeleteTagCommandParser.
 */
public class DeleteTagCommandParserTest {
    private final DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, VALID_DESC_TAG_NAME_FRUITS, new DeleteTagCommand(TypicalTags.FRUITS));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.getUsage()));
    }
}
