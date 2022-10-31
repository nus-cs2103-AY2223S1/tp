package seedu.address.logic.commands.deletecommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * The abstract base class of all DeleteCommand variations,
 * including DeleteBuyerCommand, DeleteDelivererCommand, DeleteSupplierCommand, DeleteOrderCommand, DeletePetCommand.
 */
public abstract class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_INPUT = "delete-KEY";

    public static final String MESSAGE_USAGE = COMMAND_INPUT
            + ": Deletes the person/item identified by index used in the displayed person/item list.\n"
            + "Parameter: INDEX (must be a positive integer)\n"
            + "Examples:\n"
            + "delete-b 1 : deletes the buyer of index 1\n"
            + "delete-d 2 : deletes the deliverer of index 2\n"
            + "delete-s 3 : deletes the supplier of index 3\n"
            + "delete-o 1 : deletes the order of index 1\n"
            + "delete-p 2 : deletes the pet of index 2";
    /**
     * Creates a default base DeleteCommand.
     */
    public DeleteCommand() {}

    /**
     * Returns the command result to display.
     * This is an abstract method that requires its subclasses,
     * such as {@code DeleteBuyerCommand, DeleteDelivererCommand, DeleteSupplierCommand, DeleteOrderCommand,
     * DeletePetCommand}, to implement.
     *
     * @param model {@code Model} which the command should operate on.
     * @return CommandResult the result to be displayed.
     * @throws CommandException if the command cannot work.
     */
    @Override
    public abstract CommandResult execute(Model model) throws CommandException;
}
