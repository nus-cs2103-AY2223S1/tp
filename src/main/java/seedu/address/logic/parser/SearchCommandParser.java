package seedu.address.logic.parser;

import javafx.util.Pair;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactContainsAllKeywordsPredicate;
import seedu.address.model.person.ContactContainsAnyKeywordsPredicate;
import seedu.address.model.person.ContactContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns an SearchCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        String condition = argMultimap.getPreamble().toLowerCase();

        switch (condition) {
        case SearchCommand.AND_CONDITION:
            return parseSearchWithAndCondition(argMultimap);
        case SearchCommand.OR_CONDITION:
            return parseSearchWithOrCondition(argMultimap);
        case SearchCommand.EMPTY_CONDITION:
            return parseSearchWithEmptyCondition(argMultimap);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    private SearchCommand parseSearchWithAndCondition(ArgumentMultimap argMultimap) {
        Pair<List<String>, List<List<String>>> keywordsAndPrefixes = extractPrefixesAndKeywords(argMultimap);
        List<String> prefixes = keywordsAndPrefixes.getKey();
        List<List<String>> keywords = keywordsAndPrefixes.getValue();
        return new SearchCommand(new ContactContainsAllKeywordsPredicate(prefixes, keywords));
    }

    private SearchCommand parseSearchWithOrCondition(ArgumentMultimap argMultimap) {
        Pair<List<String>, List<List<String>>> keywordsAndPrefixes = extractPrefixesAndKeywords(argMultimap);
        List<String> prefixes = keywordsAndPrefixes.getKey();
        List<List<String>> keywords = keywordsAndPrefixes.getValue();
        return new SearchCommand(new ContactContainsAnyKeywordsPredicate(prefixes, keywords));
    }

    private SearchCommand parseSearchWithEmptyCondition(ArgumentMultimap argMultimap) {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_NAME.getPrefix(),
                    argMultimap.getAllValues(PREFIX_NAME)));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_PHONE.getPrefix(),
                    argMultimap.getAllValues(PREFIX_PHONE)));
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_EMAIL.getPrefix(),
                    argMultimap.getAllValues(PREFIX_EMAIL)));
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_ADDRESS.getPrefix(),
                    argMultimap.getAllValues(PREFIX_ADDRESS)));
        } else {
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_TAG.getPrefix(),
                    argMultimap.getAllValues(PREFIX_TAG)));
        }
    }

    private Pair<List<String>, List<List<String>>> extractPrefixesAndKeywords(ArgumentMultimap argMultimap) {
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
        return new Pair<>(prefixes, keywords);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForSearch(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
