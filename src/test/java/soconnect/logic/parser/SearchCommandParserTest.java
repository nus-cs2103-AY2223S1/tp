package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import soconnect.logic.commands.SearchCommand;
import soconnect.model.person.search.ContactContainsAllKeywordsPredicate;
import soconnect.model.person.search.ContactContainsAnyKeywordsPredicate;
import soconnect.model.person.search.ContactMightBeRelevantPredicate;

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
    public void parse_jointSearchValidArgs_success() {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize("and n/name a/address p/phone",
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        ContactContainsAllKeywordsPredicate firstPredicate =
                new ContactContainsAllKeywordsPredicate(argMultimap);
        ContactMightBeRelevantPredicate alternativePredicate =
                new ContactMightBeRelevantPredicate(argMultimap, false);
        ContactMightBeRelevantPredicate leastAccuratePredicate =
                new ContactMightBeRelevantPredicate(argMultimap, true);

        assertParseSuccess(parser, "and n/name a/address p/phone",
                new SearchCommand(firstPredicate, alternativePredicate, leastAccuratePredicate));
    }

    @Test
    public void parse_disjointSearchValidArgs_success() {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize("or n/name a/address p/phone",
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        ContactContainsAnyKeywordsPredicate secondPredicate =
                new ContactContainsAnyKeywordsPredicate(argMultimap);
        ContactMightBeRelevantPredicate alternativePredicate =
                new ContactMightBeRelevantPredicate(argMultimap, false);
        ContactMightBeRelevantPredicate leastAccuratePredicate =
                new ContactMightBeRelevantPredicate(argMultimap, true);

        assertParseSuccess(parser, "or n/name a/address p/phone",
                new SearchCommand(secondPredicate, alternativePredicate, leastAccuratePredicate));
    }

    @Test
    public void parse_emptyPreambleSearchValidArgs_success() {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(" n/name a/address p/phone",
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        ContactContainsAllKeywordsPredicate firstPredicate =
                new ContactContainsAllKeywordsPredicate(argMultimap);
        ContactMightBeRelevantPredicate alternativePredicate =
                new ContactMightBeRelevantPredicate(argMultimap, false);
        ContactMightBeRelevantPredicate leastAccuratePredicate =
                new ContactMightBeRelevantPredicate(argMultimap, true);

        assertParseSuccess(parser, " n/name a/address p/phone",
                new SearchCommand(firstPredicate, alternativePredicate, leastAccuratePredicate));
    }

    @Test
    public void parse_invalidPreambleSearchValidArgs_throwsParseException() {
        assertParseFailure(parser, "either n/name a/address p/phone",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "all n/name a/address p/phone",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "!1s n/name a/address p/phone",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSearchArgs_throwsParseException() {
        assertParseFailure(parser, "name address phone",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "h/name address p/phone",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "name/n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
    }
}
