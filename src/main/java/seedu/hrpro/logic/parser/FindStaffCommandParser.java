package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.hrpro.logic.commands.FindStaffCommand;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffNameContainsKeywordsPredicate;

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

        String[] nameKeywords = trimmedArgs.split("\\s+");

        for (String name : nameKeywords) {
            if (!StaffName.isValidStaffName(name)) {
                throw new ParseException(StaffName.MESSAGE_CONSTRAINTS);
            }
        }

        return new FindStaffCommand(new StaffNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
