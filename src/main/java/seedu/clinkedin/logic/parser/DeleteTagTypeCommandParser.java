package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.clinkedin.logic.commands.DeleteTagTypeCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.exceptions.TagTypeNotFoundException;
import seedu.clinkedin.model.tag.TagType;

/**
 * Parses input arguments and creates a new DeleteTagTypeCommand object
 */
public class DeleteTagTypeCommandParser implements Parser<DeleteTagTypeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTagTypeCommand
     * and returns a DeleteTagTypeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagTypeCommand parse(String args) throws ParseException {
        String trimmedTagType = args.trim();
        if (trimmedTagType.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagTypeCommand.MESSAGE_USAGE));
        }
        Prefix pref;
        try {
            pref = UniqueTagTypeMap.getPrefixFromTagType(trimmedTagType);
        } catch (TagTypeNotFoundException tne) {
            throw new ParseException(tne.getMessage());
        }
        TagType tagType = ParserUtil.parseTagType(trimmedTagType, pref);
        return new DeleteTagTypeCommand(tagType);
    }
}

