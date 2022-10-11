package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_EMAIL;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_NAME;
import static seedu.address.logic.parser.CliSyntax.INDICATOR_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.ContactContainsAllKeywordsPredicate;
import seedu.address.model.person.ContactContainsAnyKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

public class SearchCommandParserTest {
    private final String failureMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE);
    private final SearchCommandParser parser = new SearchCommandParser();

    @Test
    void parse_noValidArguments_failure() {
        assertParseFailure(parser, "", failureMessage);
        assertParseFailure(parser, "     ", failureMessage);
        assertParseFailure(parser, "abcd", failureMessage);
        assertParseFailure(parser, "an/namep/phone", failureMessage);
    }

    @Test
    void parse_oneArgument_success() {
        // no leading and trailing whitespaces
        assertParseSuccess(parser, "n/Alice",
                new SearchCommand(preparePredicate(INDICATOR_NAME, "Alice")));
        assertParseSuccess(parser, "p/1234567",
                new SearchCommand(preparePredicate(INDICATOR_PHONE, "1234567")));
        assertParseSuccess(parser, "e/bob@gmail.com",
                new SearchCommand(preparePredicate(INDICATOR_EMAIL, "bob@gmail.com")));
        assertParseSuccess(parser, "a/NUS",
                new SearchCommand(preparePredicate(INDICATOR_ADDRESS, "NUS")));

    }

    /**
     * Parses {@code userInput} into a {@code ContactContainsAllKeywordsPredicate}.
     */
    private ContactContainsAllKeywordsPredicate preparePredicate(String prefix, String searchInput) {
        return new ContactContainsAllKeywordsPredicate(List.of(prefix),
                List.of(List.of(searchInput)));
    }

}
