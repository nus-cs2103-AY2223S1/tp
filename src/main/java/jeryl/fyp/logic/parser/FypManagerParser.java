package jeryl.fyp.logic.parser;

import static jeryl.fyp.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jeryl.fyp.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jeryl.fyp.logic.commands.AddDeadlineCommand;
import jeryl.fyp.logic.commands.AddStudentCommand;
import jeryl.fyp.logic.commands.ClearCommand;
import jeryl.fyp.logic.commands.Command;
import jeryl.fyp.logic.commands.DeleteDeadlineCommand;
import jeryl.fyp.logic.commands.DeleteStudentCommand;
import jeryl.fyp.logic.commands.EditCommand;
import jeryl.fyp.logic.commands.ExitCommand;
import jeryl.fyp.logic.commands.FindProjectNameCommand;
import jeryl.fyp.logic.commands.FindStudentIdCommand;
import jeryl.fyp.logic.commands.FindStudentNameCommand;
import jeryl.fyp.logic.commands.FindTagsCommand;
import jeryl.fyp.logic.commands.HelpCommand;
import jeryl.fyp.logic.commands.ListCommand;
import jeryl.fyp.logic.commands.ListDeadlineCommand;
import jeryl.fyp.logic.commands.MarkCommand;
import jeryl.fyp.logic.commands.SortProjectNameCommand;
import jeryl.fyp.logic.commands.SortProjectStatusCommand;
import jeryl.fyp.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class FypManagerParser {

    /**
     * Used for initial separation of command word, flag, and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern
            .compile("(?<commandWord>[\\S&&[^-]]+)"
                    + "(?<flag>\\s*-\\w+)?"
                    + "(?<arguments>.*)");

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
        final String flag = matcher.group("flag");
        final String arguments = matcher.group("arguments");
        final String newCommandWord = commandWord + (flag == null ? "" : " " + flag.trim());

        switch (newCommandWord) {

        case AddStudentCommand.COMMAND_WORD:
        case AddStudentCommand.ALTERNATIVE_COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
        case DeleteStudentCommand.ALTERNATIVE_COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteDeadlineCommand.COMMAND_WORD:
            return new DeleteDeadlineCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindProjectNameCommand.COMMAND_WORD:
        case FindProjectNameCommand.ALTERNATIVE_COMMAND_WORD:
            return new FindProjectNameCommandParser().parse(arguments);

        case FindStudentNameCommand.COMMAND_WORD:
            return new FindStudentNameCommandParser().parse(arguments);

        case FindStudentIdCommand.COMMAND_WORD:
            return new FindStudentIdCommandParser().parse(arguments);

        case FindTagsCommand.COMMAND_WORD:
            return new FindTagsCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(arguments);

        case ListDeadlineCommand.COMMAND_WORD:
            return new ListDeadlineCommandParser().parse(arguments);

        case SortProjectNameCommand.COMMAND_WORD:
        case SortProjectNameCommand.ALTERNATIVE_COMMAND_WORD:
            return new SortProjectNameCommand();

        case SortProjectStatusCommand.COMMAND_WORD:
            return new SortProjectStatusCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
