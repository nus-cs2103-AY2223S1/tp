package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISKTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NormalTagContainsKeywordsPredicate;
import seedu.address.model.person.RiskTagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, PREFIX_NAME.getPrefix() + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREFIX_NAME.getPrefix() + "Alice  \t \n Bob", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindRiskTagCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new RiskTagContainsKeywordsPredicate(Arrays.asList("high",
                        "medium")));
        assertParseSuccess(parser , PREFIX_RISKTAG.getPrefix()
                + "high medium", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREFIX_RISKTAG.getPrefix()
                + "high \n \t medium" , expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindNormalTagCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NormalTagContainsKeywordsPredicate(Arrays.asList("friends",
                        "owesMoney")));
        assertParseSuccess(parser , PREFIX_TAG.getPrefix()
                + "friends owesMoney", expectedFindCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, PREFIX_TAG.getPrefix()
                + "friends \n \t owesMoney" , expectedFindCommand);
    }

}
