package swift.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;

import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.ArgumentMultimap;
import swift.logic.parser.ArgumentTokenizer;
import swift.logic.parser.Prefix;

/**
 * Suggests a command based on the user input.
 */
public class CommandSuggestor {
    private ArrayList<String> commandList;
    private ArrayList<HashMap<Prefix, String>> argPromptList;

    /**
     * Constructs a {@code CommandSuggestor} with predefined commands and argument prompts.
     */
    public CommandSuggestor() {
        commandList = new ArrayList<>();
        argPromptList = new ArrayList<>();

        commandList.add(AddContactCommand.COMMAND_WORD);
        argPromptList.add(AddContactCommand.ARGUMENT_PROMPTS);

        commandList.add(AddTaskCommand.COMMAND_WORD);
        argPromptList.add(AddTaskCommand.ARGUMENT_PROMPTS);

        commandList.add(ClearCommand.COMMAND_WORD);
        argPromptList.add(ClearCommand.ARGUMENT_PROMPTS);

        commandList.add(DeleteContactCommand.COMMAND_WORD);
        argPromptList.add(DeleteContactCommand.ARGUMENT_PROMPTS);

        commandList.add(DeleteTaskCommand.COMMAND_WORD);
        argPromptList.add(DeleteTaskCommand.ARGUMENT_PROMPTS);

        commandList.add(EditContactCommand.COMMAND_WORD);
        argPromptList.add(EditContactCommand.ARGUMENT_PROMPTS);

        commandList.add(EditTaskCommand.COMMAND_WORD);
        argPromptList.add(EditTaskCommand.ARGUMENT_PROMPTS);

        commandList.add(ExitCommand.COMMAND_WORD);
        argPromptList.add(ExitCommand.ARGUMENT_PROMPTS);

        commandList.add(FindContactCommand.COMMAND_WORD);
        argPromptList.add(FindContactCommand.ARGUMENT_PROMPTS);

        commandList.add(FindTaskCommand.COMMAND_WORD);
        argPromptList.add(FindTaskCommand.ARGUMENT_PROMPTS);

        commandList.add(HelpCommand.COMMAND_WORD);
        argPromptList.add(HelpCommand.ARGUMENT_PROMPTS);

        commandList.add(ListContactCommand.COMMAND_WORD);
        argPromptList.add(ListContactCommand.ARGUMENT_PROMPTS);

        commandList.add(ListTaskCommand.COMMAND_WORD);
        argPromptList.add(ListTaskCommand.ARGUMENT_PROMPTS);
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
        HashMap<Prefix, String> argPrompt = argPromptList.get(commandList.indexOf(suggestedCommand));

        if (userInputArray.length > 1) {
            return userInput.stripTrailing() + suggestArguments(argPrompt, userInput);
        } else {
            return suggestedCommand + suggestArguments(argPrompt, userInput);
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

        userInput = userInput + suggestedCommand.substring(0, autocompleteUptoIndex);
        return userInput;
    }

    /**
     * Suggests prompts for arguments based on the user input.
     *
     * @param argPrompt Argument prompts for specified command.
     * @param userInput Current user input.
     * @return Suggested arguments.
     * @throws CommandException If the user input is invalid.
     */
    public String suggestArguments(HashMap<Prefix, String> argPrompt, String userInput) throws CommandException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, argPrompt.keySet()
            .toArray(new Prefix[] {}));
        String argumentSuggestion = "";
        String[] userInputArray = userInput.split(" ");
        Prefix currPrefix = null;

        // Check if user is trying to autocomplete a prefix
        if (userInputArray.length > 1 && !userInputArray[userInputArray.length - 1].contains("/")) {
            argumentSuggestion += "/ ";
            currPrefix = new Prefix(userInputArray[userInputArray.length - 1] + "/");
            argumentMultimap.put(currPrefix, "");
            if (argPrompt.get(currPrefix) == null) {
                throw new CommandException("Invalid prefix");
            }
        }

        for (Prefix key : argPrompt.keySet()) {
            if (!argumentMultimap.getValue(key).isPresent()) {
                argumentSuggestion += " " + key + argPrompt.get(key);
            }
        }
        return argumentSuggestion;
    }
}
