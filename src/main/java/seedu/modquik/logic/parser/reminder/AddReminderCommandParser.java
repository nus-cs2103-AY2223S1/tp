package seedu.modquik.logic.parser.reminder;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.modquik.logic.commands.reminder.AddReminderCommand;
import seedu.modquik.logic.parser.ArgumentMultimap;
import seedu.modquik.logic.parser.ArgumentTokenizer;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.datetime.Datetime;
import seedu.modquik.model.datetime.DatetimeCommonUtils;
import seedu.modquik.model.reminder.Reminder;
import seedu.modquik.model.reminder.ReminderDescription;
import seedu.modquik.model.reminder.ReminderName;
import seedu.modquik.model.reminder.ReminderPriority;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE_DAY, PREFIX_TIME, PREFIX_DESCRIPTION,
                        PREFIX_PRIORITY);

        ParserUtil.assertAllPrefixesPresent(argMultimap, AddReminderCommand.MESSAGE_USAGE,
                PREFIX_NAME, PREFIX_DATE_DAY, PREFIX_TIME, PREFIX_DESCRIPTION, PREFIX_PRIORITY);
        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE));
        }

        ReminderName name = ReminderParserUtil.parseReminderName(argMultimap.getValue(PREFIX_NAME).get());
        ReminderDescription description = ReminderParserUtil.parseReminderDescription(
                argMultimap.getValue(PREFIX_DESCRIPTION).get());
        String date = argMultimap.getValue(PREFIX_DATE_DAY).get();
        String deadlineString = argMultimap.getValue(PREFIX_TIME).get();
        Datetime deadline = DatetimeCommonUtils.parseDatetime(date, deadlineString);
        ReminderPriority priority = ReminderParserUtil.parseReminderPriority(argMultimap.getValue(PREFIX_PRIORITY)
                .get());

        Reminder reminder = new Reminder(name, deadline, priority, description);

        return new AddReminderCommand(reminder);
    }
}
