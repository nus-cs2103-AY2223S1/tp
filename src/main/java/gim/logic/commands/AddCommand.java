package gim.logic.commands;

import static gim.logic.parser.CliSyntax.PREFIX_NAME;
import static gim.logic.parser.CliSyntax.PREFIX_REPS;
import static gim.logic.parser.CliSyntax.PREFIX_SETS;
import static gim.logic.parser.CliSyntax.PREFIX_TAG;
import static gim.logic.parser.CliSyntax.PREFIX_WEIGHT;
import static java.util.Objects.requireNonNull;

import gim.logic.commands.exceptions.CommandException;
import gim.model.Model;
import gim.model.exercise.Exercise;


/**
 * Adds an exercise to Gim.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = ":a";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to Gim. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_SETS + "SETS "
            + PREFIX_REPS + "REPS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Squat "
            + PREFIX_WEIGHT + "60 "
            + PREFIX_SETS + "1 "
            + PREFIX_REPS + "5 "
            + PREFIX_TAG + "25/01/2022";

    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";
    public static final String MESSAGE_DUPLICATE_EXERCISE = "This exercise already exists in the exercise tracker";

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

        //        if (model.hasExercise(toAdd)) {
        //            throw new CommandException(MESSAGE_DUPLICATE_EXERCISE);
        //        }

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
