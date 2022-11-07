package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AVAILABILITY;

import java.util.NoSuchElementException;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AvailabilityCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.position.TeachingAssistant;

/**
 * Parses input arguments and creates a new AvailabilityCommand object
 */
public class AvailabilityCommandParser implements Parser<AvailabilityCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AvailabilityCommand}
     * and returns a {@code AvailabilityCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AvailabilityCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AVAILABILITY);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AvailabilityCommand.MESSAGE_USAGE), ive);
        } catch (NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AvailabilityCommand.MESSAGE_USAGE), e);
        }

        String availability = argMultimap.getValue(PREFIX_AVAILABILITY).orElse("");
        if (!TeachingAssistant.isValidAvailability(availability)) {
            throw new ParseException(TeachingAssistant.MESSAGE_CONSTRAINTS);
        }

        return new AvailabilityCommand(index, availability);
    }
}
