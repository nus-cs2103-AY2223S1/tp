package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.SearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ContactContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
        case SearchCommand.OR_CONDITION:
        case SearchCommand.EMPTY_CONDITION:
            return parseSearchWithEmptyCondition(argMultimap);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
    }

    private SearchCommand parseSearchWithEmptyCondition(ArgumentMultimap argMultimap) {
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = new String[]{argMultimap.getValue(PREFIX_NAME).get()};
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_NAME.getPrefix(),
                    Arrays.asList(nameKeywords)));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] phoneKeywords = new String[]{argMultimap.getValue(PREFIX_PHONE).get()};
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_PHONE.getPrefix(),
                    Arrays.asList(phoneKeywords)));
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] emailKeywords = new String[]{argMultimap.getValue(PREFIX_EMAIL).get()};
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_EMAIL.getPrefix(),
                    Arrays.asList(emailKeywords)));
        } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            String[] addressKeywords = new String[]{argMultimap.getValue(PREFIX_ADDRESS).get()};
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_ADDRESS.getPrefix(),
                    Arrays.asList(addressKeywords)));
        } else {
            String[] tagKeywords = new String[]{argMultimap.getValue(PREFIX_TAG).get()};
            return new SearchCommand(new ContactContainsKeywordsPredicate(PREFIX_TAG.getPrefix(),
                    Arrays.asList(tagKeywords)));
        }
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
