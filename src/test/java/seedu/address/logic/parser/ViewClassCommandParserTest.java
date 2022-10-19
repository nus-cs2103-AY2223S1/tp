package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewClassCommand;
import seedu.address.model.student.Class;
import seedu.address.model.student.ClassPredicate;

public class ViewClassCommandParserTest {

    private ViewClassCommandParser parser = new ViewClassCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewClassCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        ViewClassCommand expectedViewClassCommand =
                new ViewClassCommand(new ClassPredicate(new Class("CLASS1")));
        assertParseSuccess(parser, "CLASS1", expectedViewClassCommand);
    }

}
