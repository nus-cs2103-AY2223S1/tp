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
    private static final FindCommandParser parser = new FindCommandParser();
    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "     ", ERROR_MESSAGE);
    }

    @Test
    public void parse_emptyPrefix_throwsParseException() {
        assertParseFailure(parser, "Alice", ERROR_MESSAGE);
    }

    @Test
    public void parse_nonEmptyPreamble_throwsParseException() {
        assertParseFailure(parser, "nonEmptyPreamble n/Alice", ERROR_MESSAGE);
    }

    @Test
    public void parse_keywordMissing_throwsParseException() {
        // missing name keyword
        assertParseFailure(parser, " n/", ERROR_MESSAGE);

        // missing scholarship keyword
        assertParseFailure(parser, " s/", ERROR_MESSAGE);

        // missing majors keyword
        assertParseFailure(parser, " m/", ERROR_MESSAGE);

        //missing multiple keywords
        assertParseFailure(parser, " n/ s/", ERROR_MESSAGE);

        //missing one prefix
        assertParseFailure(parser, "Alice s/", ERROR_MESSAGE);

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
