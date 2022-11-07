package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.project.FindProjectCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class FindProjectCommandParser {
    private ProjectCommandParser parser = new ProjectCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "-f", "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindProjectCommand.MESSAGE_FIND_PROJECT_USAGE));
    }

    @Test
    public void parse_argsWithNoPefix_throwsParseException() {
        assertParseFailure(parser, "-f", "abcd", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindProjectCommand.MESSAGE_FIND_PROJECT_USAGE));
    }
}
