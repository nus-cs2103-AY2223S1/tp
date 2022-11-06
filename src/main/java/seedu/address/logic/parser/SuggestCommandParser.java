package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_TIME_OF_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.Collection;
import java.util.Set;

import seedu.address.logic.commands.SuggestCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DayTimeInWeek;
import seedu.address.model.person.Keyword;
import seedu.address.model.person.PersonSuggestionPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SuggestCommandParser implements Parser<SuggestCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuggestCommand parse(String args) throws ParseException {

        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DAY_TIME_OF_WEEK, PREFIX_KEYWORD);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
        }

        Collection<String> cs = argMultimap.getAllValues(PREFIX_DAY_TIME_OF_WEEK);
        Set<DayTimeInWeek> dayTimesInWeek = ParserUtil.parseDayTimesInWeek(
                argMultimap.getAllValues(PREFIX_DAY_TIME_OF_WEEK));
        Set<Keyword> keywords = ParserUtil.parseKeywords(argMultimap.getAllValues(PREFIX_KEYWORD));

        if ((dayTimesInWeek.isEmpty() && keywords.isEmpty())
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SuggestCommand.MESSAGE_USAGE));
        }

        return new SuggestCommand(new PersonSuggestionPredicate(dayTimesInWeek, keywords));

    }

}
