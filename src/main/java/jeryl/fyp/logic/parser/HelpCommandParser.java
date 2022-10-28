package jeryl.fyp.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jeryl.fyp.logic.commands.AddDeadlineCommand;
import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.commands.ClearCommand;
import jeryl.fyp.logic.commands.DeleteDeadlineCommand;
import jeryl.fyp.logic.commands.DeleteStudentCommand;
import jeryl.fyp.logic.commands.EditCommand;
import jeryl.fyp.logic.commands.ExitCommand;
import jeryl.fyp.logic.commands.FindCommand;
import jeryl.fyp.logic.commands.FindProjectNameCommand;
import jeryl.fyp.logic.commands.FindStudentIdCommand;
import jeryl.fyp.logic.commands.FindStudentNameCommand;
import jeryl.fyp.logic.commands.FindTagsCommand;
import jeryl.fyp.logic.commands.HelpCommand;
import jeryl.fyp.logic.commands.ListCommand;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.commands.SortProjectNameCommand;
import jeryl.fyp.logic.commands.SortProjectStatusCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new HelpCommand object
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    private static final Pattern HELP_ARGS_COMMAND_FORMAT = Pattern
            .compile("(?<commandWord>[\\S&&[^-]]+)"
                    + "(?<flag>\\s*-\\w)?"
                    + ".*");

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns a HelpCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) {
        final Matcher matcher = HELP_ARGS_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new HelpCommand();
        }

        final String commandWord = matcher.group("commandWord");
        final String flag = matcher.group("flag");
        final String parsedArgs = commandWord + (flag == null ? "" : " " + flag.trim());

        switch (parsedArgs) {

        case AddStudentCommand.COMMAND_WORD:
        case AddStudentCommand.ALTERNATIVE_COMMAND_WORD:
            return new HelpCommand(AddStudentCommand.MESSAGE_USAGE);

        case AddDeadlineCommand.COMMAND_WORD:
            return new HelpCommand(AddDeadlineCommand.MESSAGE_USAGE);

        case EditCommand.COMMAND_WORD:
            return new HelpCommand(EditCommand.MESSAGE_USAGE);

        case DeleteStudentCommand.COMMAND_WORD:
        case DeleteStudentCommand.ALTERNATIVE_COMMAND_WORD:
            return new HelpCommand(DeleteStudentCommand.MESSAGE_USAGE);

        case DeleteDeadlineCommand.COMMAND_WORD:
            return new HelpCommand(DeleteDeadlineCommand.MESSAGE_USAGE);

        case ClearCommand.COMMAND_WORD:
            return new HelpCommand(ClearCommand.MESSAGE_USAGE);

        case FindProjectNameCommand.COMMAND_WORD:
            return new HelpCommand(FindProjectNameCommand.MESSAGE_USAGE);

        case FindProjectNameCommand.ALTERNATIVE_COMMAND_WORD: // 'find' gives the full FindCommand usage message
            return new HelpCommand(FindCommand.MESSAGE_USAGE);

        case FindStudentNameCommand.COMMAND_WORD:
            return new HelpCommand(FindStudentNameCommand.MESSAGE_USAGE);

        case FindStudentIdCommand.COMMAND_WORD:
            return new HelpCommand(FindStudentIdCommand.MESSAGE_USAGE);

        case FindTagsCommand.COMMAND_WORD:
            return new HelpCommand(FindTagsCommand.MESSAGE_USAGE);

        case ListCommand.COMMAND_WORD:
            return new HelpCommand(ListCommand.MESSAGE_USAGE);

        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand(HelpCommand.MESSAGE_USAGE);

        case MarkCommand.COMMAND_WORD:
            return new HelpCommand(MarkCommand.MESSAGE_USAGE);

        case SortProjectNameCommand.COMMAND_WORD:
        case SortProjectNameCommand.ALTERNATIVE_COMMAND_WORD:
            return new HelpCommand(SortProjectNameCommand.MESSAGE_USAGE);

        case SortProjectStatusCommand.COMMAND_WORD:
            return new HelpCommand(SortProjectStatusCommand.MESSAGE_USAGE);

        default:
            return new HelpCommand();
        }

    }
}
