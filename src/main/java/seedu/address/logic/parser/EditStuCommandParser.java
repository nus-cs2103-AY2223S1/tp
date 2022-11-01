package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditStuCommand object
 */
public class EditStuCommandParser implements Parser<EditStuCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditStuCommand
     * and returns an EditStuCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditStuCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TELEGRAM, PREFIX_EMAIL, PREFIX_ATTENDANCE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStuCommand.MESSAGE_USAGE), pe);
        }

        EditStuCommand.EditStudentDescriptor editStudentDescriptor = new EditStuCommand.EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseStuName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            editStudentDescriptor.setTelegram(ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseStuEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ATTENDANCE).isPresent()) {
            editStudentDescriptor.setAttendance(ParserUtil
                    .parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).get()));
        }

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStuCommand.MESSAGE_NOT_EDITED);
        }

        return new EditStuCommand(index, editStudentDescriptor);
    }

}
