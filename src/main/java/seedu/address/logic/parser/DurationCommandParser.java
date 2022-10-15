package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendanceCommand;
import seedu.address.logic.commands.DurationCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Duration;

/**
 * Parses input command and creates a new DurationCommand object.
 */
public class DurationCommandParser implements Parser<DurationCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DurationCommand
     * and returns a DurationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DurationCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DURATION);
        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendanceCommand.MESSAGE_USAGE), pe);
        }
        String duration = argumentMultimap.getValue(PREFIX_DURATION).orElse("");
        return new DurationCommand(index, new Duration(duration));
    }
}
