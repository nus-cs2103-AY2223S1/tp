package swift.logic.commands;

import static swift.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.ArrayList;
import java.util.Collections;

import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.ArgumentMultimap;
import swift.logic.parser.ArgumentTokenizer;
import swift.logic.parser.Prefix;

/**
 * Suggests a command based on the user input.
 */
public class CommandSuggestor {
    private final ArrayList<String> commandList;
    private final ArrayList<ArrayList<Prefix>> argPrefixList;

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

        commandList.add(SelectContactCommand.COMMAND_WORD);
        argPrefixList.add(SelectContactCommand.ARGUMENT_PREFIXES);

        commandList.add(SelectTaskCommand.COMMAND_WORD);
        argPrefixList.add(SelectTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(MarkTaskCommand.COMMAND_WORD);
        argPrefixList.add(MarkTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(UnmarkTaskCommand.COMMAND_WORD);
        argPrefixList.add(UnmarkTaskCommand.ARGUMENT_PREFIXES);

        commandList.add(AssignCommand.COMMAND_WORD);
        argPrefixList.add(AssignCommand.ARGUMENT_PREFIXES);

        commandList.add(UnassignCommand.COMMAND_WORD);
        argPrefixList.add(UnassignCommand.ARGUMENT_PREFIXES);
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
        String[] userInputArray = userInput.split(" ", 2);
        String commandWord = userInputArray[0];
        String suggestedCommand = "";
        boolean isCommandComplete = userInput.contains(" ");

        for (String command : commandList) {
            if (command.startsWith(commandWord)) {
                if (isCommandComplete && !command.equals(commandWord)) {
                    continue;
                }
                suggestedCommand = command;
                break;
            }
        }

        if (suggestedCommand.equals("") && !commandWord.equals("")) {
            throw new CommandException("Invalid command");
        }
        ArrayList<Prefix> argPrefixes = argPrefixList.get(commandList.indexOf(suggestedCommand));

        if (userInputArray.length > 1) {
            return userInput.stripTrailing() + suggestArguments(argPrefixes, userInputArray[1]);
        } else {
            return suggestedCommand + suggestArguments(argPrefixes, "");
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
        int autocompleteUptoIndex;
        if (isCommandComplete) {
            autocompleteUptoIndex = suggestedCommand.indexOf(isCommandComplete ? "/" : " ") + 1;
        } else {
            return getLongestMatchingPrefixSuggestion(userInput);
        }

        // If command has no prefix arguments
        if (autocompleteUptoIndex == 0) {
            autocompleteUptoIndex = suggestedCommand.length();
        }

        String autocompletedCommand = suggestedCommand.substring(0, autocompleteUptoIndex);
        if(!autocompletedCommand.contains("<")) {
            userInput = userInput + autocompletedCommand;
        }
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
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(" " + userInput, argPrefixes.toArray(new Prefix[] {}));
        String argumentSuggestion = "";
        String[] userInputArray = userInput.trim().split(" ");
        Prefix currPrefix = null;
        boolean isIndexRequired = argPrefixes.contains(new Prefix(""));
        boolean hasKeyword = argPrefixes.contains(PREFIX_KEYWORD);
        boolean hasPrefix = (!userInput.isEmpty() && (!isIndexRequired || userInputArray.length > 1));

        // Check if user input for index is valid (only if required)
        if (isIndexRequired) {
            if (userInputArray[0].equals("")) {
                argumentSuggestion += " " + argPrefixes.get(0).getUserPrompt();
            } else {
                if (!userInputArray[0].matches("-?\\d+(\\.\\d+)?")) {
                    throw new CommandException("Invalid index");
                }
            }
        }

        if (hasKeyword) {
            // Check if user input contains keyword
            if (userInput.equals("")) {
                argumentSuggestion += " " + argPrefixes.get(0).getUserPrompt();
            }
            argumentMultimap.put(PREFIX_KEYWORD, "");
        } else if (hasPrefix && !userInputArray[userInputArray.length - 1].contains("/")) {
            // Check if user is trying to autocomplete a prefix
            currPrefix = new Prefix(userInputArray[userInputArray.length - 1] + "/");
            argumentMultimap.put(currPrefix, "");

            if (argPrefixes.contains(currPrefix)) {
                argumentSuggestion += "/ ";
            } else if (!userInput.contains("/")) {
                throw new CommandException("Invalid prefix");
            }
        }

        for (Prefix prefix : argPrefixes) {
            if (argumentMultimap.getValue(prefix).isEmpty()) {
                argumentSuggestion += " " + prefix + prefix.getUserPrompt();
            }
        }
        return argumentSuggestion;
    }

    /**
     * Gets the longest matching prefix from all possible command suggestions depending on the user
     * input.
     *
     * @param userInput User input.
     * @return Longest matching prefix.
     * @throws CommandException If the user input is invalid.
     */
    public String getLongestMatchingPrefixSuggestion(String userInput) {
        assert userInput != null && !userInput.isEmpty();
        String[] userInputArray = userInput.split(" ", 2);
        String commandWord = userInputArray[0];
        boolean isCommandComplete = userInput.contains(" ");
        ArrayList<String> matchingCommands = new ArrayList<>();

        for (String command : commandList) {
            if (command.startsWith(commandWord)) {
                if (isCommandComplete && !command.equals(commandWord)) {
                    continue;
                }
                matchingCommands.add(command + " ");
            }
        }
        return getLongestMatchingPrefix(matchingCommands);
    }

    /**
     * Gets longest matching prefix from list of strings.
     *
     * @param matchingCommands List of strings.
     * @return Longest matching prefix.
     */
    public String getLongestMatchingPrefix(ArrayList<String> matchingCommands) {
        Collections.sort(matchingCommands);
        int size = matchingCommands.size();
        if (size == 0) {
            return "";
        }

        if (size == 1) {
            return matchingCommands.get(0);
        }

        // find the minimum length from first and last string
        int end =
                Math.min(matchingCommands.get(0).length(), matchingCommands.get(size - 1).length());

        // find the common prefix between the first and last string
        int i = 0;
        while (i < end
                && matchingCommands.get(0).charAt(i) == matchingCommands.get(size - 1).charAt(i)) {
            i++;
        }

        String prefix = matchingCommands.get(0).substring(0, i);
        return prefix;
    }
}
