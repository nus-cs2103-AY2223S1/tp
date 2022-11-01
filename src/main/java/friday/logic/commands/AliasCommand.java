package friday.logic.commands;

import static friday.logic.parser.CliSyntax.PREFIX_ALIAS;
import static friday.logic.parser.CliSyntax.PREFIX_RESERVED_KEYWORD;
import static java.util.Objects.requireNonNull;

import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;

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
    public static final String MESSAGE_INVALID_ALIAS = "Alias given is invalid.\n"
            + "Alias cannot be a reserved keyword and must contain only 1 word.";
    public static final String MESSAGE_INVALID_KEYWORD = "Keyword given is not a reserved keyword";

    private final Alias toAdd;
    private final ReservedKeyword keyword;

    /**
     * Creates an AliasCommand to add the specified {@code alias}
     */
    public AliasCommand(Alias alias, ReservedKeyword reservedKeyword) {
        requireNonNull(alias);
        requireNonNull(reservedKeyword);
        toAdd = alias;
        keyword = reservedKeyword;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!ReservedKeyword.isValidReservedKeyword(keyword.toString())) {
            throw new CommandException(MESSAGE_INVALID_KEYWORD);
        }

        if (!Alias.isValidAlias(toAdd.toString())) {
            throw new CommandException(MESSAGE_INVALID_ALIAS);
        }

        if (model.hasAlias(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_ALIAS);
        }

        model.addAlias(toAdd, keyword);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || ((other instanceof AliasCommand) // instanceof handles nulls
                && toAdd.equals(((AliasCommand) other).toAdd)
                && keyword.equals(((AliasCommand) other).keyword));
    }
}
