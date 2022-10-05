package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.stall.Stall;

/**
 * Adds a stall to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a stall to the address book. "
            + "Parameters: "
            + CliSyntax.PREFIX_NAME + "NAME "
            + CliSyntax.PREFIX_PHONE + "PHONE "
            + CliSyntax.PREFIX_ADDRESS + "ADDRESS "
            + "[" + CliSyntax.PREFIX_DETAIL + "DETAIL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_NAME + "John Doe "
            + CliSyntax.PREFIX_PHONE + "98765432 "
            + CliSyntax.PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + CliSyntax.PREFIX_DETAIL + "friends "
            + CliSyntax.PREFIX_DETAIL + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New stall added: %1$s";
    public static final String MESSAGE_DUPLICATE_STALL = "This stall already exists in the address book";

    private final Stall toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Stall}
     */
    public AddCommand(Stall stall) {
        requireNonNull(stall);
        toAdd = stall;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStall(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STALL);
        }

        model.addStall(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
