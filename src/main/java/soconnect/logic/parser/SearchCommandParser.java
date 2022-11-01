package soconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.function.Predicate;

import soconnect.logic.commands.SearchCommand;
import soconnect.logic.parser.exceptions.ParseException;
import soconnect.model.person.Person;
import soconnect.model.person.search.ContactContainsAllKeywordsPredicate;
import soconnect.model.person.search.ContactContainsAnyKeywordsPredicate;
import soconnect.model.person.search.ContactMightBeRelevantPredicate;

/**
 * Parses input arguments and creates a new {@code SearchCommand} object.
 */
public class SearchCommandParser implements Parser<SearchCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code SearchCommand}
     * and returns a {@code SearchCommand} object for execution.
     *
     * @throws ParseException If the user input does not conform with the expected format.
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

        if (!isValidSearchKeyword(argMultimap)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        String condition = argMultimap.getPreamble().toLowerCase();
        return parseSearchCondition(argMultimap, condition);
    }

    /**
     * Checks whether the search keywords are of valid types or not.
     * Invalid types are keywords that do not contain any letters or numbers.
     * For example, "++" is invalid, but "a+l+e+x" is valid.
     */
    private boolean isValidSearchKeyword(ArgumentMultimap argMultimap) {
        List<String> keywords = argMultimap.getAllValues();
        return keywords.stream().anyMatch(
                keyword -> !keyword.replaceAll("[^a-zA-Z0-9]", "").trim().isEmpty());
    }

    /**
     * Determines the type of {@code SearchCommand} based on the specified {@code String}
     * condition from the user.
     *
     * @throws ParseException If the user input does not conform with the expected format.
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

    private SearchCommand parseSearchWithCondition(ArgumentMultimap argMultimap, Boolean isJointCondition) {
        Predicate<Person> contactMightBeRelevantPredicate =
                new ContactMightBeRelevantPredicate(argMultimap, false);
        Predicate<Person> contactMightBeLessRelevantPredicate = new ContactMightBeRelevantPredicate(argMultimap, true);
        if (isJointCondition) {
            return new SearchCommand(new ContactContainsAllKeywordsPredicate(argMultimap),
                    contactMightBeRelevantPredicate, contactMightBeLessRelevantPredicate);
        } else {
            return new SearchCommand(new ContactContainsAnyKeywordsPredicate(argMultimap),
                    contactMightBeRelevantPredicate, contactMightBeLessRelevantPredicate);
        }
    }
}
