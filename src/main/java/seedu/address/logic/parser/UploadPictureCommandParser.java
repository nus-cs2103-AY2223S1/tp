package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UploadPictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Picture;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PICTURE;

public class UploadPictureCommandParser implements Parser<UploadPictureCommand> {


    @Override
    public UploadPictureCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PICTURE);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UploadPictureCommand.MESSAGE_USAGE), pe);
        }

        String filePath = argMultimap.getValue(PREFIX_PICTURE).get();
        Picture picture = ParserUtil.parseFilePath(filePath);
        UploadPictureCommand command = new UploadPictureCommand(picture, index);
        return command;
    }
}
