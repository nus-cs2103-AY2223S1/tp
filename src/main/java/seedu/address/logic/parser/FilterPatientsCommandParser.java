package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FilterAppointmentsCommand;
import seedu.address.logic.commands.FilterPatientsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterPatientCommand object
 */
public class FilterPatientsCommandParser implements Parser<FilterPatientsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterPatientCommand
     * and returns a FilterPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPatientsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        String trimmedArgs = args.trim();
        String[] keywords = trimmedArgs.split("\\s+");
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            return new FilterPatientsCommand(new TagContainsKeywordsPredicate(argMultimap.getValue(PREFIX_TAG).orElse("")));
        }
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterAppointmentsCommand.MESSAGE_USAGE));
        }
        return new FilterPatientsCommand(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
    }

}
