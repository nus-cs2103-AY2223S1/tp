package soconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import soconnect.logic.commands.SearchCommand;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.person.ContactContainsAllKeywordsPredicate;
import soconnect.model.person.ContactContainsAnyKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code SearchCommand} object.
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code SearchCommand}
     * and returns a {@code SearchCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    public SearchCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        String condition = argMultimap.getPreamble().toLowerCase();
        return parseSearchCondition(argMultimap, condition);
    }

    /**
     * Determines the type of {@code SearchCommand} based on the specified {@code String}
     * condition from the user.
     *
     * @throws ParseException If the user input does not conform the expected format.
     */
    private SearchCommand parseSearchCondition(ArgumentMultimap argMultimap, String condition) throws ParseException {
        boolean isJointCondition;

        switch (condition) {
        // Search commands with empty and "and" preamble are both treated as
        // search for result that satisfy all prefix conditions
        case SearchCommand.AND_CONDITION:
        case SearchCommand.EMPTY_CONDITION:
            isJointCondition = true;
            return parseSearchWithCondition(argMultimap, isJointCondition);
        case SearchCommand.OR_CONDITION:
            isJointCondition = false;
            return parseSearchWithCondition(argMultimap, isJointCondition);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
    }

    private SearchCommand parseSearchWithCondition(ArgumentMultimap argMultimap, Boolean isJointCondition)
            throws ParseException {
        if (isJointCondition) {
            return new SearchCommand(new ContactContainsAllKeywordsPredicate(argMultimap));
        } else {
            return new SearchCommand(new ContactContainsAnyKeywordsPredicate(argMultimap));
        }
    }

    private Pair<List<String>, List<List<String>>> extractPrefixesAndKeywords(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> prefixes = new ArrayList<>();
        List<List<String>> keywords = new ArrayList<>();
        populatePrefixAndKeywordsList(PREFIX_NAME, argMultimap, prefixes, keywords);
        populatePrefixAndKeywordsList(PREFIX_PHONE, argMultimap, prefixes, keywords);
        populatePrefixAndKeywordsList(PREFIX_EMAIL, argMultimap, prefixes, keywords);
        populatePrefixAndKeywordsList(PREFIX_ADDRESS, argMultimap, prefixes, keywords);
        populatePrefixAndKeywordsList(PREFIX_TAG, argMultimap, prefixes, keywords);
        if (prefixes.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        return new Pair<>(prefixes, keywords);
    }

    private void populatePrefixAndKeywordsList(Prefix prefix, ArgumentMultimap argMultimap,
                                               List<String> prefixes, List<List<String>> keywords) {
        if (argMultimap.getValue(prefix).isPresent()) {
            prefixes.add(prefix.getPrefix());
            keywords.add(argMultimap.getAllValues(prefix));
        }
    }

}
