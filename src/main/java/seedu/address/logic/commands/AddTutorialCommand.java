package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;


/**
 * Adds a tutorial in the tutorial list.
 */
public class AddTutorialCommand extends Command {

    public static final String COMMAND_WORD = "addtut";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial to the address book. "
            + "Parameters: "
            + PREFIX_GROUP + "GROUP NUMBER "
            + PREFIX_CONTENT + "CONTENT "
            + PREFIX_TIME + "TIME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "T08 "
            + PREFIX_CONTENT + "UML Diagram "
            + PREFIX_TIME + "2022-10-01 1400";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "Addtut command not implemented yet";

    public static final String MESSAGE_ARGUMENTS = "Group: %1$s, Content: %2$s, Time: %3$s";
    // I may need to change this later. The time format might need to be specified clearer.

    public static final String MESSAGE_SUCCESS = "New tutorial added: %1$s";
    public static final String MESSAGE_DUPLICATE_TUTORIAL = "This tutorial already exists in the address book";

    private final Tutorial tutorial;

    /**
     * Creates an AddTutorialCommand to add the specified {@code Tutorial}
     */
    public AddTutorialCommand(Tutorial tutorial) {
        requireAllNonNull(tutorial);

        this.tutorial = tutorial;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTutorial(tutorial)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTORIAL);
        }

        model.addTutorial(tutorial);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tutorial));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTutorialCommand)) {
            return false;
        }

        // state check
        AddTutorialCommand e = (AddTutorialCommand) other;
        return tutorial.equals(e);
    }
}
