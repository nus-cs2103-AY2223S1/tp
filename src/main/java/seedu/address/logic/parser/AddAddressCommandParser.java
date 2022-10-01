package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAddressCommand;
import seedu.address.logic.commands.AddAddressCommand.AddAddressDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAddressCommand object
 */
public class AddAddressCommandParser implements Parser<AddAddressCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAddressCommand
     * and returns an AddAddressCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAddressCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] indexAndAddress = parseAddressForAddAddress(args);

        Index index;
        AddAddressDescriptor addAddressDescriptor = new AddAddressDescriptor();

        try {
            index = ParserUtil.parseIndex(indexAndAddress[0]);
            addAddressDescriptor.setAddress(ParserUtil.parseAddress(indexAndAddress[1]));
        } catch (ParseException | IndexOutOfBoundsException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAddressCommand.MESSAGE_USAGE),
                    pe);
        }

        if (!addAddressDescriptor.isAddressAdded()) {
            throw new ParseException(AddAddressCommand.MESSAGE_NOT_ADDRESSED);
        }

        return new AddAddressCommand(index, addAddressDescriptor);
    }

    /**
     * Parses the index and address.
     */
    private String[] parseAddressForAddAddress(String args) throws ParseException {
        String trimmedArgs = args.trim();

        return trimmedArgs.split(" ", 2);
    }

}
