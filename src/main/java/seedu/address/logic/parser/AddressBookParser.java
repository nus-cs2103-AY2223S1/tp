package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.consultation.AddConsultationCommand;
import seedu.address.logic.commands.reminder.AddReminderCommand;
import seedu.address.logic.commands.reminder.DeleteReminderCommand;
import seedu.address.logic.commands.tutorial.AddTutorialCommand;
import seedu.address.logic.commands.tutorial.DeleteTutorialCommand;
import seedu.address.logic.parser.consultation.AddConsultationCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.exceptions.UnknownPreambleException;
import seedu.address.logic.parser.reminder.AddReminderCommandParser;
import seedu.address.logic.parser.reminder.DeleteReminderCommandParser;
import seedu.address.logic.parser.tutorial.AddTutorialCommandParser;
import seedu.address.logic.parser.tutorial.DeleteTutorialCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    // Quick workaround to extract preamble instead of just first word by changing regex
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWords>[a-z]+( [a-z]+(?!\\/))*)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWords = matcher.group("commandWords");
        final String arguments = matcher.group("arguments");
        switch (commandWords) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddReminderCommand.COMMAND_WORD:
            return new AddReminderCommandParser().parse(arguments);

        case DeleteReminderCommand.COMMAND_WORD:
            return new DeleteReminderCommandParser().parse(arguments);

        case AddTutorialCommand.COMMAND_WORD:
            return new AddTutorialCommandParser().parse(arguments);

        case DeleteTutorialCommand.COMMAND_WORD:
            return new DeleteTutorialCommandParser().parse(arguments);

        case AddConsultationCommand.COMMAND_WORD:
            return new AddConsultationCommandParser().parse(arguments);

        default:
            throw new UnknownPreambleException(commandWords);
        }
    }

}
