package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddStuCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DarkModeCommand;
import seedu.address.logic.commands.DeleteStudentCommand;
import seedu.address.logic.commands.DeleteTaCommand;
import seedu.address.logic.commands.EditStuCommand;
import seedu.address.logic.commands.EditTeachingAssistantCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.LightModeCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.module.AddModuleCommand;
import seedu.address.logic.commands.module.DeleteModuleCommand;
import seedu.address.logic.commands.module.EditModuleCommand;
import seedu.address.logic.commands.module.ListModuleCommand;
import seedu.address.logic.commands.module.ViewModuleCommand;
import seedu.address.logic.commands.module.ViewTargetModuleCommand;
import seedu.address.logic.commands.schedule.AddScheduleCommand;
import seedu.address.logic.commands.schedule.ClearScheduleCommand;
import seedu.address.logic.commands.schedule.DeleteScheduleCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.ViewScheduleCommand;
import seedu.address.logic.commands.schedule.ViewTimeTableCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.schedule.AddScheduleCommandParser;
import seedu.address.logic.parser.schedule.ClearScheduleCommandParser;
import seedu.address.logic.parser.schedule.DeleteScheduleCommandParser;
import seedu.address.logic.parser.schedule.EditScheduleCommandParser;
import seedu.address.logic.parser.schedule.ViewScheduleCommandParser;
import seedu.address.logic.parser.schedule.ViewTimeTableCommandParser;

/**
 * Parses user input.
 */
public class ProfNusParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ProfNusParser.class);

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

        case AddStuCommand.COMMAND_WORD:
            return new AddStuCommandParser().parse(arguments);

        case AddModuleCommand.COMMAND_WORD:
            return new AddModuleCommandParser().parse(arguments);

        case AddScheduleCommand.COMMAND_WORD:
            logger.fine("User creates an add schedule command ");
            return new AddScheduleCommandParser().parse(arguments);

        case EditStuCommand.COMMAND_WORD:
            return new EditStuCommandParser().parse(arguments);

        case EditTeachingAssistantCommand.COMMAND_WORD:
            return new EditTaParser().parse(arguments);

        case EditModuleCommand.COMMAND_WORD:
            return new EditModuleCommandParser().parse(arguments);

        case EditScheduleCommand.COMMAND_WORD:
            logger.fine("User creates an edit schedule command ");
            return new EditScheduleCommandParser().parse(arguments);

        case DeleteStudentCommand.COMMAND_WORD:
            return new DeleteStudentCommandParser().parse(arguments);

        case DeleteTaCommand.COMMAND_WORD:
            return new DeleteTaCommandParser().parse(arguments);

        case DeleteModuleCommand.COMMAND_WORD:
            return new DeleteModuleCommandParser().parse(arguments);

        case DeleteScheduleCommand.COMMAND_WORD:
            logger.fine("User creates a delete schedule command ");
            return new DeleteScheduleCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case ClearScheduleCommand.COMMAND_WORD:
            return new ClearScheduleCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ViewScheduleCommand.COMMAND_WORD:
            return new ViewScheduleCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ListModuleCommand.COMMAND_WORD:
            return new ListModuleCommand();

        case ViewModuleCommand.COMMAND_WORD:
            return new ModuleViewCommandParser().parse(arguments);

        case ViewTargetModuleCommand.COMMAND_WORD:
            return new ViewTargetModuleCommandParser().parse(arguments);

        case ViewTimeTableCommand.COMMAND_WORD:
            return new ViewTimeTableCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case LightModeCommand.COMMAND_WORD:
            return new LightModeCommand();

        case DarkModeCommand.COMMAND_WORD:
            return new DarkModeCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
