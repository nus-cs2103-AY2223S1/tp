package seedu.classify.logic.parser;

import static seedu.classify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.classify.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.classify.logic.commands.ViewStatsCommand;
import seedu.classify.model.exam.Exam;
import seedu.classify.model.student.Class;

public class ViewStatsCommandParserTest {

    private ViewStatsCommandParser parser = new ViewStatsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCommandFormat_throwsParseException() {
        assertParseFailure(parser, " class/4a exam/ca1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStatsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidClass_throwsParseException() {
        assertParseFailure(parser, " class/.,., exam/ca1 filter/on", Class.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidExam_throwsParseException() {
        assertParseFailure(parser, " class/4a exam/cfeff1 filter/off", Exam.MESSAGE_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidFilter_throwsParseException() {
        assertParseFailure(parser, " class/4a exam/ca1 filter/sdfsof", ParserUtil.MESSAGE_INVALID_FILTER);
    }

    @Test
    public void parse_validArgs_returnsViewStatsCommand() {
        ViewStatsCommand expectedCommand = new ViewStatsCommand(new Class("4a"), "CA1", true);
        assertParseSuccess(parser, " class/4a exam/ca1 filter/on", expectedCommand);

        // no leading or trailing whitespaces
        assertParseSuccess(parser, " class/4a \t exam/\tca1 filter/on \n", expectedCommand);
    }
}
