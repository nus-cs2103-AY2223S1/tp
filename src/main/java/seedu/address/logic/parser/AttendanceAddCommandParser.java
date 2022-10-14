package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SIZE;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendanceAddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AttendanceAddCommandParser implements Parser<AttendanceAddCommand> {

    @Override
    public AttendanceAddCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CLASS_GROUP, PREFIX_SIZE);
        Index index;
        String mod;
        String size;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            mod = argMultimap.getValue(PREFIX_CLASS_GROUP).get();
            // add a parser method for this later ^
            size = ParserUtil.parseSize(argMultimap.getValue(PREFIX_SIZE).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceAddCommand.MESSAGE_USAGE), ive);
        }
              return new AttendanceAddCommand(index, mod, size);
    }
}
