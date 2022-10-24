package seedu.address.logic.parser.reminder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT;

import seedu.address.logic.commands.reminder.AddReminderCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.datetime.Datetime;
import seedu.address.model.datetime.DatetimeCommonUtils;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.reminder.ReminderPriority;

/**
 * Parses input arguments and creates a new AddReminderCommand object
 */
public class AddReminderCommandParser implements Parser<AddReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddReminderCommand
     * and returns an AddReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddReminderCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIMESLOT, PREFIX_DESCRIPTION, PREFIX_PRIORITY);

        ParserUtil.assertAllPrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TIMESLOT, PREFIX_DESCRIPTION,
                PREFIX_PRIORITY);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE));
        }

        ReminderName name = ReminderParserUtil.parseReminderName(argMultimap.getValue(PREFIX_NAME).get());
        ReminderDescription description = ReminderParserUtil.parseReminderDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Datetime deadline = DatetimeCommonUtils.parseDatetime(argMultimap.getValue(PREFIX_TIMESLOT).get());
        ReminderPriority priority = ReminderParserUtil.parseReminderPriority(argMultimap.getValue(PREFIX_PRIORITY)
                .get());

        Reminder reminder = new Reminder(name, deadline, priority, description);

        return new AddReminderCommand(reminder);
    }
}
