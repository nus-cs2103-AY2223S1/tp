package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.FindCommand;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.model.applicant.ScholarshipContainsKeywordsPredicate;
import seedu.trackascholar.model.major.MajorContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, "Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, "nonEmptyPreamble n/Alice",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_keywordMissing_throwsParseException() {
        // missing name keyword
        assertParseFailure(parser, " n/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // missing scholarship keyword
        assertParseFailure(parser, " s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        // missing majors keyword
        assertParseFailure(parser, " m/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        //missing multiple keywords
        assertParseFailure(parser, " n/ s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

        //missing one prefix
        assertParseFailure(parser, "Alice s/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_validArgs_returnsSearchCommand() {
        NameContainsKeywordsPredicate nameKeywordPredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        ScholarshipContainsKeywordsPredicate scholarshipKeywordPredicate =
                new ScholarshipContainsKeywordsPredicate(Arrays.asList("Merit", "Scholarship"));
        MajorContainsKeywordsPredicate majorKeywordPredicate =
                new MajorContainsKeywordsPredicate(Arrays.asList("Computing", "Business"));

        FindCommand expectedFindNameCommand = new FindCommand(nameKeywordPredicate);
        FindCommand expectedFindScholarshipCommand = new FindCommand(scholarshipKeywordPredicate);
        FindCommand expectedFindMajorCommand = new FindCommand(majorKeywordPredicate);

        // no leading and trailing whitespaces, one prefix per predicate
        assertParseSuccess(parser, " n/Alice Bob", expectedFindNameCommand);
        assertParseSuccess(parser, " s/Merit Scholarship", expectedFindScholarshipCommand);
        assertParseSuccess(parser, " m/Computing Business", expectedFindMajorCommand);

        // no leading and trailing whitespaces, one prefix per keyword
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindNameCommand);
        assertParseSuccess(parser, " s/Merit s/Scholarship", expectedFindScholarshipCommand);
        assertParseSuccess(parser, " m/Computing m/Business", expectedFindMajorCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " n/\t Alice \n \t Bob  \t", expectedFindNameCommand);
        assertParseSuccess(parser, " s/Merit\n Scholarship  \t", expectedFindScholarshipCommand);
        assertParseSuccess(parser, " m/\nComputing \t \nBusiness\t", expectedFindMajorCommand);
    }

}
