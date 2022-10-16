package soconnect.logic.parser;

import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.commands.CustomiseCommand.Attribute.ADDRESS;
import static soconnect.logic.commands.CustomiseCommand.Attribute.EMAIL;
import static soconnect.logic.commands.CustomiseCommand.Attribute.PHONE;
import static soconnect.logic.commands.CustomiseCommand.Attribute.TAGS;
import static soconnect.logic.commands.CustomiseCommand.CustomiseSubCommand.HIDE;
import static soconnect.logic.commands.CustomiseCommand.CustomiseSubCommand.ORDER;
import static soconnect.logic.commands.CustomiseCommand.CustomiseSubCommand.SHOW;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static soconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.Test;

import soconnect.commons.core.GuiSettings;
import soconnect.logic.commands.CustomiseCommand;
import soconnect.logic.commands.SearchCommand;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;
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
    public void parse_searchValidArgs_success() {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize("and n/name a/address p/phone",
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);
        ContactContainsAllKeywordsPredicate firstPredicate =
                new ContactContainsAllKeywordsPredicate(argMultimap);
        ContactContainsAnyKeywordsPredicate secondPredicate =
                new ContactContainsAnyKeywordsPredicate(argMultimap);
        ContactMightBeRelevantPredicate alternativePredicate =
                new ContactMightBeRelevantPredicate(argMultimap.getAllValues());

        assertParseSuccess(parser, "and n/name a/address p/phone",
                new SearchCommand(firstPredicate, alternativePredicate));
    }
}
