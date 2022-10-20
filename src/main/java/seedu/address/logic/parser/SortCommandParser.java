package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVERSE;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;

/**
 * Parses input arguments and creates a new SortCommand object
 */

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_REVERSE);

        String trimmedArgs = argMultimap.getPreamble().toLowerCase().trim();

        switch (trimmedArgs) {
        case "name":
            return new SortCommand(Applicant.sortByName(), isReversePrefixPresent(argMultimap));
        case "scholarship":
            return new SortCommand(Applicant.sortByScholarship(), isReversePrefixPresent(argMultimap));
        case "status":
            return new SortCommand(Applicant.sortByStatus(), isReversePrefixPresent(argMultimap));
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    private static boolean isReversePrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_REVERSE).isPresent();
    }
}
