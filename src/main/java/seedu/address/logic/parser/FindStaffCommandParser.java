package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindStaffCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.staff.StaffNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindStaffCommand object.
 */
public class FindStaffCommandParser implements Parser<FindStaffCommand> {

    /**
     * Parses input arguments and creates a FindStaffCommand.
     */
    public FindStaffCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStaffCommand.MESSAGE_USAGE));
        }
        return new FindStaffCommand(new StaffNameContainsKeywordsPredicate(trimmedArgs));
    }

}
