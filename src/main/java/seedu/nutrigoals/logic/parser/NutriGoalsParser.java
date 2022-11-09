package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.nutrigoals.logic.commands.AddCommand;
import seedu.nutrigoals.logic.commands.ClearCommand;
import seedu.nutrigoals.logic.commands.Command;
import seedu.nutrigoals.logic.commands.DeleteCommand;
import seedu.nutrigoals.logic.commands.EditCommand;
import seedu.nutrigoals.logic.commands.ExitCommand;
import seedu.nutrigoals.logic.commands.FindCommand;
import seedu.nutrigoals.logic.commands.HelpCommand;
import seedu.nutrigoals.logic.commands.ListCommand;
import seedu.nutrigoals.logic.commands.LocateGymCommand;
import seedu.nutrigoals.logic.commands.ProfileCommand;
import seedu.nutrigoals.logic.commands.ReviewCommand;
import seedu.nutrigoals.logic.commands.SetupCommand;
import seedu.nutrigoals.logic.commands.SuggestCommand;
import seedu.nutrigoals.logic.commands.TargetCommand;
import seedu.nutrigoals.logic.commands.TipCommand;
import seedu.nutrigoals.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class NutriGoalsParser {

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
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TargetCommand.COMMAND_WORD:
            return new TargetCommandParser().parse(arguments);

        case SetupCommand.COMMAND_WORD:
            return new SetupCommandParser().parse(arguments);

        case ReviewCommand.COMMAND_WORD:
            return new ReviewCommand();

        case SuggestCommand.COMMAND_WORD:
            return new SuggestCommand();

        case LocateGymCommand.COMMAND_WORD:
            return new LocateGymCommandParser().parse(arguments);

        case ProfileCommand.COMMAND_WORD:
            return new ProfileCommand();

        case TipCommand.COMMAND_WORD:
            return new TipCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
