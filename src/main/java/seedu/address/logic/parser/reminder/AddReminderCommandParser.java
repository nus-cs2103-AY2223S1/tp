package seedu.address.logic.parser.reminder;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.reminder.AddReminderCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDeadline;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddReminderCommandParser implements Parser<AddReminderCommand> {
    
    /**
     * Parses the given {@code String} of arguments in the context of the AddReminderCommand
     * and returns an AddReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddReminderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        ParserUtil.assertPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        ReminderName name = ParserUtil.parseReminderName(argMultimap.getValue(PREFIX_NAME).get());
        ReminderDeadline deadline = ParserUtil.parseReminderDeadline(argMultimap.getValue(PREFIX_TIMESLOT).get());
        ReminderDescription description = ParserUtil.parseReminderDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());

        Reminder reminder = new Reminder(name, deadline, description);

        return new AddReminderCommand(reminder);
    }
}
