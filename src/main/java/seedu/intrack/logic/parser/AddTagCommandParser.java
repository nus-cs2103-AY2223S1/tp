package seedu.intrack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.commons.exceptions.IllegalValueException;
import seedu.intrack.logic.commands.AddTagCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code AddTagCommand} object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddTagCommand}
     * and returns a {@code AddTagCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimArgs = args.trim();
        if (trimArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        String[] splitCommand = trimArgs.split("\\s+", 2);
        if (splitCommand.length != 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        String[] tags = splitCommand[1].split("\\s+");
        List<Tag> tagList = new ArrayList<>();
        for (String tag : tags) {
            tagList.add(ParserUtil.parseTag(tag));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(splitCommand[0]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), ive);
        }

        return new AddTagCommand(index, tagList);
    }
}
