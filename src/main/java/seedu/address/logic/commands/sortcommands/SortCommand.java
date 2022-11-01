package seedu.address.logic.commands.sortcommands;

import seedu.address.logic.commands.Command;

/**
 * Provides a range of acceptable parameters for sort command.
 */
public abstract class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_UNKNOWN_LIST = "%1$s is not an valid list type\n%2$s";
    public static final String MESSAGE_SUPPORTED_LIST = "The following list parameters are supported: \n"
            + "buyer/b, deliverer/d, supplier/s, order/o, pet/p";
    public static final String MESSAGE_ONLY_ALPHABET_PARAMETERS =
            "Please enter alphabets only for attributes, %1$s is not recognised";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the specified list according to the specified attribute.\n"
            + "Parameters: LIST_TYPE [ATTRIBUTES...]\n"
            + "Examples:\n"
            + COMMAND_WORD + " buyer\n"
            + COMMAND_WORD + " pet price height weight\n"
            + COMMAND_WORD + " s n /loc\n"
            + COMMAND_WORD + " -o pr s p";

}
