package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_PROGRESS_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_PROGRESS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GradeProgressCommand;
import seedu.address.model.person.GradeProgress;

public class GradeProgressCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeProgressCommand.MESSAGE_USAGE);

    private GradeProgressCommandParser parser = new GradeProgressCommandParser();

    @Test
    public void parse_validArgs_returnsGradeProgressCommand() {
        String userInput = "1 " + PREFIX_GRADE_PROGRESS + VALID_GRADE_PROGRESS_AMY;
        GradeProgressCommand expectedCommand = new GradeProgressCommand(INDEX_FIRST_PERSON,
                new GradeProgress(VALID_GRADE_PROGRESS_AMY));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_noPrefixField_failure() {
        assertParseFailure(parser, "1 " + VALID_GRADE_PROGRESS_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_emptyField_failure() {
        assertParseFailure(parser, "1 " + PREFIX_GRADE_PROGRESS, GradeProgress.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_GRADE_PROGRESS + " ", GradeProgress.MESSAGE_CONSTRAINTS);
    }
}
