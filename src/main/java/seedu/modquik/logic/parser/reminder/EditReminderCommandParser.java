package seedu.modquik.logic.parser.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.modquik.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.reminder.EditReminderCommand;
import seedu.modquik.logic.commands.reminder.EditReminderCommand.EditReminderDescriptor;
import seedu.modquik.logic.parser.ArgumentMultimap;
import seedu.modquik.logic.parser.ArgumentTokenizer;
import seedu.modquik.logic.parser.Parser;
import seedu.modquik.logic.parser.ParserUtil;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.model.datetime.DatetimeCommonUtils;

/**
 * Parses input arguments and creates a new EditReminderCommand object
 */
public class EditReminderCommandParser implements Parser<EditReminderCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditReminderCommand
     * and returns an EditReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditReminderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_TIME, PREFIX_DATE_DAY, PREFIX_PRIORITY,
                        PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditReminderCommand.MESSAGE_USAGE),
                    pe);
        }

        EditReminderDescriptor editReminderDescriptor = new EditReminderDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editReminderDescriptor.setName(ReminderParserUtil.parseReminderName(argMultimap
                    .getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent() && argMultimap.getValue(PREFIX_DATE_DAY).isPresent()) {
            String date = argMultimap.getValue(PREFIX_DATE_DAY).get();
            String deadline = argMultimap.getValue(PREFIX_TIME).get();
            editReminderDescriptor.setDeadline(DatetimeCommonUtils.parseDatetime(date, deadline));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editReminderDescriptor.setPriority(ReminderParserUtil.parseReminderPriority(argMultimap
                    .getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editReminderDescriptor.setDescription(ReminderParserUtil.parseReminderDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }

        //Only date
        if (argMultimap.getValue(PREFIX_DATE_DAY).isPresent() && !argMultimap.getValue(PREFIX_TIME).isPresent()) {
            throw new ParseException(EditReminderCommand.MESSAGE_DATETIME_REMINDER);
        }
        //Only timeslot
        if (!argMultimap.getValue(PREFIX_DATE_DAY).isPresent() && argMultimap.getValue(PREFIX_TIME).isPresent()) {
            throw new ParseException(EditReminderCommand.MESSAGE_DATETIME_REMINDER);
        }

        if (!editReminderDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditReminderCommand.MESSAGE_NOT_EDITED);
        }

        return new EditReminderCommand(index, editReminderDescriptor);
    }

}
