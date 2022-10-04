package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static gim.logic.parser.CliSyntax.PREFIX_EMAIL;
import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_PHONE;
import static gim.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import gim.logic.commands.exceptions.CommandException;
import gim.model.Model;
import gim.model.exercise.Exercise;

/**
 * Adds a exercise to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a exercise to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the address book";

    private final Exercise toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Exercise}
     */
    public AddCommand(Exercise exercise) {
        requireNonNull(exercise);
        toAdd = exercise;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasExercise(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        }

        model.addExercise(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
