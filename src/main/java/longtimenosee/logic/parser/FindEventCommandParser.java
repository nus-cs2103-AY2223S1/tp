package longtimenosee.logic.parser;

import static java.util.Objects.requireNonNull;
import static longtimenosee.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DATE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import longtimenosee.logic.commands.FindEventCommand;
import longtimenosee.logic.parser.exceptions.ParseException;
import longtimenosee.model.event.Event;
import longtimenosee.model.event.predicate.DescriptionContainsKeywordsPredicate;
import longtimenosee.model.event.predicate.EventDateMatchesInputPredicate;
import longtimenosee.model.event.predicate.NameInEventContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_NAME, PREFIX_DATE);

        if (!isAtLeastOnePrefixesPresent(argMultimap, PREFIX_DESCRIPTION, PREFIX_NAME, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        List<Predicate<Event>> predicates = new ArrayList<Predicate<Event>>();

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            String trimmedDescription = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get())
                    .retrieveDescription().trim();
            predicates.add(new DescriptionContainsKeywordsPredicate(trimmedDescription));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName.trim();
            String[] nameKeywords = trimmedArgs.split("\\s+");
            predicates.add(new NameInEventContainsKeywordsPredicate(List.of(nameKeywords)));
        }

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            String birthday = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()).value;
            predicates.add(new EventDateMatchesInputPredicate(birthday));
        }

        return new FindEventCommand(predicates);
    }

    /**
     * Checks if there is at least one of the specified prefixes is present in the argument multimap.
     *
     * @param argumentMultimap contains the tokenized arguments
     * @param prefixes to be checked
     * @return boolean to indicate if any prefix is present
     */
    private static boolean isAtLeastOnePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (argumentMultimap.getValue(prefix).isPresent()) {
                return true;
            }
        }
        return false;
    }
}
