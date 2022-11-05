package modtrekt.logic.parser;

import static java.util.Objects.requireNonNull;
import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.beust.jcommander.IUsageFormatter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.UnixStyleUsageFormatter;

import modtrekt.commons.core.LogsCenter;
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
    private static final Logger LOGGER = LogsCenter.getLogger(ModtrektParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        JCommander jcommander = getConfiguredJCommander();
        List<String> tokens = tokenizeCommand(userInput);
        try {
            return parseJCommand(tokens, jcommander);
        } catch (ParameterException ex) { // fallback to the AB3 parser and try again for the old commands
            return parseLegacyCommand(userInput).orElseThrow(() -> getParseException(ex, jcommander));
        }
    }

    /**
     * Returns the JCommander instance configured with all the commands.
     */
    private static JCommander getConfiguredJCommander() {
        // devs: Instantiate your commands here by passing it to addCommand() -
        //       you don't need any CommandParser classes anymore.
        return JCommander.newBuilder().programName("")
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
    }

    /**
     * Returns the list of tokens in the user input.
     *
     * @param userInput full user input string
     * @return the list of tokens in the user input
     * @throws ParseException if the input is blank
     */
    private static List<String> tokenizeCommand(String userInput) throws ParseException {
        if (userInput.isBlank()) {
            throw new ParseException(Messages.MESSAGE_MISSING_COMMAND);
        }
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
        return tokens;
    }

    /**
     * Validates the arity of commands which require a main parameter.
     * JCommander does this validation too late (after conversion to the parameter value)
     * which may throw an exception with a wrong error message, hence the need for this.
     * Currently only validates the few commands which throw the wrong error message when the exception
     * is thrown due to the feature freeze.
     */
    private static void validateCommandArity(List<String> tokens) {
        requireNonNull(tokens);
        if (tokens.isEmpty()) {
            return; // missing command phrase, can't do anything
        }
        String phrase = tokens.get(0);
        Set<String> targets = Set.of(
                RemoveTaskCommand.COMMAND_WORD, RemoveTaskCommand.COMMAND_ALIAS,
                DoneTaskCommand.COMMAND_WORD, UndoneTaskCommand.COMMAND_WORD,
                EditTaskCommand.COMMAND_WORD
        ); // these are the task commands that require a main parameter (we want to check that exactly one is present)
        if (!targets.contains(phrase)) {
            return; // not a command that requires arity validation, nothing to do
        }

        // We must have exactly as many values as there are parameters (the main parameter counts as 1 and is required).
        long numberOfFlags = tokens.stream().filter(token -> token.startsWith("-")).count();
        long numberOfParameters = numberOfFlags + 1; // +1 to include the main parameter
        long numberOfValues = tokens.size() - numberOfFlags - 1; // -1 to exclude the command phrase

        LOGGER.fine(() -> "tokens: " + tokens);
        LOGGER.info(String.format("got %d of %d value(s) expected for `%s`",
                numberOfValues, numberOfParameters, phrase)
        );

        // Throw an exception if there is are missing or excess values.
        if (numberOfValues < numberOfParameters) { // missing values
            // JCommander will take care of the exception for missing parameters,
            // and we can't change this error message displayed because of the feature freeze.
            return; // can't do anything
        } else if (numberOfValues > numberOfParameters) { // excess values
            throw new ParameterException(
                    "Too many values provided. If your values contain spaces, surround them with quotes."
            );
        }
        // else: correct number of values, nothing to do
    }

    private static Command parseJCommand(List<String> tokens, JCommander jcommander) {
        // Parse the command tokens with JCommander.
        // Invalid commands as well as missing, duplicate, or invalid options will throw a ParameterException.
        validateCommandArity(tokens);
        jcommander.parse(tokens.toArray(new String[0]));

        // This cast is safe since we only pass Command objects to jcommander::addCommand.
        return (Command) jcommander.getCommands().get(jcommander.getParsedCommand()).getObjects().get(0);
    }

    private static ParseException getParseException(ParameterException ex, JCommander jcommander) {
        // Discard the main parameter error message if present as it's not relevant to users.
        String parsedCommand = jcommander.getParsedCommand();
        if (parsedCommand == null) { // unknown command
            return new ParseException(ex.getMessage()); // JCommander has its own unknown command message
        }
        JCommander filteredJCommander = jcommander.getCommands().get(parsedCommand);
        if (filteredJCommander == null) { // guarding against NPE in case JCommander internals change
            return new ParseException(ex.getMessage());
        }

        // Add the formatted usage message to the error message.
        String message = ex.getMessage().endsWith("no main parameter was defined in your arg class")
                ? "Syntax error. If your command arguments contain spaces, surround them with quotes."
                // JCommander parses multiple module codes from the input arguments, so we regard the error as
                // invalid syntax provided by the user.
                // Handle main parameter error message for module commands.
                : ex.getMessage().endsWith("Code should only contain alphanumeric characters, "
                + "should not contain white space and should be between 6 and 9 characters long")
                ? "Syntax error. Please ensure your command follows the correct syntax."
                : ex.getMessage();
        IUsageFormatter usageFormatter = new UnixStyleUsageFormatter(filteredJCommander);
        StringBuilder usageBuilder = new StringBuilder(message).append("\n\n");
        usageFormatter.usage(usageBuilder);

        // Rethrow the JCommander unknown command ParameterException using ModtRekt's ParseException as
        // it displays the error message in the UI.
        return new ParseException(usageBuilder.toString());
    }

    private static Optional<Command> parseLegacyCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }
        final String commandWord = matcher.group("commandWord");
        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return Optional.of(new ExitCommand());
        case HelpCommand.COMMAND_WORD:
            return Optional.of(new HelpCommand());
        default:
            return Optional.empty();
        }
    }
}
