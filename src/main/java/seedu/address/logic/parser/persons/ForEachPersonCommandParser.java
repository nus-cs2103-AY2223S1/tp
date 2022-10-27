package seedu.address.logic.parser.persons;

import seedu.address.logic.commands.persons.ForEachPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

public class ForEachPersonCommandParser implements Parser<ForEachPersonCommand> {

    @Override
    public ForEachPersonCommand parse(String userInput) throws ParseException {
        return new ForEachPersonCommand(userInput.trim());
    }

}
