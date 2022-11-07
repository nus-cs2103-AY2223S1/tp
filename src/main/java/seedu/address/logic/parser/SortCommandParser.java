package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.sort.SortByAppointment;
import seedu.address.logic.parser.sort.SortByClientTag;
import seedu.address.logic.parser.sort.SortByIncome;
import seedu.address.logic.parser.sort.SortByMonthly;
import seedu.address.logic.parser.sort.SortByName;
import seedu.address.logic.parser.sort.SortByRiskTag;


/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        // Make sorting case-insensitive
        switch (trimmedArgs.toLowerCase()) {

        case "name":
            return new SortCommand(new SortByName("asc"), "name");

        case "name desc":
            return new SortCommand(new SortByName("desc"), "name in descending order");

        case "appt":
            return new SortCommand(new SortByAppointment("asc"), "appt");

        case "appt desc":
            return new SortCommand(new SortByAppointment("desc"), "appt in descending order");

        case "income":
            return new SortCommand(new SortByIncome("asc"), "income");

        case "income desc":
            return new SortCommand(new SortByIncome("desc"), "income in descending order");

        case "risk":
            return new SortCommand(new SortByRiskTag("asc"), "risk");

        case "risk desc":
            return new SortCommand(new SortByRiskTag("desc"), "risk in descending order");

        case "client":
            return new SortCommand(new SortByClientTag("asc"), "client");

        case "client desc":
            return new SortCommand(new SortByClientTag("desc"), "client in descending order");

        case "monthly":
            return new SortCommand(new SortByMonthly("asc"), "monthly");

        case "monthly desc":
            return new SortCommand(new SortByMonthly("desc"), "monthly in descending order");

        default:
            throw new ParseException(String.format(SortCommand.MESSAGE_INCORRECT_KEYWORD, SortCommand.MESSAGE_USAGE));
        }
    }

}
