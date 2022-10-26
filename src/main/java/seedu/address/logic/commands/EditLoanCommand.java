package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Edits the loan value of an existing person in the address book.
 */
public class EditLoanCommand extends Command{

    public static final String COMMAND_WORD = "editLoan";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the loan value of the persion identified "
            + "by the index number used in the displayed person list. "
            + "The existing loan value will be added to the input value.\n"
            + "Parameters: INDEX (can be a positive or negative integer) "
            + PREFIX_LOAN_AMOUNT + "AMOUNT"
            + PREFIX_LOAN_REASON + "REASON\n"
            + "Example: " + COMMAND_WORD + "1 "
            + PREFIX_LOAN_AMOUNT + "20"
            + PREFIX_LOAN_REASON + "Buy logistics";


    public static final String MESSAGE_EDIT_LOAN_SUCCESS = ""
}
