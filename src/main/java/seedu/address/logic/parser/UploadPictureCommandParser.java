package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UploadPictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Upload a picture of a student to the images folder of the main directory.
 */
public class UploadPictureCommandParser implements Parser<UploadPictureCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the UploadPictureCommand
     * and returns an UploadPictureCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public UploadPictureCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UploadPictureCommand.MESSAGE_USAGE), pe);
        }

        UploadPictureCommand command = new UploadPictureCommand(index);
        return command;
    }
}
