package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_EMPTY_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.parseCommaSeparatedKeywords;
import static seedu.address.logic.parser.ParserUtil.parseNameQueryPredicate;
import static seedu.address.logic.parser.ParserUtil.parseTagsQueryPredicate;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterClearCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    private static final String MATCH_GROUP_SPECIFIER = "specifier";
    private static final String MATCH_GROUP_ARGUMENTS = "arguments";

    private static final String REGEX_FILTER_COMMAND = String.format("(?<%1$s>%2$s|%3$s\\b)?(?<%4$s>.*)?",
            MATCH_GROUP_SPECIFIER, FilterClearCommand.COMMAND_SPECIFIER,
            FilterClearCommand.COMMAND_SPECIFIER_ALIAS, MATCH_GROUP_ARGUMENTS);
    private static final Pattern COMMAND_FORMAT = Pattern.compile(REGEX_FILTER_COMMAND);

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        final Matcher matcher = COMMAND_FORMAT.matcher(trimmedArgs);

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        final String commandSpecifier = matcher.group(MATCH_GROUP_SPECIFIER);
        final String arguments = matcher.group(MATCH_GROUP_ARGUMENTS);

        // no clear specifier
        if (commandSpecifier == null) {
            return new FilterCommand(parseSearchKeywords(arguments, createParseException(FilterCommand.MESSAGE_USAGE)));
        }
        return hasArguments(arguments)
                ? new FilterClearCommand(
                        parseSearchKeywords(arguments, createParseException(FilterClearCommand.MESSAGE_USAGE)))
                : new FilterClearCommand();
    }

    /**
     * Parses keywords given in {@code args} into a {@code FilterCommandPredicate}.
     *
     * @param args Keywords arguments.
     * @param exception ParseException to throw if the parse fails.
     * @return FilterCommandPredicate used for filtering.
     * @throws ParseException When no relevant keywords were found in {@code args}.
     */
    private FilterCommandPredicate parseSearchKeywords(String args, Supplier<ParseException> exception)
            throws ParseException {
        ArgumentMultimap argMultimap = parseNameAndTagsTokens(args);
        Set<NameContainsKeywordsPredicate> nameQueryPredicates = constructNameQueryPredicate(argMultimap);
        Set<TagMatchesQueryPredicate> tagsQueryPredicates = constructTagsQueryPredicate(argMultimap);

        if (!isAnyNonNull(nameQueryPredicates, tagsQueryPredicates)) {
            throw exception.get();
        }
        return new FilterCommandPredicate(nameQueryPredicates, tagsQueryPredicates);
    }

    /**
     * Parses {@code args} into an {@code ArgumentMultimap} for {@code name} and {@code tag}.
     *
     * @param args Keywords arguments.
     * @return ArgumentMultimap of {@code name} and {@code tag}
     * @throws ParseException When no relevant keywords were found in {@code args}.
     */
    private ArgumentMultimap parseNameAndTagsTokens(String args) throws ParseException {
        String paddedArgs = " " + args;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(paddedArgs, PREFIX_NAME, PREFIX_TAG);

        return argMultimap;
    }

    /**
     * Creates a name filtering predicate based on tokens in ArgumentMultimap.
     *
     * @param argMultimap ArgumentMultimap of tokens.
     * @return Set of Name filtering predicate.
     * @throws ParseException If an error occurs during parsing.
     */
    private Set<NameContainsKeywordsPredicate> constructNameQueryPredicate(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> nameKeywords = extractPrefixValues(argMultimap, PREFIX_NAME);
        return nameKeywords == null ? null : parseNameQueryPredicate(nameKeywords);
    }

    /**
     * Creates a tag filtering predicate based on tokens in ArgumentMultimap.
     *
     * @param argMultimap ArgumentMultimap of tokens.
     * @return Set of Tag filtering predicate.
     * @throws ParseException If an error occurs during parsing.
     */
    private Set<TagMatchesQueryPredicate> constructTagsQueryPredicate(ArgumentMultimap argMultimap)
            throws ParseException {
        List<String> tagKeywords = extractPrefixValues(argMultimap, PREFIX_TAG);
        return tagKeywords == null ? null : parseTagsQueryPredicate(tagKeywords);
    }

    private boolean hasArguments(String arguments) {
        return arguments.length() != 0;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes)
                .allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean hasEmptyStrings(List<String> values) {
        return values.stream()
                .anyMatch((value) -> value.equals(""));
    }

    private List<String> extractPrefixValues(ArgumentMultimap argMultimap, Prefix prefix) throws ParseException {
        if (!arePrefixesPresent(argMultimap, prefix) || !argMultimap.getPreamble().isEmpty()) {
            return null;
        }
        List<String> values = argMultimap.getAllValues(prefix);
        if (hasEmptyStrings(values)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, String.format(MESSAGE_EMPTY_ARGUMENTS, prefix)));
        }
        return parseCommaSeparatedKeywords(values);
    }

    private static Supplier<ParseException> createParseException(String errorMessage) {
        return () -> new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, errorMessage));
    }
}


