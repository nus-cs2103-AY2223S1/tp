package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;

import java.util.Set;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

public class DeleteTagCommandParserTest {

    private DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index index = INDEX_FIRST_TASK;
        String keyword = "priority";
        String keywords = "priority deadline";
        String keywordsWithSpacing = "priority      deadline";
        String duplicateKeywords = "priority priority";

        //Valid input with all fields present and one keyword
        assertParseSuccess(parser, "1 " + PREFIX_TAG + keyword,
                new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("priority")));

        //Valid input with all fields present and two keywords
        assertParseSuccess(parser, "1 " + PREFIX_TAG + keywords,
                new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("priority", "deadline")));

        //Valid input with all fields present and large spacing between keywords
        assertParseSuccess(parser, "1 " + PREFIX_TAG + keywordsWithSpacing,
                new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("priority", "deadline")));

        //Valid input with all fields present and duplicate keywords
        assertParseSuccess(parser, "1 " + PREFIX_TAG + duplicateKeywords,
                new DeleteTagCommand(INDEX_FIRST_TASK, Set.of("priority")));
    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteTagCommand.MESSAGE_USAGE);

        //Missing index
        String missingIndex = PREFIX_TAG + "priority deadline";
        assertParseFailure(parser, missingIndex, expectedMessage);

        //Missing keywords
        String missingKeywords = "1 priority deadline";
        assertParseFailure(parser, missingKeywords, expectedMessage);
    }

    @Test
    public void parse_invalidInputs_Failure() {
        //Empty Keyword
        String emptyKeyword = "1 " + PREFIX_TAG;
        String emptyKeywordMessage = ParserUtil.MESSAGE_INVALID_KEYWORDS;
        assertParseFailure(parser, emptyKeyword, emptyKeywordMessage);

        //Too many keywords(3 given)
        String tooManyKeywords = "1 " + PREFIX_TAG + "priority deadline description";
        String tooManyKeywordsMessage = ParserUtil.MESSAGE_INVALID_NUMBER_OF_KEYWORDS;
        assertParseFailure(parser, tooManyKeywords, tooManyKeywordsMessage);

        //Invalid keywords used
        String invalidKeyword = "1 " + PREFIX_TAG + "pri";
        String invalidKeywordMessage = ParserUtil.MESSAGE_INVALID_KEYWORDS;
        assertParseFailure(parser, invalidKeyword, invalidKeywordMessage);

        //Invalid index(Non-positive)
        String invalidIndexMessage = DeleteTagCommandParser.INVALID_INDEX_FOR_DELETE_TAG;
        assertParseFailure(parser, "-1 " + PREFIX_TAG + "priority", invalidIndexMessage);

        //Invalid index(Above INT_MAX_VALUE) (Waiting for merged code for bug to check again)
        /*
        assertParseFailure(parser, "9999999999999999999999 " + PREFIX_TAG + "priority",
                invalidIndexMessage);
         */

    }
}
