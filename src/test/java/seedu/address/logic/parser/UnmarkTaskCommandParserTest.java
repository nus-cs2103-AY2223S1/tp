package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.task.UnmarkTaskCommand;
import seedu.address.logic.parser.task.UnmarkTaskCommandParser;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class UnmarkTaskCommandParserTest {

    private UnmarkTaskCommandParser parser = new UnmarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsUnmarkTaskCommand() {
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptorBuilder().withCompletionStatus(false).build();

        assertParseSuccess(parser, "1", new UnmarkTaskCommand(INDEX_FIRST_TASK, editTaskDescriptor));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnmarkTaskCommand.MESSAGE_USAGE));
    }
}
