package seedu.address.logic.parser;

import static seedu.address.logic.parser.ParserUtil.parseTags;

import java.util.Arrays;
import java.util.Set;

import seedu.address.logic.commands.CreateTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new CreateTagCommand object
 */
public class CreateTagCommandParser implements Parser<CreateTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTagCommand
     * and returns a CreateTagCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public CreateTagCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] tagNames = trimmedArgs.split("\\s+");

        Set<Tag> newTags = parseTags(Arrays.asList(tagNames));
        return new CreateTagCommand(newTags);
    }
}
