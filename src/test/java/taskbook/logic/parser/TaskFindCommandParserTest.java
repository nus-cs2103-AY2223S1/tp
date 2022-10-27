package taskbook.logic.parser;

import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskFindCommand;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.logic.parser.tasks.TaskFindCommandParser;
import taskbook.model.task.Task;

public class TaskFindCommandParserTest {

    private TaskFindCommandParser parser = new TaskFindCommandParser();
    private Predicate<Task> dummy = t -> true;
    private TaskFindCommand control = new TaskFindCommand(dummy, "test", "FROM", "X");

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, " q/test x/X a/FROM", control);
    }

    @Test
    public void parse_allFieldsPresent_failure() {
        TaskFindCommand test = new TaskFindCommand(dummy, "testy", "FROM", "X");
        assert !control.equals(test);
    }

    @Test
    public void parse_onlyOneField_success() {
        TaskFindCommand test = new TaskFindCommand(dummy, "test", null, null);
        assertParseSuccess(parser, " q/test", test);
    }

    @Test
    public void parse_onlyOneField_failure() throws ParseException {
        TaskFindCommand test1 = parser.parse(" q/test");
        TaskFindCommand test2 = new TaskFindCommand(dummy, null, "FROM", null);
        assert !test1.equals(test2);
    }

    @Test
    public void parse_noFields_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                "Find arguments are empty.\n",
                TaskFindCommand.MESSAGE_USAGE));
    }
}
