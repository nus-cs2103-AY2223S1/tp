package seedu.classify.logic.parser;

import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.classify.logic.commands.ViewClassCommand;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;

public class ViewClassCommandParserTest {

    private ViewClassCommandParser parser = new ViewClassCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewClassCommand() {
        // no leading and trailing whitespaces
        ViewClassCommand expectedViewClassCommand =
                new ViewClassCommand(new ClassPredicate(new Class("CLASS1")));
        assertParseSuccess(parser, "CLASS1", expectedViewClassCommand);
    }

}
