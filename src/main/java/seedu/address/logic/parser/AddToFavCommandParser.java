package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToFavCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;

/**
 * Parses input arguments and creates a new AddToFavCommand object
 */
public class AddToFavCommandParser implements Parser<AddToFavCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddToFavCommand
     * and returns an AddToFavCommand object for execution.
     *
     * @param args              string arguments inputted
     * @return                  AddToFavCommand to be executed
     * @throws ParseException   if user input did not satisfy the expected format
     */
    public AddToFavCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        Group groupToAdd = new Group("favorite");

        try {
            index = ParserUtil.parseIndex(args.trim());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToFavCommand.MESSAGE_USAGE),
                    pe);
        }

        return new AddToFavCommand(index, groupToAdd);
    }
}
