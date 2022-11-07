package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterCurrModCommand;
import seedu.address.logic.commands.FilterPlanModCommand;
import seedu.address.logic.commands.FilterPrevModCommand;
import seedu.address.logic.commands.FilterTagCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LessonCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ModuleCommand;
import seedu.address.logic.commands.ModulesLeftCommand;
import seedu.address.logic.commands.NextSemCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.commands.TimetableCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.UserCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

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

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case UserCommand.COMMAND_WORD:
            return new UserCommandParser().parse(arguments);

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

        case ModuleCommand.COMMAND_WORD:
            return new ModuleCommandParser().parse(arguments);

        case ModulesLeftCommand.COMMAND_WORD:
            return new ModulesLeftCommandParser().parse(arguments);

        case LessonCommand.COMMAND_WORD:
            return new LessonCommandParser().parse(arguments);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case TimetableCommand.COMMAND_WORD:
            return new TimetableCommandParser().parse(arguments);

        case FilterTagCommand.COMMAND_WORD:
            return new FilterTagCommandParser().parse(arguments);

        case FilterCurrModCommand.COMMAND_WORD:
            return new FilterCurrModCommandParser().parse(arguments);

        case FilterPrevModCommand.COMMAND_WORD:
            return new FilterPrevModCommandParser().parse(arguments);

        case FilterPlanModCommand.COMMAND_WORD:
            return new FilterPlanModCommandParser().parse(arguments);

        case NextSemCommand.COMMAND_WORD:
            return new NextSemCommand();

        case UndoCommand.COMMAND_WORD:
            return new UndoCommand();

        case RedoCommand.COMMAND_WORD:
            return new RedoCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
