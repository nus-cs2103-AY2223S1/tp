package friday.logic.commands;

import static java.util.Objects.requireNonNull;

import friday.model.Model;

/**
 * Lists all aliases in FRIDAY to the user.
 */
public class AliasListCommand extends Command {

    public static final String COMMAND_WORD = "aliaslist";

    public static final String MESSAGE_SUCCESS = "Listed all alias: %1$s";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String aliasesString = model.displayAliases();
        return new CommandResult(String.format(MESSAGE_SUCCESS, aliasesString));
    }
}
