package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.COMMAND_IDENTIFIER_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LECTURE_ZOOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_ZOOM;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to the module list.
 */
public class AddModuleCommand extends Command {

    public static final String COMMAND_TYPE = "add";
    public static final String COMMAND_WORD = COMMAND_TYPE + COMMAND_IDENTIFIER_MODULE;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the module list. "
        + "Parameters: "
        + PREFIX_MODULE + "MODULE "
        + PREFIX_LECTURE + "LECTURE "
        + PREFIX_TUTORIAL + "TUTORIAL "
        + PREFIX_LECTURE_ZOOM + "LECTURE ZOOM "
        + PREFIX_TUTORIAL_ZOOM + "TUTORIAL ZOOM "
        + "[" + PREFIX_ASSIGNMENT + "ASSIGNMENT]...\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_MODULE + "CS2100 "
        + PREFIX_LECTURE + "Monday 10am "
        + PREFIX_TUTORIAL + "Thursday 12pm "
        + PREFIX_LECTURE_ZOOM + "https://nus-sg.zoom.us/j/82167158590 "
        + PREFIX_TUTORIAL_ZOOM + "https://nus-sg.zoom.us/j/82167158591 "
        + PREFIX_ASSIGNMENT + "Tutorial1 "
        + PREFIX_ASSIGNMENT + "Lab1";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in the module list";

    private final Module toAdd;

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module module) {
        requireNonNull(module);
        toAdd = module;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddModuleCommand // instanceof handles nulls
            && toAdd.equals(((AddModuleCommand) other).toAdd));
    }
}
