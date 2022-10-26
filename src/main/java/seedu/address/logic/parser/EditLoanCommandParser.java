package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLoanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Reason;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_REASON;


public class EditLoanCommandParser implements Parser<EditLoanCommand> {

    private Model model;

    public EditLoanCommandParser(Model model) {
        this.model = model;
    }

    public EditLoanCommand parse (String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LOAN_AMOUNT, PREFIX_LOAN_REASON);
        Index index;
        String preamble = argMultimap.getPreamble();
        index = ParserUtil.parseIndex(preamble);


        Loan loanChange = ParserUtil.parseLoan(argMultimap.getValue(PREFIX_LOAN_AMOUNT).get());
        Reason reasonChange = ParserUtil.parseReason(argMultimap.getValue(PREFIX_LOAN_REASON).get());
        LoanHistory toAdd = new LoanHistory(loanChange, reasonChange);

        EditLoanCommand.EditLoanDescriptor editLoanDescriptor = new EditLoanCommand.EditLoanDescriptor();
        editLoanDescriptor.setLoan(loanChange);
        editLoanDescriptor.setHistory(toAdd);

        return new EditLoanCommand(index, editLoanDescriptor, toAdd);
    }

}
