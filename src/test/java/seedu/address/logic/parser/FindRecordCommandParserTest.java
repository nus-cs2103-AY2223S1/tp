package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.model.record.RecordContainsKeywordsPredicate;

public class FindRecordCommandParserTest {

    private FindRecordCommandParser parser = new FindRecordCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindRecordCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, " m/ r/cold", FindRecordCommand.MESSAGE_EMPTY_PREFIX);
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        assertParseFailure(parser, " d/10-10-2010 1400", FindRecordCommand.MESSAGE_INVALID_FIND_DATE_FORMAT);
    }

    @Test
    public void parse_validArgsWithOnePrefix_returnsFindRecordCommand() {
        // no leading and trailing whitespaces
        FindRecordCommand expectedFindRecordCommand =
                new FindRecordCommand(new RecordContainsKeywordsPredicate(
                        Arrays.asList("flu", "cold"), Arrays.asList(), ""));
        assertParseSuccess(parser, " r/flu cold", expectedFindRecordCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " r/flu \n \t cold  \t", expectedFindRecordCommand);
    }

    @Test
    public void parse_validArgsWithMultiplePrefix_returnsFindRecordCommand() {
        // no leading and trailing whitespaces
        List<String> recordKeywords = Arrays.asList("flu", "cold");
        List<String> medicationKeywords = Arrays.asList("paracetemol", "panadol");
        String dateKeyword = "12-2012";
        FindRecordCommand expectedFindRecordCommand =
                new FindRecordCommand(
                        new RecordContainsKeywordsPredicate(recordKeywords, medicationKeywords, dateKeyword));
        assertParseSuccess(parser, " r/flu cold m/paracetemol panadol d/12-2012", expectedFindRecordCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " r/flu \n \t cold  \t m/paracetemol panadol d/12-2012\t",
                expectedFindRecordCommand);
    }
}
