package seedu.clinkedin.logic.parser;

import seedu.clinkedin.logic.commands.DeleteTagTypeCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.person.exceptions.TagTypeNotFoundException;
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
        Prefix pref;
        try {
            pref = UniqueTagTypeMap.getPrefixFromTagType(trimmedTagType);
        } catch (TagTypeNotFoundException e) {
            throw new ParseException("Invalid TagType!");
        }
        TagType tagType = ParserUtil.parseTagType(trimmedTagType, pref);
        return new DeleteTagTypeCommand(tagType);
    }
}

