package seedu.address.logic.parser;


import seedu.address.logic.commands.CreateMessageCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.message.Message;

/**
 * Parses input arguments and creates a new CreateMessageCommand object
 */
public class CreateMessageCommandParser implements Parser<CreateMessageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateMessageCommand
     * and returns a CreateMessageCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public CreateMessageCommand parse(String args) throws ParseException {
        Message message = new Message(args.trim());
        return new CreateMessageCommand(message);
    }
}
