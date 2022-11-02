package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beust.jcommander.IUsageFormatter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.UnixStyleUsageFormatter;

import modtrekt.commons.core.Messages;
import modtrekt.commons.util.StringUtil;
import modtrekt.logic.commands.AddModuleCommand;
import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.logic.commands.CdModuleCommand;
import modtrekt.logic.commands.Command;
import modtrekt.logic.commands.DoneModuleCommand;
import modtrekt.logic.commands.EditModuleCommand;
import modtrekt.logic.commands.EditTaskCommand;
import modtrekt.logic.commands.ExitCommand;
import modtrekt.logic.commands.HelpCommand;
import modtrekt.logic.commands.ListModulesCommand;
import modtrekt.logic.commands.RemoveModuleCommand;
import modtrekt.logic.commands.RemoveTaskCommand;
import modtrekt.logic.commands.UndoneModuleCommand;
import modtrekt.logic.commands.tasks.DoneTaskCommand;
import modtrekt.logic.commands.tasks.ListTasksCommand;
import modtrekt.logic.commands.tasks.UndoneTaskCommand;
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
                // tasks
                .addCommand(ListTasksCommand.COMMAND_PHRASE, new ListTasksCommand(),
                        ListTasksCommand.COMMAND_ALIAS
                )
                .addCommand(AddTaskCommand.COMMAND_WORD, new AddTaskCommand())
                .addCommand(EditTaskCommand.COMMAND_WORD, new EditTaskCommand())
                .addCommand(DoneTaskCommand.COMMAND_WORD, new DoneTaskCommand())
                .addCommand(UndoneTaskCommand.COMMAND_WORD, new UndoneTaskCommand())
                .addCommand(RemoveTaskCommand.COMMAND_WORD, new RemoveTaskCommand(),
                        RemoveTaskCommand.COMMAND_ALIAS)
                // modules
                .addCommand(AddModuleCommand.COMMAND_WORD, new AddModuleCommand(),
                        AddModuleCommand.COMMAND_ALIAS)
                .addCommand(EditModuleCommand.COMMAND_WORD, new EditModuleCommand(),
                        EditModuleCommand.COMMAND_ALIAS)
                .addCommand(DoneModuleCommand.COMMAND_WORD, new DoneModuleCommand(),
                        DoneModuleCommand.COMMAND_ALIAS)
                .addCommand(UndoneModuleCommand.COMMAND_WORD, new UndoneModuleCommand(),
                        UndoneModuleCommand.COMMAND_ALIAS)
                .addCommand(ListModulesCommand.COMMAND_PHRASE, new ListModulesCommand(),
                        ListModulesCommand.COMMAND_ALIAS)
                .addCommand(CdModuleCommand.COMMAND_WORD, new CdModuleCommand())
                .addCommand(RemoveModuleCommand.COMMAND_WORD, new RemoveModuleCommand(),
                        RemoveModuleCommand.COMMAND_ALIASES)
                .build();
        try {
            // Get the tokens from the user input.
            // ARGUMENTS WITH SPACES MUST BE SURROUNDED BY DOUBLE-QUOTES.
            List<String> tokens = StringUtil.shellSplit(userInput.strip());

            // Since we're treating e.g. "add task" and "add module" as separate commands,
            // we'll consider "task" or "module" the scope of the command, and add it to the command word.
            String scope = tokens.size() >= 2 ? tokens.get(1) : null;
            // support shorthand for module, no extra -m or module flag needed, hence we remove
            // it from the token list.
            if ("module".equals(scope) || "mod".equals(scope) || "task".equals(scope)) {
                tokens.remove(1);
                tokens.set(0, tokens.get(0) + " " + scope);
            }

            // Parse the command tokens with JCommander.
            // Invalid commands as well as missing, duplicate, or invalid options will throw a ParameterException.
            jcommander.parse(tokens.toArray(new String[0]));

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
                    // JCommander parses multiple module codes from the input arguments, so we regard the error as
                    // invalid syntax provided by the user.
                    : ex.getMessage().endsWith("Code should only contain alphanumeric characters, " +
                    "should not contain white space and should be between 6 and 9 characters long")
                    ? "Syntax error. Please ensure your command follows the correct syntax."
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
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        default:
            return null;
        }
    }
}
