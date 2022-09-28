package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterClearCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommandPredicate;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagMatchesQueryPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {


    private static final String MATCH_GROUP_SPECIFIER = "specifier";
    private static final String MATCH_GROUP_ARGUMENTS = "arguments";
    private static final String COMMA = ",";

    private static final String REGEX_FIND_COMMAND = String.format("(((?<%1$s>%2$s)\\s+)?)(?<%3$s>.*)?",
            MATCH_GROUP_SPECIFIER, FilterClearCommand.COMMAND_SPECIFIER, MATCH_GROUP_ARGUMENTS);
    private static final Pattern COMMAND_FORMAT = Pattern.compile(REGEX_FIND_COMMAND);

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim() + " ";
        final Matcher matcher = COMMAND_FORMAT.matcher(trimmedArgs);

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        final String commandSpecifier = matcher.group(MATCH_GROUP_SPECIFIER);
        final String arguments = matcher.group(MATCH_GROUP_ARGUMENTS);

        // no clear specifier
        if (commandSpecifier == null) {
            return new FilterCommand(parseSearchKeywords(arguments));
        }
        return hasArguments(arguments) ? new FilterClearCommand(parseSearchKeywords(arguments))
                : new FilterClearCommand();
    }

    private boolean hasArguments(String arguments) {
        return arguments.length() != 0;
    }

    private FilterCommandPredicate parseSearchKeywords(String args) throws ParseException {
        ArgumentMultimap argMultimap = parseNameAndTagsTokens(args);
        NameContainsKeywordsPredicate nameQueryPredicate = constructNameQueryPredicate(argMultimap);
        TagMatchesQueryPredicate tagsQueryPredicate = constructTagsQueryPredicate(argMultimap);
        if (nameQueryPredicate == null && tagsQueryPredicate == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
        return new FilterCommandPredicate(nameQueryPredicate, tagsQueryPredicate);
    }

    private NameContainsKeywordsPredicate constructNameQueryPredicate(ArgumentMultimap argMultimap) {
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            return null;
        }
        List<String> nameKeywords = splitCommaSeparatedKeywords(argMultimap.getAllValues(PREFIX_NAME));
        return new NameContainsKeywordsPredicate(nameKeywords);
    }

    private TagMatchesQueryPredicate constructTagsQueryPredicate(ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG) || !argMultimap.getPreamble().isEmpty()) {
            return null;
        }
        List<String> tagKeywords = splitCommaSeparatedKeywords(argMultimap.getAllValues(PREFIX_TAG));
        Set<Tag> tagSetQuery = ParserUtil.parseTags(tagKeywords);
        return new TagMatchesQueryPredicate(tagSetQuery);
    }

    private List<String> splitCommaSeparatedKeywords(List<String> keywords) {
        return keywords.stream()
                .flatMap((keyword) -> Stream.of(keyword.split(COMMA)))
                .collect(Collectors.toList());
    }

    private ArgumentMultimap parseNameAndTagsTokens(String args) throws ParseException {
        String trimmedArgs = " " + args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_NAME, PREFIX_TAG);

        return argMultimap;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


