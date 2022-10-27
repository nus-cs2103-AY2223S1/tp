package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GOODS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;


/**
 * Edits the details of an existing client in the address book.
 */
public abstract class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of "
            + "an entity (client/transaction/remark) identified "
            + "by the index number used in the displayed client list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MODE + "MODE (must be a client, transaction or remark) FIELDS [MORE_FIELDS]...\n"
            + "FIELDS for Client: " + PREFIX_NAME + "NAME " + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_PHONE + "PHONE " + PREFIX_EMAIL + "EMAIL " + PREFIX_TAG + "TAG\n"
            + "FIELDS for Transaction: " + PREFIX_GOODS + "GOOD " + PREFIX_PRICE + "PRICE "
            + PREFIX_QUANTITY + "QUANTITY " + PREFIX_DATE + "DATE\n"
            + "FIELDS for Remark: only need to add the string to replace the current remark\n"
            + "Example: " + COMMAND_WORD + " 2 " + PREFIX_MODE + "client a/Blk 221 Yishun St 81\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MODE + "transaction g/oranges price/1.30\n"
            + "Example: " + COMMAND_WORD + " 3 " + PREFIX_MODE + "remark new Remark that I want to replace";
}

