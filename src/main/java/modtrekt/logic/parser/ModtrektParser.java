package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.logic.commands.utils.AddCommandMessages.MESSAGE_ADD_COMMAND_PREFIXES;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beust.jcommander.IUsageFormatter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.UnixStyleUsageFormatter;

import modtrekt.commons.core.Messages;
import modtrekt.commons.util.StringUtil;
import modtrekt.logic.commands.AddCommand;
import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.logic.commands.CdModuleCommand;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.EditTaskCommand;
import modtrekt.logic.commands.ExitCommand;
import modtrekt.logic.commands.HelpCommand;
import modtrekt.logic.commands.RemoveCommand;
import modtrekt.logic.commands.tasks.ArchiveTaskCommand;
import modtrekt.logic.commands.tasks.ListTasksCommand;
import modtrekt.logic.commands.tasks.PrioritizeTaskCommand;
import modtrekt.logic.commands.tasks.UnarchiveTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ModtrektParser {
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
        if (userInput.isBlank()) {
            throw new ParseException(Messages.MESSAGE_MISSING_COMMAND);
        }
        // devs: Instantiate your commands here by passing it to addCommand() -
        //       you don't need any CommandParser classes anymore.
        JCommander jcommander = JCommander.newBuilder().programName("")
                .addCommand(ListTasksCommand.COMMAND_WORD, new ListTasksCommand())
                .addCommand(ArchiveTaskCommand.COMMAND_WORD, new ArchiveTaskCommand())
                .addCommand(UnarchiveTaskCommand.COMMAND_WORD, new UnarchiveTaskCommand())
                .addCommand(PrioritizeTaskCommand.COMMAND_WORD, new PrioritizeTaskCommand())
                .addCommand(EditTaskCommand.COMMAND_WORD, new EditTaskCommand())
                .build();
        try {
            // This takes care of invalid commands, as well as missing or invalid arguments
            // via the ParameterException.
            // Arguments with spaces MUST BE SURROUNDED BY QUOTES.
            String[] args = StringUtil.shellSplit(userInput.strip());
            jcommander.parse(args);
            // This cast is safe since we only pass Command objects to jcommander::addCommand.
            return (Command) jcommander.getCommands().get(jcommander.getParsedCommand()).getObjects().get(0);
        } catch (ParameterException ex) {
            // Fallback to the legacy AB3 parser if the command is not recognized by JCommander.
            Command command = parseLegacyCommand(userInput);
            if (command != null) {
                return command;
            }

            // Discard the main parameter error message if present as it's not relevant to users.
            String parsedCommand = jcommander.getParsedCommand();
            if (parsedCommand == null) { // unknown command
                throw new ParseException(ex.getMessage()); // JCommander has its own unknown command message
            }
            JCommander filteredJCommander = jcommander.getCommands().get(parsedCommand);
            if (filteredJCommander == null) { // guarding against NPE in case JCommander internals change
                throw new ParseException(ex.getMessage());
            }

            // Add the formatted usage message to the error message.
            String message = ex.getMessage().endsWith("no main parameter was defined in your arg class")
                    ? "Syntax error. If your command arguments contain spaces, surround them with quotes."
                    : ex.getMessage();
            IUsageFormatter usageFormatter = new UnixStyleUsageFormatter(filteredJCommander);
            StringBuilder usageBuilder = new StringBuilder(message).append("\n\n");
            usageFormatter.usage(usageBuilder);

            // Rethrow the JCommander unknown command ParameterException using ModtRekt's ParseException as
            // it displays the error message in the UI.
            throw new ParseException(usageBuilder.toString());
        }
    }

    private Command parseLegacyCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {
        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);
        case AddCommand.COMMAND_WORD:
            if (arguments.contains(AddCommand.COMMAND_IDENTIFIER)) {
                return new AddCommandParser().parse(arguments);
            } else if (arguments.contains(AddTaskCommand.COMMAND_IDENTIFIER)) {
                return new AddTaskCommandParser().parse(arguments);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADD_COMMAND_PREFIXES));
            }
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case CdModuleCommand.COMMAND_WORD:
            return new CdCommandParser().parse(arguments);
        default:
            return null;
        }
    }
}
