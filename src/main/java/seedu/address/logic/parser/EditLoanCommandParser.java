package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_AMBIGUOUS_NAME;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOAN_REASON;

import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditLoanCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Person;
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
            filterPersonListByName(preamble, pe);
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

    /**
     * Filters the {@code ObservableList<Person>} by person name
     * @param preamble the name to search for, by complete word
     * @param pe the ParseException to throw on failure
     * @throws ParseException if there is nobody found by the find command, or there exist
     *      an ambiguity
     */
    private void filterPersonListByName(String preamble, ParseException pe) throws ParseException {
        try {
            new FindCommandParser().parse(preamble).execute(model);
        } catch (ParseException ignored) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLoanCommand.MESSAGE_USAGE), pe);
        }

        ObservableList<Person> filteredPersonList = model.getFilteredPersonList();

        String splitPreamble = Arrays.stream(preamble.split(" "))
                .map(x -> "\"" + x.trim() + "\"")
                .collect(Collectors.joining(" or "));

        if (filteredPersonList.size() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_NAME, splitPreamble), pe);
        } else if (filteredPersonList.size() > 1) {
            throw new ParseException(String.format(MESSAGE_INVALID_AMBIGUOUS_NAME, splitPreamble), pe);
        }
    }

}
