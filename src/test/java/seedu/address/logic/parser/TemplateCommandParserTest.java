package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TemplateCommand;

class TemplateCommandParserTest {

    private TemplateCommandParser parser = new TemplateCommandParser();

    @Test
    void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", TemplateCommand.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "professor", TemplateCommand.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validProfInput_returnsTemplateCommand() {
        TemplateCommand expectedTemplateCommand = new TemplateCommand("prof");
        assertParseSuccess(parser, "   prof   ", expectedTemplateCommand);
        assertParseSuccess(parser, "prof ", expectedTemplateCommand);
        assertParseSuccess(parser, " prof ", expectedTemplateCommand);
    }

    @Test
    public void parse_validTAInput_returnsTemplateCommand() {
        TemplateCommand expectedTemplateCommand = new TemplateCommand("ta");
        assertParseSuccess(parser, "   ta   ", expectedTemplateCommand);
        assertParseSuccess(parser, "ta ", expectedTemplateCommand);
        assertParseSuccess(parser, " ta ", expectedTemplateCommand);
    }

    @Test
    public void parse_validStudentInput_returnsTemplateCommand() {
        TemplateCommand expectedTemplateCommand = new TemplateCommand("student");
        assertParseSuccess(parser, "   student   ", expectedTemplateCommand);
        assertParseSuccess(parser, "student ", expectedTemplateCommand);
        assertParseSuccess(parser, " student ", expectedTemplateCommand);
    }
}
