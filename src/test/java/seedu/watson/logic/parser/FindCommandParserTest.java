package seedu.watson.logic.parser;

import static seedu.watson.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.watson.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.watson.logic.commands.FindCommand;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.student.FindCommandPredicate;

public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() throws ParseException {
        // no leading and trailing whitespaces
        List<String> predicateKeywordList = new ArrayList<>();
        Collections.addAll(predicateKeywordList, "Alice", "", "");
        List<String> unmodifiablePredicateKeywordList = Collections.unmodifiableList(predicateKeywordList);

        FindCommand expectedFindCommand =
            new FindCommand(new FindCommandPredicate(unmodifiablePredicateKeywordList));
        assertParseSuccess(parser, " n/Alice", expectedFindCommand);
    }

}
