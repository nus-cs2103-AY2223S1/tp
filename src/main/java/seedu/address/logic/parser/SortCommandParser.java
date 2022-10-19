package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Scholarship;

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
        String trimmedArgs = args.toLowerCase().trim();
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
}
