package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.intrack.logic.commands.RemarkCommand;
import seedu.intrack.model.internship.Remark;

public class RemarkCommandParserTest {
    private RemarkCommandParser parser = new RemarkCommandParser();
    private final String nonEmptyRemark = "New remark.";

    @Test
    public void parse_validRemark_success() {
        // remark present
        String userInput = " " + PREFIX_REMARK + nonEmptyRemark;
        RemarkCommand expectedCommand = new RemarkCommand(new Remark(nonEmptyRemark));
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark not present
        userInput = " " + PREFIX_REMARK;
        expectedCommand = new RemarkCommand(new Remark(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }
}
