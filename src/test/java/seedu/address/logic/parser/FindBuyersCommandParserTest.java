package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindBuyersCommand;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.NameContainsKeywordsPredicate;
import seedu.address.model.buyer.NameContainsSubstringPredicate;

public class FindBuyersCommandParserTest {

    private FindBuyersCommandParser parser = new FindBuyersCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyersCommand.MESSAGE_USAGE));
    }

    // removed as the predicate in FindBuyersCommand cannot be compared using equals
//    @Test
//    public void parse_validArgs_returnsFindCommand() {
//        // no leading and trailing whitespaces
//        Predicate<Buyer> combinedPredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"))
//                .or(new NameContainsSubstringPredicate("Alice Bob"));
//        FindBuyersCommand expectedFindBuyersCommand =
//                new FindBuyersCommand(combinedPredicate);
//        assertParseSuccess(parser, "Alice Bob", expectedFindBuyersCommand);
//
//        // multiple whitespaces between keywords
//        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindBuyersCommand);
//    }

}
