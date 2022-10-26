package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.logic.commands.AddTagCommand;
import seedu.intrack.logic.commands.DeleteTagCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.tag.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses input arguments and creates a new {@code AddTagCommand} object
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code DeleteTagCommand}
     * and returns a {@code DeleteTagCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimArgs = args.trim();
        if (trimArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        // split the index and commands
        // this is the index
        String[] splitCommand = trimArgs.split("\\s+", 2);
        if (splitCommand.length != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTagCommand.DELETE_TAG_CONSTRAINTS));
        }
        // these are the tags to be deleted
        String[] tags = splitCommand[1].split("\\s+");
        List<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < tags.length; i++) {
            Tag tagToAdd = new Tag(tags[i]);
            tagList.add(tagToAdd);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(splitCommand[0]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), ive);
        }

        String clearCommand = tags[0].toUpperCase();
        return new DeleteTagCommand(index, tagList, clearCommand);
    }
}
