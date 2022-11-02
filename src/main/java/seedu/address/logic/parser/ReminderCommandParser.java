package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER;
import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Reminder;

/**
 * Parses input arguments and creates a new {@code ReminderCommand} object
 */
public class ReminderCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ReminderCommand}
     * and returns a {@code ReminderCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReminderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMINDER, PREFIX_DATE);

        Index index;
        Reminder reminder;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReminderCommand.MESSAGE_USAGE), ive);
        }

        String task = argMultimap.getValue(PREFIX_REMINDER).orElse("");
        String date = argMultimap.getValue(PREFIX_DATE).orElse("");

        if (task.isBlank()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ReminderCommand.MESSAGE_EMPTY_REMINDER));
        }

        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)
                    .withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException e) {
            if (e.getMessage().contains("Invalid")) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReminderCommand.MESSAGE_INVALID_DATE), e);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ReminderCommand.MESSAGE_DATE_CONSTRAINTS), e);
            }
        }

        reminder = new Reminder(task, date);

        return new ReminderCommand(index, reminder);
    }
}

