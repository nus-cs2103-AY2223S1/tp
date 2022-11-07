package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.grade.GradeEditCommand;
import seedu.address.logic.commands.grade.GradeViewCommand;
import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.logic.commands.student.StudentDeleteCommand;
import seedu.address.logic.commands.student.StudentEditCommand;
import seedu.address.logic.commands.student.StudentEnrollCommand;
import seedu.address.logic.commands.student.StudentExpelCommand;
import seedu.address.logic.commands.student.StudentListCommand;
import seedu.address.logic.commands.student.StudentResetFilterCommand;
import seedu.address.logic.commands.task.TaskAddCommand;
import seedu.address.logic.commands.task.TaskDeleteCommand;
import seedu.address.logic.commands.task.TaskEditCommand;
import seedu.address.logic.commands.task.TaskListCommand;
import seedu.address.logic.commands.tutorialgroup.TutorialGroupAddCommand;
import seedu.address.logic.commands.tutorialgroup.TutorialGroupDeleteCommand;
import seedu.address.logic.commands.tutorialgroup.TutorialGroupFilterCommand;
import seedu.address.logic.commands.tutorialgroup.TutorialGroupListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.grade.GradeEditCommandParser;
import seedu.address.logic.parser.grade.GradeViewCommandParser;
import seedu.address.logic.parser.student.StudentAddCommandParser;
import seedu.address.logic.parser.student.StudentDeleteCommandParser;
import seedu.address.logic.parser.student.StudentEditCommandParser;
import seedu.address.logic.parser.student.StudentEnrollCommandParser;
import seedu.address.logic.parser.student.StudentExpelCommandParser;
import seedu.address.logic.parser.task.TaskAddCommandParser;
import seedu.address.logic.parser.task.TaskDeleteCommandParser;
import seedu.address.logic.parser.task.TaskEditCommandParser;
import seedu.address.logic.parser.tutorialgroup.TutorialGroupAddCommandParser;
import seedu.address.logic.parser.tutorialgroup.TutorialGroupDeleteCommandParser;

/**
 * Parses user input.
 */
public class TaaParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern COMPLEX_COMMAND_FORMAT = Pattern.compile(
            "(?<commandWord>(\\S+ \\S+))(?<arguments>.*)"
    );


    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = COMPLEX_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case StudentAddCommand.COMMAND_WORD:
            return new StudentAddCommandParser().parse(arguments);

        case StudentDeleteCommand.COMMAND_WORD:
            return new StudentDeleteCommandParser().parse(arguments);

        case StudentEnrollCommand.COMMAND_WORD:
            return new StudentEnrollCommandParser().parse(arguments);

        case StudentExpelCommand.COMMAND_WORD:
            return new StudentExpelCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case StudentListCommand.COMMAND_WORD:
            return new StudentListCommand();

        case StudentEditCommand.COMMAND_WORD:
            return new StudentEditCommandParser().parse(arguments);

        case StudentResetFilterCommand.COMMAND_WORD:
            return new StudentResetFilterCommand();

        case TutorialGroupAddCommand.COMMAND_WORD:
            return new TutorialGroupAddCommandParser().parse(arguments);

        case TutorialGroupDeleteCommand.COMMAND_WORD:
            return new TutorialGroupDeleteCommandParser().parse(arguments);

        case TutorialGroupListCommand.COMMAND_WORD:
            return new TutorialGroupListCommand();

        case TutorialGroupFilterCommand.COMMAND_WORD:
            return new TutorialGroupFilterCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TaskAddCommand.COMMAND_WORD:
            return new TaskAddCommandParser().parse(arguments);

        case TaskEditCommand.COMMAND_WORD:
            return new TaskEditCommandParser().parse(arguments);

        case TaskDeleteCommand.COMMAND_WORD:
            return new TaskDeleteCommandParser().parse(arguments);

        case TaskListCommand.COMMAND_WORD:
            return new TaskListCommand();

        case GradeEditCommand.COMMAND_WORD:
            return new GradeEditCommandParser().parse(arguments);

        case GradeViewCommand.COMMAND_WORD:
            return new GradeViewCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
