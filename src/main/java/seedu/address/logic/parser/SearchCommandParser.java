package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactContainsAllKeywordsPredicate;
import seedu.address.model.person.ContactContainsAnyKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchCommand object.
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns an SearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
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
        boolean isJointCondition;

        switch (condition) {
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
        Pair<List<String>, List<List<String>>> keywordsAndPrefixes = extractPrefixesAndKeywords(argMultimap);
        List<String> prefixes = keywordsAndPrefixes.getKey();
        List<List<String>> keywords = keywordsAndPrefixes.getValue();
        if (isJointCondition) {
            return new SearchCommand(new ContactContainsAllKeywordsPredicate(prefixes, keywords));
        } else {
            return new SearchCommand(new ContactContainsAnyKeywordsPredicate(prefixes, keywords));
        }
    }

    private Pair<List<String>, List<List<String>>> extractPrefixesAndKeywords(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> prefixes = new ArrayList<>();
        List<List<String>> keywords = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixes.add(PREFIX_NAME.getPrefix());
            keywords.add(argMultimap.getAllValues(PREFIX_NAME));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            prefixes.add(PREFIX_PHONE.getPrefix());
            keywords.add(argMultimap.getAllValues(PREFIX_PHONE));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            prefixes.add(PREFIX_EMAIL.getPrefix());
            keywords.add(argMultimap.getAllValues(PREFIX_EMAIL));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            prefixes.add(PREFIX_ADDRESS.getPrefix());
            keywords.add(argMultimap.getAllValues(PREFIX_ADDRESS));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            prefixes.add(PREFIX_TAG.getPrefix());
            keywords.add(argMultimap.getAllValues(PREFIX_TAG));
        }
        if (prefixes.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }
        return new Pair<>(prefixes, keywords);
    }

}
