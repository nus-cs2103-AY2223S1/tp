package seedu.address.logic.parser.logiccommands;

import seedu.address.logic.commands.logicalcommand.ContainsAttributeCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to parse user input to check contains attribute command
 */
public class ContainsAttributeCommandParser implements Parser<ContainsAttributeCommand> {

    @Override
    public ContainsAttributeCommand parse(String userInput) throws ParseException {
        return new ContainsAttributeCommand(userInput);
    }

}
