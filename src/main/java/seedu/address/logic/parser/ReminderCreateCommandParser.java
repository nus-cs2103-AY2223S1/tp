package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReminderCreateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.reminder.DateTime;


/**
 * Parses input arguments and creates a new ReminderCreateCommand object
 */
public class ReminderCreateCommandParser implements Parser<ReminderCreateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CreateTagCommand
     * and returns a CreateTagCommand object for execution
     * @throws ParseException if the user input does not conform to the specified format
     */
    public ReminderCreateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_DATETIME);

        if (!argMultimap.arePrefixesPresent(PREFIX_DESCRIPTION, PREFIX_DATETIME)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReminderCreateCommand.MESSAGE_USAGE));
        }
        Optional<Index> index;

        try {
            index = parseIndex(argMultimap);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReminderCreateCommand.MESSAGE_USAGE), pe);
        }

        String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
        return index.map(i -> new ReminderCreateCommand(i, description, dateTime))
                .orElse(new ReminderCreateCommand(description, dateTime));
    }

    private Optional<Index> parseIndex(ArgumentMultimap argMultimap) throws ParseException {
        if (argMultimap.getPreamble().isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(ParserUtil.parseIndex(argMultimap.getPreamble()));
    }
}
