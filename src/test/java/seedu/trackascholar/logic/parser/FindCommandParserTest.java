package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.FindCommand;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.model.applicant.NameContainsKeywordsPredicate;
import seedu.trackascholar.model.applicant.ScholarshipContainsKeywordsPredicate;
import seedu.trackascholar.model.major.MajorContainsKeywordsPredicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

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
        List<Predicate<Applicant>> partialKeywordsPredicateList = new ArrayList<>();
        List<Predicate<Applicant>> combinedKeywordsPredicateList = new ArrayList<>();

        NameContainsKeywordsPredicate nameKeywordPredicate =
                new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        ScholarshipContainsKeywordsPredicate scholarshipKeywordPredicate =
                new ScholarshipContainsKeywordsPredicate(Arrays.asList("Merit", "Scholarship"));
        MajorContainsKeywordsPredicate majorKeywordPredicate =
                new MajorContainsKeywordsPredicate(Arrays.asList("Computing", "Business"));


        Collections.addAll(partialKeywordsPredicateList, nameKeywordPredicate, majorKeywordPredicate);
        Predicate<Applicant> partialKeywordsPredicate =
                FindCommandParser.combinePredicateList(partialKeywordsPredicateList);

        Collections.addAll(combinedKeywordsPredicateList, nameKeywordPredicate, scholarshipKeywordPredicate,
                majorKeywordPredicate);
        Predicate<Applicant> combinedKeywordsPredicate =
                FindCommandParser.combinePredicateList(combinedKeywordsPredicateList);

        FindCommand expectedFindNameCommand = new FindCommand(nameKeywordPredicate);
        FindCommand expectedFindScholarshipCommand = new FindCommand(scholarshipKeywordPredicate);
        FindCommand expectedFindMajorCommand = new FindCommand(majorKeywordPredicate);
        FindCommand expectedFindPartialCommand = new FindCommand(partialKeywordsPredicate);
        FindCommand expectedFindAllCommand = new FindCommand(combinedKeywordsPredicate);

        // no leading and trailing whitespaces, one prefix per predicate
        assertParseSuccess(parser, " n/Alice Bob", expectedFindNameCommand);
        assertParseSuccess(parser, " s/Merit Scholarship", expectedFindScholarshipCommand);
        assertParseSuccess(parser, " m/Computing Business", expectedFindMajorCommand);
        assertParseSuccess(parser, " n/Alice Bob m/Computing Business", expectedFindPartialCommand);
        assertParseSuccess(parser, " n/Alice Bob s/Merit Scholarship m/Computing Business",
                expectedFindAllCommand);

        // no leading and trailing whitespaces, one prefix per keyword
        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindNameCommand);
        assertParseSuccess(parser, " s/Merit s/Scholarship", expectedFindScholarshipCommand);
        assertParseSuccess(parser, " m/Computing m/Business", expectedFindMajorCommand);
        assertParseSuccess(parser, " n/Alice n/Bob m/Computing m/Business", expectedFindPartialCommand);
        assertParseSuccess(parser, " n/Alice n/Bob s/Merit s/Scholarship m/Computing m/Business",
                expectedFindAllCommand);

        // whitespaces between keywords
        assertParseSuccess(parser, " n/Alice \n \t Bob  \t", expectedFindNameCommand);
        assertParseSuccess(parser, " s/Merit \n \t Engineer  \t", expectedFindScholarshipCommand);
        assertParseSuccess(parser, " r/Technical \n \t Interview            \t", expectedFindMajorCommand);

        // leading whitespace after prefix
        assertParseSuccess(parser, " n/\nAlice \n \t Bob  \t", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/                  Software \n \t Engineer  \t", expectedJobSearchCommand);
        assertParseSuccess(parser, " r/        Technical     \n         Interview  ", expectedRoundSearchCommand);
        assertParseSuccess(parser, " s/    Java s/   Python", expectedSkillSearchCommand);

        //case insensitive
        assertParseSuccess(parser, " n/AlIce BOb", expectedNameSearchCommand);
        assertParseSuccess(parser, " j/soFTwaRe enGiNeer", expectedJobSearchCommand);
        assertParseSuccess(parser, " r/teChnIcaL inTerVieW", expectedRoundSearchCommand);
        assertParseSuccess(parser, " s/JaVa s/PyThON", expectedSkillSearchCommand);
        assertParseSuccess(parser, " n/AlIce BOb s/JaVa s/PyThON", expectedPartialSearchCommand);
        assertParseSuccess(parser, " n/AlIce BOb j/soFTwaRe enGiNeer r/tEchniCal InteRvieW s/JaVa s/PyThON",
                expectedAllPrefixesCommand);

        //AND condition for all prefix
        assertParseSuccess(parser, " n/Alice Bob j/Software Engineer r/Technical Interview s/Java s/Python",
                expectedAllPrefixesCommand);

        //AND condition for partial prefix
        assertParseSuccess(parser, " n/Alice Bob s/Java s/Python", expectedPartialSearchCommand);
    }

}

}
