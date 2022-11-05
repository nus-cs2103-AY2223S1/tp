package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXAM;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkExamCommand;

public class LinkExamCommandParserTest {
    private LinkExamCommandParser parser = new LinkExamCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index examIndex = INDEX_FIRST_EXAM;
        Index taskIndex = INDEX_FIRST_TASK;

        String allFieldsPresent = " " + PREFIX_EXAM_INDEX + "1" + " "
                + PREFIX_TASK_INDEX + "1";
        //Valid input with all fields present
        assertParseSuccess(parser, allFieldsPresent,
                new LinkExamCommand(examIndex, taskIndex));

        //Valid input with whitespace between prefix and argument
        String allFieldsPresentWithWhitespace = " " + PREFIX_EXAM_INDEX + "\t   "
                + "1" + "    " + PREFIX_TASK_INDEX + "    \t" + "1";
        assertParseSuccess(parser, allFieldsPresentWithWhitespace,
                new LinkExamCommand(examIndex, taskIndex));
    }

    @Test
    public void parse_missingFields_failure() {
        String missingExamPrefix = " 2 " + PREFIX_TASK_INDEX + "2";
        String missingTaskPrefix = " " + PREFIX_EXAM_INDEX + "2"
                + " 2";
        String missingAllPrefixes = " 2 2";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                LinkExamCommand.MESSAGE_USAGE);
        //Missing exam prefix
        assertParseFailure(parser, missingExamPrefix, expectedMessage);

        //Missing task prefix
        assertParseFailure(parser, missingTaskPrefix, expectedMessage);

        //Missing all prefixes
        assertParseFailure(parser, missingAllPrefixes, expectedMessage);
    }

    @Test
    public void parse_invalidFields_failure() {
        String nonEmptyPreamble = " gibberish" + PREFIX_EXAM_INDEX + "2" + " "
                + PREFIX_TASK_INDEX + "2";
        String negativeTaskIndex = " " + PREFIX_EXAM_INDEX + "2" + " "
                + PREFIX_TASK_INDEX + "-1";
        String largeNumberTaskIndex = " " + PREFIX_EXAM_INDEX + "2" + " "
                + PREFIX_TASK_INDEX + "99999999999999999999999";
        String nonNumberTaskIndex = " " + PREFIX_EXAM_INDEX + "2" + " "
                + PREFIX_TASK_INDEX + "NOTHING";
        String negativeExamIndex = " " + PREFIX_EXAM_INDEX + "-2" + " "
                + PREFIX_TASK_INDEX + "1";
        String largeNumberExamIndex = " " + PREFIX_EXAM_INDEX + "99999999999999999999999"
                + " " + PREFIX_TASK_INDEX + "1";
        String nonNumberExamIndex = " " + PREFIX_EXAM_INDEX + "NOTHING" + " "
                + PREFIX_TASK_INDEX + "1";

        //Non-empty preamble
        assertParseFailure(parser, nonEmptyPreamble, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                LinkExamCommand.MESSAGE_USAGE));

        //Negative task index
        assertParseFailure(parser, negativeTaskIndex, LinkExamCommandParser.TASK_INDEX_INVALID);

        //Out of bounds large number(Greater than INT_MAX_VALUE) for task index
        assertParseFailure(parser, largeNumberTaskIndex, LinkExamCommandParser.TASK_INDEX_INVALID);

        //Task index is not a number
        assertParseFailure(parser, nonNumberTaskIndex, LinkExamCommandParser.TASK_INDEX_INVALID);

        //Negative exam index
        assertParseFailure(parser, negativeExamIndex, LinkExamCommandParser.EXAM_INDEX_INVALID);

        //Out of bounds large number(Greater than INT_MAX_VALUE) for exam index
        assertParseFailure(parser, largeNumberExamIndex, LinkExamCommandParser.EXAM_INDEX_INVALID);

        //Exam index is not a number
        assertParseFailure(parser, nonNumberExamIndex, LinkExamCommandParser.EXAM_INDEX_INVALID);
    }
}
