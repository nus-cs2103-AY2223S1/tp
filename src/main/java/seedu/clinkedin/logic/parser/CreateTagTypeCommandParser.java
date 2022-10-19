package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.clinkedin.logic.commands.CreateTagTypeCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CreateTagTypeCommand object
 */
public class CreateTagTypeCommandParser implements Parser<CreateTagTypeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CreateTagTypeCommand
     * and returns a CreateTagTypeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CreateTagTypeCommand parse(String args) throws ParseException {
        String[] tagTypeAndPrefix = args.trim().split("\\s+", 2);
        if (tagTypeAndPrefix.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagTypeCommand.MESSAGE_USAGE));
        }
        return new CreateTagTypeCommand(ParserUtil.parseTagType(tagTypeAndPrefix[0], tagTypeAndPrefix[1]),
                ParserUtil.parsePrefix(tagTypeAndPrefix[1]));
    }
}

