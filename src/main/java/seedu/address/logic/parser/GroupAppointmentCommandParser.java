package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_KEY;

import seedu.address.logic.commands.GroupAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Key;

/**
 * Parses input arguments and creates a new GroupAppointmentCommand object
 */
public class GroupAppointmentCommandParser implements Parser<GroupAppointmentCommand> {
    @Override
    public GroupAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_KEY);
        Key key;
        if (argMultimap.getValue(PREFIX_KEY).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GroupAppointmentCommand.MESSAGE_USAGE));
        }
        String keyName = argMultimap.getValue(PREFIX_KEY).get();
        if (!Key.isValidKey(keyName)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    GroupAppointmentCommand.MESSAGE_USAGE));
        }
        key = ParserUtil.parseKey(keyName);
        return new GroupAppointmentCommand(key);
    }
}
