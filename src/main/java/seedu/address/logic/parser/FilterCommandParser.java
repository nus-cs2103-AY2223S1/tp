package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterClearCommand;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {


    private static final String MATCH_GROUP_SPECIFIER = "specifier";
    private static final String MATCH_GROUP_ARGUMENTS = "arguments";

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

        if (commandSpecifier == null) {
            return new FilterCommand(parseKeywords(arguments));
        }
        return hasArguments(arguments) ? new FilterClearCommand(parseKeywords(arguments)) : new FilterClearCommand();
    }

    private boolean hasArguments(String arguments) {
        return arguments.length() != 0;
    }

    private NameContainsKeywordsPredicate parseKeywords(String args) throws ParseException {
        String trimmedArgs = " " + args.trim();
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        String nameKeyword = argMultimap.getValue(PREFIX_NAME).get();
        return new NameContainsKeywordsPredicate(nameKeyword);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}


