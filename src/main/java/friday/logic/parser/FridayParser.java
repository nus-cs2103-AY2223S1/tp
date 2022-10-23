package friday.logic.parser;

import static friday.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static friday.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import friday.logic.commands.AddCommand;
import friday.logic.commands.AliasCommand;
import friday.logic.commands.AliasListCommand;
import friday.logic.commands.ClearCommand;
import friday.logic.commands.Command;
import friday.logic.commands.DeleteCommand;
import friday.logic.commands.EditCommand;
import friday.logic.commands.ExitCommand;
import friday.logic.commands.FindCommand;
import friday.logic.commands.HelpCommand;
import friday.logic.commands.ListCommand;
import friday.logic.commands.MarkMasteryCheckCommand;
import friday.logic.commands.RemarkCommand;
import friday.logic.commands.SortCommand;
import friday.logic.commands.UgCommand;
import friday.logic.commands.UnaliasCommand;
import friday.logic.parser.exceptions.ParseException;
import friday.model.Model;

/**
 * Parses user input.
 */
public class FridayParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @param model current model
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Model model) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String alias = matcher.group("commandWord");
        final String commandWord = model.getKeyword(alias);
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

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case UgCommand.COMMAND_WORD:
            return new UgCommand();

        case MarkMasteryCheckCommand.COMMAND_WORD:
            return new MarkMasteryCheckCommandParser().parse(arguments);

        case AliasCommand.COMMAND_WORD:
            return new AliasCommandParser().parse(arguments);

        case UnaliasCommand.COMMAND_WORD:
            return new UnaliasCommandParser().parse(arguments);

        case AliasListCommand.COMMAND_WORD:
            return new AliasListCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
