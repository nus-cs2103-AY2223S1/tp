package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.clinkedin.logic.commands.EditTagTypeCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.tag.TagType;

/**
 * Parses input arguments and creates a new EditTagTypeCommand object
 */
public class EditTagTypeCommandParser implements Parser<EditTagTypeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditTagTypeCommand
     * and returns a EditTagTypeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTagTypeCommand parse(String args) throws ParseException {
        int seperatorIndex = args.trim().lastIndexOf(" ");
        if (seperatorIndex == -1) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagTypeCommand.MESSAGE_USAGE));
        }
        String prefixPair = args.trim().substring(seperatorIndex + 1).trim();
        String tagTypePair = args.trim().substring(0, seperatorIndex).trim();
        String[] oldNewTagTypeStrings = ParserUtil.parseHyphen(tagTypePair);
        String[] oldNewPrefixStrings = ParserUtil.parseHyphen(prefixPair);
        Prefix[] oldNewPrefixes = new Prefix[2];
        oldNewPrefixes[0] = ParserUtil.parsePrefix(oldNewPrefixStrings[0]);
        oldNewPrefixes[1] = ParserUtil.parsePrefix(oldNewPrefixStrings[1]);
        TagType[] oldNewTagTypes = new TagType[2];
        oldNewTagTypes[0] = ParserUtil.parseTagType(oldNewTagTypeStrings[0], oldNewPrefixes[0]);
        oldNewTagTypes[1] = ParserUtil.parseTagType(oldNewTagTypeStrings[1], oldNewPrefixes[1]);
        return new EditTagTypeCommand(oldNewPrefixes[0], oldNewTagTypes[0], oldNewPrefixes[1], oldNewTagTypes[1]);
    }
}
