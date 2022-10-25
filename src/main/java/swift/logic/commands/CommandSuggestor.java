package swift.logic.commands;

import java.util.ArrayList;

import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.ArgumentMultimap;
import swift.logic.parser.ArgumentTokenizer;
import swift.logic.parser.Prefix;

/**
 * Suggests a command based on the user input.
 */
public class CommandSuggestor {
    private ArrayList<String> commandList;
    private ArrayList<ArrayList<Prefix>> argPrefixList;

    /**
     * Constructs a {@code CommandSuggestor} with predefined commands and argument prompts.
     */
    public CommandSuggestor() {
        commandList = new ArrayList<>();
        argPrefixList = new ArrayList<>();

        commandList.add(AddContactCommand.COMMAND_WORD);
        argPrefixList.add(AddContactCommand.ARGUMENT_PREFIXES);

        commandList.add(AddTaskCommand.COMMAND_WORD);
        argPrefixList.add(AddTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(ClearCommand.COMMAND_WORD);
        argPrefixList.add(ClearCommand.ARGUMENT_PREFIXES);

        commandList.add(DeleteContactCommand.COMMAND_WORD);
        argPrefixList.add(DeleteContactCommand.ARGUMENT_PREFIXES);

        commandList.add(DeleteTaskCommand.COMMAND_WORD);
        argPrefixList.add(DeleteTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(EditContactCommand.COMMAND_WORD);
        argPrefixList.add(EditContactCommand.ARGUMENT_PREFIXES);

        commandList.add(EditTaskCommand.COMMAND_WORD);
        argPrefixList.add(EditTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(ExitCommand.COMMAND_WORD);
        argPrefixList.add(ExitCommand.ARGUMENT_PREFIXES);

        commandList.add(FindContactCommand.COMMAND_WORD);
        argPrefixList.add(FindContactCommand.ARGUMENT_PREFIXES);

        commandList.add(FindTaskCommand.COMMAND_WORD);
        argPrefixList.add(FindTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(HelpCommand.COMMAND_WORD);
        argPrefixList.add(HelpCommand.ARGUMENT_PREFIXES);

        commandList.add(ListContactCommand.COMMAND_WORD);
        argPrefixList.add(ListContactCommand.ARGUMENT_PREFIXES);

        commandList.add(ListTaskCommand.COMMAND_WORD);
        argPrefixList.add(ListTaskCommand.ARGUMENT_PREFIXES);
    }

    /**
     * Suggests a command based on the user input.
     *
     * @param userInput User input.
     * @return Suggested command.
     * @throws CommandException If the user input is invalid.
     */
    public String suggestCommand(String userInput) throws CommandException {
        assert userInput != null && !userInput.isEmpty();
        String[] userInputArray = userInput.split(" ");
        String commandWord = userInputArray[0];
        String suggestedCommand = "";
        for (String command : commandList) {
            if (command.startsWith(commandWord)) {
                suggestedCommand = command;
                break;
            }
        }

        if (suggestedCommand == "" && commandWord != "") {
            throw new CommandException("Invalid command");
        }
        ArrayList<Prefix> argPrefixes = argPrefixList.get(commandList.indexOf(suggestedCommand));

        if (userInputArray.length > 1) {
            return userInput.stripTrailing() + suggestArguments(argPrefixes, userInput);
        } else {
            return suggestedCommand + suggestArguments(argPrefixes, userInput);
        }
    }

    /**
     * Returns the new user input when user auto-completes the command.
     *
     * @param userInput Current User Input.
     * @param commandSuggestion Current Command Suggestion
     * @return New User Input.
     */
    public String autocompleteCommand(String userInput, String commandSuggestion) {
        // Command suggested but not yet entered by user
        String suggestedCommand = commandSuggestion.substring(userInput.length());
        boolean isCommandComplete = userInput.contains(" ");
        int autocompleteUptoIndex = suggestedCommand.indexOf(isCommandComplete ? "/" : " ") + 1;

        // If command has no prefix arguments
        if(autocompleteUptoIndex == 0) {
            autocompleteUptoIndex = suggestedCommand.length();
        }
        userInput = userInput + suggestedCommand.substring(0, autocompleteUptoIndex);
        return userInput;
    }

    /**
     * Suggests prompts for arguments based on the user input.
     *
     * @param argPrefixes Argument prefixes for specified command.
     * @param userInput Current user input.
     * @return Suggested arguments.
     * @throws CommandException If the user input is invalid.
     */
    public String suggestArguments(
            ArrayList<Prefix> argPrefixes, String userInput) throws CommandException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, argPrefixes.toArray(new Prefix[]{}));
        String argumentSuggestion = "";
        String[] userInputArray = userInput.split(" ");
        Prefix currPrefix = null;

        // Check if user is trying to autocomplete a prefix
        if (userInputArray.length > 1 && !userInputArray[userInputArray.length - 1].contains("/")) {
            argumentSuggestion += "/ ";
            currPrefix = new Prefix(userInputArray[userInputArray.length - 1] + "/");
            argumentMultimap.put(currPrefix, "");
            if (!argPrefixes.contains(currPrefix)) {
                throw new CommandException("Invalid prefix");
            }
        }

        for (Prefix prefix : argPrefixes) {
            if (!argumentMultimap.getValue(prefix).isPresent()) {
                argumentSuggestion += " " + prefix + prefix.getUserPrompt();
            }
        }
        return argumentSuggestion;
    }
}
