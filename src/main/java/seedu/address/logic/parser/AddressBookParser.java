package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemoveFieldCommand;
import seedu.address.logic.commands.RenameCommand;
import seedu.address.logic.commands.creationcommand.FloatCommand;
import seedu.address.logic.commands.creationcommand.IntCommand;
import seedu.address.logic.commands.creationcommand.StringCommand;
import seedu.address.logic.commands.logicalcommand.CheckTaskCompleteCommand;
import seedu.address.logic.commands.logicalcommand.ContainsAttributeCommand;
import seedu.address.logic.commands.logicalcommand.IfCommand;
import seedu.address.logic.commands.persons.PersonCommand;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.logic.commands.tasks.TaskCommand;
import seedu.address.logic.commands.tasks.UnmarkTaskCommand;
import seedu.address.logic.commands.teams.AddUserToTeamCommand;
import seedu.address.logic.commands.teams.ChangeTeamCommand;
import seedu.address.logic.commands.teams.TeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.logiccommands.CheckTaskCompleteCommandParser;
import seedu.address.logic.parser.logiccommands.ContainsAttributeCommandParser;
import seedu.address.logic.parser.logiccommands.IfCommandParser;
import seedu.address.logic.parser.persons.PersonCommandParser;
import seedu.address.logic.parser.tasks.MarkTaskCommandParser;
import seedu.address.logic.parser.tasks.TaskCommandParser;
import seedu.address.logic.parser.tasks.UnmarkTaskCommandParser;
import seedu.address.logic.parser.teams.TeamCommandParser;

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
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand();

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case RemoveFieldCommand.COMMAND_WORD:
                return new RemoveFieldCommandParser().parse(arguments);

            case ChangeTeamCommand.COMMAND_WORD:
                return new ChangeTeamCommandParser().parse(arguments);

            case TaskCommand.COMMAND_WORD:
                return new TaskCommandParser().parse(arguments);

            case AddUserToTeamCommand.COMMAND_WORD:
                return new AddUserToTeamCommandParser().parse(arguments);

            case TeamCommand.COMMAND_WORD:
                return new TeamCommandParser().parse(arguments);

            case CheckTaskCompleteCommand.COMMAND_WORD:
                return new CheckTaskCompleteCommandParser().parse(arguments);

            case ContainsAttributeCommand.COMMAND_WORD:
                return new ContainsAttributeCommandParser().parse(arguments);

            case IfCommand.COMMAND_WORD:
                return new IfCommandParser().parse(arguments);

            case MarkTaskCommand.SUBCOMMAND_WORD:
                return new MarkTaskCommandParser().parse(arguments);

            case UnmarkTaskCommand.SUBCOMMAND_WORD:
                return new UnmarkTaskCommandParser().parse(arguments);

            case RenameCommand.COMMAND_WORD:
                return new RenameCommandParser().parse(arguments);

            case PersonCommand.COMMAND_WORD:
                return new PersonCommandParser().parse(arguments);

            case FloatCommand.COMMAND_WORD:
                return FloatCommand.parser().parse(arguments);

            case IntCommand.COMMAND_WORD:
                return IntCommand.parser().parse(arguments);

            case StringCommand.COMMAND_WORD:
                return StringCommand.parser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
