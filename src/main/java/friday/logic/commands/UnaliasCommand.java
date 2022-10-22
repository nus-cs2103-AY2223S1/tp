package friday.logic.commands;

import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static java.util.Objects.requireNonNull;

import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.alias.Alias;

/**
 * Removes an alias from FRIDAY.
 */
public class UnaliasCommand extends Command {

    public static final String COMMAND_WORD = "unalias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes an alias from FRIDAY. "
            + "Parameters: "
            + PREFIX_ALIAS + "ALIAS "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ALIAS + "ls ";

    public static final String MESSAGE_SUCCESS = "Alias removed: %1$s";
    public static final String MESSAGE_ALIAS_NOT_FOUND = "Alias not in FRIDAY";

    private final Alias toRemove;

    /**
     * Creates an UnaliasCommand to remove the specified {@code alias}
     */
    public UnaliasCommand(Alias alias) {
        requireNonNull(alias);
        toRemove = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasAlias(toRemove)) {
            throw new CommandException(MESSAGE_ALIAS_NOT_FOUND);
        }

        model.removeAlias(toRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnaliasCommand // instanceof handles nulls
                && toRemove.equals(((UnaliasCommand) other).toRemove));
    }
}
