package gim.logic.commands;

import gim.model.Model;

/**
 * Generates a sample workout based on existing PRs, according to difficulty level specified.
 */
public class GenerateCommand extends Command {

    public static final String COMMAND_WORD = ":gen";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from generate");
    }
}