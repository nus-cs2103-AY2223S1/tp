package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceAddCommand;
import seedu.address.logic.commands.AttendanceDeleteCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
public class AttendanceDeleteCommandParser implements Parser<AttendanceDeleteCommand> {

    @Override
    public AttendanceDeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceDeleteCommand.MESSAGE_USAGE), ive);
        }
        return new AttendanceDeleteCommand(index);
    }
}
