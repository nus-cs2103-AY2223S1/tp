package seedu.address.logic.commands.sortcommands;

import seedu.address.logic.commands.Command;

/**
 * The abstract base class of all SortCommand variations,
 * including SortBuyerCommand, SortDelivererCommand, SortSupplierCommand, SortOrderCommand, SortPetCommand.
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_UNKNOWN_LIST = "%1$s is not an valid list type\n%2$s";
    public static final String MESSAGE_SUPPORTED_LIST = "The following lists are supported: \n"
            + "buyer, deliverer, supplier, order, pet";
    public static final String MESSAGE_ONLY_ALPHABET_PARAMETERS =
            "Please enter alphabets only for attributes, %1$s is not recognised";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the specified list according to the specified attribute.\n"
            + "Parameters: LIST_TYPE [ATTRIBUTES...]\n"
            + "Examples:\n"
            + COMMAND_WORD + " buyer\n"
            + COMMAND_WORD + " pet price height weight\n";

}
