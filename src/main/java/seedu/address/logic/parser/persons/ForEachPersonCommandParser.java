package seedu.address.logic.parser.persons;

import seedu.address.logic.commands.persons.ForEachPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser for foreach command on person
 */
public class ForEachPersonCommandParser implements Parser<ForEachPersonCommand> {

    @Override
    public ForEachPersonCommand parse(String userInput) throws ParseException {
        return new ForEachPersonCommand(userInput.trim());
    }

}
