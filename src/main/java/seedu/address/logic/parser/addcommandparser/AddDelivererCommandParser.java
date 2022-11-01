package seedu.address.logic.parser.addcommandparser;

import seedu.address.logic.commands.addcommands.AddDelivererCommand;
import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonCategory;

/**
 * Parses input arguments and creates a new AddDeliverer object
 */
public class AddDelivererCommandParser implements Parser<AddDelivererCommand> {

    public AddDelivererCommandParser() {
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddDelivererCommand
     * and returns an AddDelivererCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDelivererCommand parse(String args) throws ParseException {
        AddPersonCommand addPersonCommand = ParserUtil.parseAddPersonCommand(args, PersonCategory.DELIVERER);
        assert(addPersonCommand instanceof AddDelivererCommand);
        return (AddDelivererCommand) addPersonCommand;
    }
}
