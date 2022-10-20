package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REVERSE;

import java.util.stream.Stream;

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

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argMultimap.getPreamble().toLowerCase().trim();
        if (isPrefixPresent(argMultimap, PREFIX_REVERSE)) {
            switch (trimmedArgs) {
            case "name":
                return new SortCommand(Applicant.sortByName().reversed());
            case "scholarship":
                return new SortCommand(Applicant.sortByScholarship().reversed());
            default:
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }

        switch (trimmedArgs) {
        case "name":
            return new SortCommand(Applicant.sortByName());
        case "scholarship":
            return new SortCommand(Applicant.sortByScholarship());
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
