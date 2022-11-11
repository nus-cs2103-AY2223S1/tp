package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jarvis.logic.commands.AddConsultCommand;
import jarvis.logic.commands.AddMasteryCheckCommand;
import jarvis.logic.commands.AddNoteCommand;
import jarvis.logic.commands.AddParticipationCommand;
import jarvis.logic.commands.AddStudentCommand;
import jarvis.logic.commands.AddStudioCommand;
import jarvis.logic.commands.AddTaskCommand;
import jarvis.logic.commands.ClearCommand;
import jarvis.logic.commands.Command;
import jarvis.logic.commands.DeleteLessonCommand;
import jarvis.logic.commands.DeleteNoteCommand;
import jarvis.logic.commands.DeleteStudentCommand;
import jarvis.logic.commands.DeleteTaskCommand;
import jarvis.logic.commands.EditStudentCommand;
import jarvis.logic.commands.ExitCommand;
import jarvis.logic.commands.FindStudentCommand;
import jarvis.logic.commands.GradeCommand;
import jarvis.logic.commands.HelpCommand;
import jarvis.logic.commands.ListAllCommand;
import jarvis.logic.commands.ListLessonCommand;
import jarvis.logic.commands.ListStudentCommand;
import jarvis.logic.commands.ListTaskCommand;
import jarvis.logic.commands.MarkLessonCommand;
import jarvis.logic.commands.MarkStudentCommand;
import jarvis.logic.commands.MarkTaskCommand;
import jarvis.logic.commands.MasteryCheckCommand;
import jarvis.logic.commands.UnmarkLessonCommand;
import jarvis.logic.commands.UnmarkStudentCommand;
import jarvis.logic.commands.UnmarkTaskCommand;
import jarvis.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class JarvisParser {

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

        case AddStudentCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case AddMasteryCheckCommand.COMMAND_WORD:
            return new AddMasteryCheckCommandParser().parse(arguments);

        case AddStudioCommand.COMMAND_WORD:
            return new AddStudioCommandParser().parse(arguments);

        case AddConsultCommand.COMMAND_WORD:
            return new AddConsultCommandParser().parse(arguments);

        case AddNoteCommand.COMMAND_WORD:
            return new AddNoteCommandParser().parse(arguments);

        case AddParticipationCommand.COMMAND_WORD:
            return new AddParticipationCommandParser().parse(arguments);

        case EditStudentCommand.COMMAND_WORD:
            return new EditStudentCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case DeleteLessonCommand.COMMAND_WORD:
            return new DeleteLessonCommandParser().parse(arguments);

        case DeleteNoteCommand.COMMAND_WORD:
            return new DeleteNoteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindStudentCommand.COMMAND_WORD:
            return new FindStudentCommandParser().parse(arguments);

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case ListStudentCommand.COMMAND_WORD:
            return new ListStudentCommand();

        case ListTaskCommand.COMMAND_WORD:
            return new ListTaskCommand();

        case ListLessonCommand.COMMAND_WORD:
            return new ListLessonCommand();

        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();

        case MasteryCheckCommand.COMMAND_WORD:
            return new MasteryCheckCommandParser().parse(arguments);

        case MarkTaskCommand.COMMAND_WORD:
            return new MarkTaskCommandParser().parse(arguments);

        case MarkStudentCommand.COMMAND_WORD:
            return new MarkStudentCommandParser().parse(arguments);

        case MarkLessonCommand.COMMAND_WORD:
            return new MarkLessonCommandParser().parse(arguments);

        case UnmarkTaskCommand.COMMAND_WORD:
            return new UnmarkTaskCommandParser().parse(arguments);

        case UnmarkStudentCommand.COMMAND_WORD:
            return new UnmarkStudentCommandParser().parse(arguments);

        case UnmarkLessonCommand.COMMAND_WORD:
            return new UnmarkLessonCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
