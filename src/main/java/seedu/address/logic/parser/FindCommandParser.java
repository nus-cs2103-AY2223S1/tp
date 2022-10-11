package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonMatchesPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    private PersonMatchesPredicate predicate = new PersonMatchesPredicate();
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MODULE_CODE);

        if (!areAllArgsValid(argMultimap, PREFIX_NAME, PREFIX_MODULE_CODE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywordsString = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            List<String> nameKeywordsList = Arrays.asList(nameKeywordsString);
            predicate.setNamesList(nameKeywordsList);
            return new FindCommand(predicate);
        }

        if (argMultimap.getValue(PREFIX_MODULE_CODE).isPresent()) {
            String[] modKeywordsString = argMultimap.getValue(PREFIX_MODULE_CODE).get().split("\\s+");
            List<String> modKeywordsList = Arrays.asList(modKeywordsString);
            predicate.setModuleList(modKeywordsList);
            return new FindCommand(predicate);
        }

        return new FindCommand(new PersonMatchesPredicate());

    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areAllArgsValid(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        Supplier<Stream<Prefix>> presentArgs = () ->
                Stream.of(prefixes).filter(prefix -> argumentMultimap.getValue(prefix).isPresent());
        if (presentArgs.get().count() == 0) {
            return false;
        } else {
            return presentArgs.get().allMatch(prefix ->
                    argumentMultimap.getValue(prefix).get().trim().length() != 0);
        }
    }
}
