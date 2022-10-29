package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.model.person.PersonComparators.ADDRESS_COMPARATOR;
import static seedu.address.model.person.PersonComparators.NAME_COMPARATOR;
import static seedu.address.model.person.PersonComparators.ROLE_COMPARATOR;
import static seedu.address.model.person.PersonComparators.reverseComparator;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static final Map<Prefix, Comparator<Person>> PREFIX_COMPARATOR_MAP = Map.of(
        PREFIX_NAME, NAME_COMPARATOR,
        PREFIX_ADDRESS, ADDRESS_COMPARATOR,
        PREFIX_ROLE, ROLE_COMPARATOR
    );

    private static final Prefix[] AVAILABLE_FIELDS = PREFIX_COMPARATOR_MAP.keySet().toArray(new Prefix[]{});

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String userInput) throws ParseException {

        // Add `/` behind userInput so that ArgumentTokenizer can tokenize
        // `sort name` like `sort name/` or `sort name/desc`.
        userInput = userInput.contains("desc") ? userInput : userInput + "/";

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, AVAILABLE_FIELDS);

        if (!anyPrefixesPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Prefix prefix = getFirstPresentPrefix(argMultimap);
        Comparator<Person> comparator = PREFIX_COMPARATOR_MAP.get(prefix);

        // The value can only be "" or "desc"
        if (!argMultimap.getValue(prefix).get().equals("") && !argMultimap.getValue(prefix).get().equals("desc")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(prefix).get().equals("desc")) {
            comparator = reverseComparator(comparator);
        }

        return new SortCommand(comparator);
    }

    private static Prefix getFirstPresentPrefix(ArgumentMultimap argumentMultimap) {
        return Stream.of(AVAILABLE_FIELDS)
                .filter(prefix -> argumentMultimap.getValue(prefix).isPresent())
                .findFirst().get();
    }

    /**
     * Returns true if one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap) {
        return Stream.of(AVAILABLE_FIELDS).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
