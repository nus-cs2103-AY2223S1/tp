package seedu.address.logic.commands.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;

/**
 * Adds a tutorial to the ModQuik.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "addt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the ModQuik. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T W17 ";

    public static final String MESSAGE_SUCCESS = "New tutorial added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in the ModQuik";

    private final Tutorial toAdd;

    /**
     * Creates an AddTutorialCommand to add the specified {@code Tutorial}
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireNonNull(tutorial);
        toAdd = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorial(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.addTutorial(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTutorialCommand // instanceof handles nulls
                && toAdd.equals(((AddTutorialCommand) other).toAdd));
    }
}
