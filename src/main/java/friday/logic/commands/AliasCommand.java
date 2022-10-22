package friday.logic.commands;

import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static friday.logic.parser.CliSyntax.PREFIX_RESERVED_KEYWORD;
import static java.util.Objects.requireNonNull;

import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;

/**
 * Adds an alias for commands to FRIDAY.
 */
public class AliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an alias to FRIDAY. "
            + "Parameters: "
            + PREFIX_ALIAS + "ALIAS "
            + PREFIX_RESERVED_KEYWORD + "DEFAULT_COMMAND \n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ALIAS + "ls "
            + PREFIX_RESERVED_KEYWORD + ListCommand.COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "New alias added: %1$s";
    public static final String MESSAGE_DUPLICATE_ALIAS = "This alias already exists in FRIDAY";

    private final String toAdd;

    /**
     * Creates an AliasCommand to add the specified {@code alias}
     */
    public AliasCommand(String alias) {
        requireNonNull(alias);
        toAdd = alias;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAlias(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ALIAS);
        }

        model.addAlias(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AliasCommand // instanceof handles nulls
                && toAdd.equals(((AliasCommand) other).toAdd));
    }
}
