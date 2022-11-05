package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.task.MarkTaskCommand;
import seedu.address.logic.parser.task.MarkTaskCommandParser;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class MarkTaskCommandParserTest {

    private MarkTaskCommandParser parser = new MarkTaskCommandParser();

    @Test
    public void parse_validArgs_returnsMarkTaskCommand() {
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptorBuilder().withCompletionStatus(true).build();

        assertParseSuccess(parser, "1", new MarkTaskCommand(INDEX_FIRST_TASK, editTaskDescriptor));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkTaskCommand.MESSAGE_USAGE));
    }
}
