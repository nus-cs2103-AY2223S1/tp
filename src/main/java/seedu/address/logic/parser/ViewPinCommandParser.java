package seedu.address.logic.parser;

import seedu.address.logic.commands.ViewPinCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.pinnedPersonPredicate;

/**
 * parses the command to view pinned clients.
 */
public class ViewPinCommandParser {

    public ViewPinCommand parse(String args) throws ParseException {

        return new ViewPinCommand(new pinnedPersonPredicate());
    }
}
