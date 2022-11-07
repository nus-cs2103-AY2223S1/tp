package seedu.uninurse.logic.parser;

import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_DATE_FIRST;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.uninurse.logic.commands.TasksOnCommand;
import seedu.uninurse.model.task.DateTime;

class TasksOnCommandParserTest {

    private final TasksOnCommandParser parser = new TasksOnCommandParser();

    @Test
    public void parse_validDate_success() {
        TasksOnCommand expectedCommand = new TasksOnCommand(DateTime.ofDate(VALID_DATE_FIRST));

        assertParseSuccess(parser, VALID_DATE_FIRST, expectedCommand);
    }

    @Test
    public void parse_invalidDate_failure() {
        // empty inputs
        assertParseFailure(parser, "", DateTime.MESSAGE_CONSTRAINTS);

        // contains invalid characters
        assertParseFailure(parser, "20-02-202z", DateTime.MESSAGE_CONSTRAINTS);

        // uses invalid separators
        assertParseFailure(parser, "22.02.2022", DateTime.MESSAGE_CONSTRAINTS);

        // uses invalid format
        assertParseFailure(parser, "2022-02-20", DateTime.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "02-22-2022", DateTime.MESSAGE_CONSTRAINTS);
    }
}
