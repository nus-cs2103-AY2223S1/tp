package seedu.address.logic.parser.addcommandparser;

import seedu.address.logic.commands.addcommands.AddPersonCommand;
import seedu.address.logic.commands.addcommands.AddSupplierCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonCategory;

/**
 * Parses input arguments and creates a new AddSupplierCommand object
 */
public class AddSupplierCommandParser implements Parser<AddSupplierCommand> {

    public AddSupplierCommandParser() {
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddSupplierCommand
     * and returns an AddSupplierCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSupplierCommand parse(String args) throws ParseException {
        AddPersonCommand addPersonCommand = ParserUtil.parseAddPersonCommand(args, PersonCategory.SUPPLIER);
        assert(addPersonCommand instanceof AddSupplierCommand);
        return (AddSupplierCommand) addPersonCommand;
    }
}
