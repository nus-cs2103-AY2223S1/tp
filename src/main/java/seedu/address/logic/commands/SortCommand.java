package seedu.address.logic.commands;

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

}
