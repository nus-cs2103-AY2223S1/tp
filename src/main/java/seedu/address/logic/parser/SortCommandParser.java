package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonComparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.model.person.PersonComparators.ADDRESS_COMPARATOR;
import static seedu.address.model.person.PersonComparators.NAME_COMPARATOR;
import static seedu.address.model.person.PersonComparators.ROLE_COMPARATOR;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    private static Map<Prefix, Comparator<Person>> PREFIX_COMPARATOR_MAP = Map.of(
        PREFIX_NAME, NAME_COMPARATOR,
        PREFIX_ADDRESS, ADDRESS_COMPARATOR,
        PREFIX_ROLE, ROLE_COMPARATOR
    );

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String userInput) throws ParseException {

        // Add `/` behind userInput so that ArgumentTokenizer can tokenize
        // `sort name` like `sort name/`.
        userInput = userInput + "/";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_ROLE);

        if (!anyPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_ROLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Prefix prefix = getFirstPresentPrefix(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_ROLE);
        return new SortCommand(PREFIX_COMPARATOR_MAP.get(prefix));
    }

    private static Prefix getFirstPresentPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent()).findFirst().get();
    }

    /**
     * Returns true if one of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean anyPrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
