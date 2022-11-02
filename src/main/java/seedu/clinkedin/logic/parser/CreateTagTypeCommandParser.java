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
        int spaceIndex = args.trim().lastIndexOf(" ");
        if (spaceIndex == -1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateTagTypeCommand.MESSAGE_USAGE));
        }
        String prefix = args.trim().substring(spaceIndex + 1).trim();
        String tagTypeName = args.trim().substring(0, spaceIndex).trim();

        return new CreateTagTypeCommand(ParserUtil.parseTagType(tagTypeName, prefix),
                ParserUtil.parsePrefix(prefix));
    }
}

