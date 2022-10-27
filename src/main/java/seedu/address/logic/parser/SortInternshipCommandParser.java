package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;

import seedu.address.logic.commands.SortInternshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses the prefix to determine the sort condition.
 */
public class SortInternshipCommandParser implements Parser<SortInternshipCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortInternshipCommand
     * and returns a SortInternshipCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortInternshipCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInternshipCommand.MESSAGE_USAGE));
        }
        if (trimmedArgs.equalsIgnoreCase(PREFIX_COMPANY_NAME.getPrefix())) {
            return new SortInternshipCommand(SortInternshipCommand.Criteria.COMPANY_NAME);
        }
        if (trimmedArgs.equalsIgnoreCase(PREFIX_INTERVIEW_DATE.getPrefix())) {
            return new SortInternshipCommand(SortInternshipCommand.Criteria.INTERVIEW_DATE);
        }
        if (trimmedArgs.equalsIgnoreCase(PREFIX_INTERNSHIP_STATUS.getPrefix())) {
            return new SortInternshipCommand(SortInternshipCommand.Criteria.INTERNSHIP_STATUS);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInternshipCommand.MESSAGE_USAGE));

    }
}
