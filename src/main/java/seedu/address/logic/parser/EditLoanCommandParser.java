package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_REASON;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLoanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Reason;

/**
 * Parses input arguments and creates a new EditLoanCommand object
 */
public class EditLoanCommandParser implements Parser<EditLoanCommand> {

    private Model model;

    /**
     * Constructs a {@code EditLoanCommandParser}
     * @param model the model of the current state
     */
    public EditLoanCommandParser(Model model) {
        this.model = model;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditLoanCommand
     * and returns an EditLoanCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditLoanCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LOAN_AMOUNT, PREFIX_LOAN_REASON);

        Index index;
        String preamble = argMultimap.getPreamble();
        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            model.filterPersonListByName(preamble, EditLoanCommand.MESSAGE_USAGE, pe);
            index = Index.fromOneBased(1);
        }

        Loan loanChange = ParserUtil.parseLoan(argMultimap.getValue(PREFIX_LOAN_AMOUNT)
                .orElseThrow(() -> new ParseException(Messages.AMOUNT_NOT_SPECIFIED)));

        Reason reasonChange = ParserUtil.parseReason(argMultimap.getValue(PREFIX_LOAN_REASON)
                .orElseThrow(() -> new ParseException(Messages.REASON_NOT_SPECIFIED)));

        LoanHistory toAdd = new LoanHistory(loanChange, reasonChange);

        EditLoanCommand.EditLoanDescriptor editLoanDescriptor =
                new EditLoanCommand.EditLoanDescriptor(loanChange, toAdd);

        return new EditLoanCommand(index, editLoanDescriptor);
    }
}
