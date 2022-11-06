package seedu.modquik.logic.parser;

import static seedu.modquik.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.modquik.logic.commands.ClearCommand;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.ExitCommand;
import seedu.modquik.logic.commands.HelpCommand;
import seedu.modquik.logic.commands.ListCommand;
import seedu.modquik.logic.commands.SwitchCommand;
import seedu.modquik.logic.commands.consultation.AddConsultationCommand;
import seedu.modquik.logic.commands.consultation.DeleteConsultationCommand;
import seedu.modquik.logic.commands.consultation.EditConsultationCommand;
import seedu.modquik.logic.commands.reminder.AddReminderCommand;
import seedu.modquik.logic.commands.reminder.DeleteReminderCommand;
import seedu.modquik.logic.commands.reminder.EditReminderCommand;
import seedu.modquik.logic.commands.reminder.MarkReminderCommand;
import seedu.modquik.logic.commands.reminder.SortReminderCommand;
import seedu.modquik.logic.commands.reminder.UnmarkReminderCommand;
import seedu.modquik.logic.commands.student.AddStudentCommand;
import seedu.modquik.logic.commands.student.DeleteStudentCommand;
import seedu.modquik.logic.commands.student.EditStudentCommand;
import seedu.modquik.logic.commands.student.ExtractEmailsCommand;
import seedu.modquik.logic.commands.student.FindCommand;
import seedu.modquik.logic.commands.tutorial.AddTutorialCommand;
import seedu.modquik.logic.commands.tutorial.DeleteTutorialCommand;
import seedu.modquik.logic.commands.tutorial.EditTutorialCommand;
import seedu.modquik.logic.parser.consultation.AddConsultationCommandParser;
import seedu.modquik.logic.parser.consultation.DeleteConsultationCommandParser;
import seedu.modquik.logic.parser.consultation.EditConsultationCommandParser;
import seedu.modquik.logic.parser.exceptions.ParseException;
import seedu.modquik.logic.parser.exceptions.UnknownPreambleException;
import seedu.modquik.logic.parser.reminder.AddReminderCommandParser;
import seedu.modquik.logic.parser.reminder.DeleteReminderCommandParser;
import seedu.modquik.logic.parser.reminder.EditReminderCommandParser;
import seedu.modquik.logic.parser.reminder.MarkReminderCommandParser;
import seedu.modquik.logic.parser.reminder.SortReminderCommandParser;
import seedu.modquik.logic.parser.reminder.UnmarkReminderCommandParser;
import seedu.modquik.logic.parser.student.AddStudentCommandParser;
import seedu.modquik.logic.parser.student.DeleteStudentCommandParser;
import seedu.modquik.logic.parser.student.EditStudentCommandParser;
import seedu.modquik.logic.parser.student.FindCommandParser;
import seedu.modquik.logic.parser.tutorial.AddTutorialCommandParser;
import seedu.modquik.logic.parser.tutorial.DeleteTutorialCommandParser;
import seedu.modquik.logic.parser.tutorial.EditTutorialCommandParser;


/**
 * Parses user input.
 */
public class ModQuikParser {

    /**
     * Used for initial separation of command word and args.
     */
    // Quick workaround to extract preamble instead of just first word by changing regex
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile(
            "^(?<commandWords>[\\sa-zA-Z]+(?=\\s|$))(?<arguments>.*)?");

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

        final String commandWords = matcher.group("commandWords").trim().replaceAll(" +", " ");
        // Workaround: ArgumentTokenizer must have an additional space in front as preamble
        final String arguments = " " + matcher.group("arguments").trim();

        if (commandWords.startsWith(ListCommand.COMMAND_WORD + " ")) {
            return new ListCommand();
        } else if (commandWords.startsWith(ExitCommand.COMMAND_WORD + " ")) {
            return new ExitCommand();
        } else if (commandWords.startsWith(HelpCommand.COMMAND_WORD + " ")) {
            return new HelpCommand();
        } else if (commandWords.startsWith(ExtractEmailsCommand.COMMAND_WORD + " ")) {
            return new ExtractEmailsCommand();
        }

        switch (commandWords) {
        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case AddReminderCommand.COMMAND_WORD:
            return new AddReminderCommandParser().parse(arguments);

        case DeleteReminderCommand.COMMAND_WORD:
            return new DeleteReminderCommandParser().parse(arguments);

        case EditReminderCommand.COMMAND_WORD:
            return new EditReminderCommandParser().parse(arguments);

        case MarkReminderCommand.COMMAND_WORD:
            return new MarkReminderCommandParser().parse(arguments);

        case UnmarkReminderCommand.COMMAND_WORD:
            return new UnmarkReminderCommandParser().parse(arguments);

        case SortReminderCommand.COMMAND_WORD:
            return new SortReminderCommandParser().parse(arguments);

        case AddTutorialCommand.COMMAND_WORD:
            return new AddTutorialCommandParser().parse(arguments);

        case DeleteTutorialCommand.COMMAND_WORD:
            return new DeleteTutorialCommandParser().parse(arguments);

        case EditTutorialCommand.COMMAND_WORD:
            return new EditTutorialCommandParser().parse(arguments);

        case AddConsultationCommand.COMMAND_WORD:
            return new AddConsultationCommandParser().parse(arguments);

        case DeleteConsultationCommand.COMMAND_WORD:
            return new DeleteConsultationCommandParser().parse(arguments);

        case EditConsultationCommand.COMMAND_WORD:
            return new EditConsultationCommandParser().parse(arguments);

        case SwitchCommand.COMMAND_WORD:
            return new SwitchCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case ExtractEmailsCommand.COMMAND_WORD:
            return new ExtractEmailsCommand();

        default:
            throw new UnknownPreambleException(commandWords);
        }
    }

}
