package seedu.address.logic.parser.addcommandparser;

import seedu.address.logic.commands.addcommands.AddBuyerCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonCategory;

/**
 * Parses input arguments and creates a new AddBuyerCommand object
 */
public class AddBuyerCommandParser implements Parser<AddBuyerCommand> {

    public AddBuyerCommandParser() {
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddBuyerCommand
     * and returns an AddBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddBuyerCommand parse(String args) throws ParseException {
        AddPersonCommand addPersonCommand = ParserUtil.parseAddPersonCommand(args, PersonCategory.BUYER);
        assert(addPersonCommand instanceof AddBuyerCommand);
        return (AddBuyerCommand) addPersonCommand;
    }
}
