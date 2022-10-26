package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The abstract base class of all AddPersonCommand variations,
 * including AddBuyerCommand, AddDelivererCommand, and AddSupplierCommand.
 */
public abstract class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "add";

    /**
     * Construct a default base AddPersonCommand.
     */
    public AddPersonCommand() {}

    /**
     * Returns the command result to display.
     * This is an abstract method that requires its subclasses,
     * such as {@code AddBuyerCommand, AddDelivererCommand, AddSupplierCommand}, to implement.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult the result to be displayed.
     * @throws CommandException if the command cannot work.
     */
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
