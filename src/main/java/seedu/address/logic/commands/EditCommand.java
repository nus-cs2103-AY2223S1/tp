package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;


/**
 * Edits the details of an existing client in the address book.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the client identified "
            + "by the index number used in the displayed client list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_MODE + "client/transaction/remark] "
            + "+ respective information fields in client: n/NAME a/ADDRESS p/PHONE e/EMAIL t/TAG"
            + " or transaction: g/GOOD price/PRICE q/QUANTITY d/DATE"
            + " (for m/remark you only need to add the string"
            + "remark after the m/remark prefix"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODE +"client" + "a/Blk 221 Yishun St 81"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODE +"transaction" + "g/oranges price/1.30"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODE +"remark" + "new Remark that I want to add";


}

