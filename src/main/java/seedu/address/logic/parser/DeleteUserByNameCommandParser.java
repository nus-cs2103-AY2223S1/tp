package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.DeleteUserByNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FullNamePredicate;

/**
 * Parses input arguments and creates a new DeleteUserByNameCommand object
 */
public class DeleteUserByNameCommandParser implements Parser<DeleteUserByNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteUserByNameCommand
     * and returns a DeleteUserByNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteUserByNameCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args);
        if (argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteUserByNameCommand.MESSAGE_USAGE));
        }

        String name = argumentMultimap.getPreamble();
        return new DeleteUserByNameCommand(new FullNamePredicate(name));



    }
}
