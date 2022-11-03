package seedu.address.logic.commands.filtercommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * The abstract base class of all FilterCommand variations,
 * including FilterOrderCommand and FilterPetCommand.
 */
public abstract class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";
    public static final String COMMAND_INPUT = "filter-KEY";

    public static final String MESSAGE_USAGE = COMMAND_INPUT
            + ": Displays items based on the specified attribute(s).\n"
            + "Parameter: ITEM_TYPE PREFIX/INPUT\n"
            + "Examples:\n"
            + "filter-o os/Pending : filters orders that have an order status of Pending.\n"
            + "filter-o os/Negotiating pr/90-900 ar/good with children : filters orders that have an order status of "
            + "Pending, price range of 90 to 900, and additional request of good with children.\n"
            + "filter-p p_c/white : filters pets that have a color white. \n"
            + "filter-p p_c/black p_n/doraemon p_p/50 p_s/cat p_v/true : filters pets that have a color black, "
            + "the name doraemon, "
            + "the species cat, and vaccination status of true.\n";
    /**
     * Creates a default base DeleteCommand.
     */
    public FilterCommand() {}

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
