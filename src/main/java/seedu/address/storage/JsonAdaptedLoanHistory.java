package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Reason;
import seedu.address.model.tag.Tag;

public class JsonAdaptedLoanHistory {

    private final String loan;
    private final String reason;

    /**
     * Constructs a {@code JsonAdaptedLoanHistory} with the given details.
     */
    @JsonCreator
    public JsonAdaptedLoanHistory(@JsonProperty("loan") String loan, @JsonProperty("reason") String reason) {
        this.loan = loan;
        this.reason = reason;
    }

    /**
     * Converts a given {@code LoanHistory} into this class for Jackson use.
     */
    public JsonAdaptedLoanHistory(LoanHistory source) {
        this.loan = source.getLoanChange().toString();
        this.reason = source.getReason().toString();
    }

    /**
     * Converts this Jackson-friendly adapted LoanHistory object into the model's {@code LoanHistory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted LoanHistory.
     */
    public LoanHistory toModelType() throws IllegalValueException {
        if (!Loan.isValidLoan(loan)) {
            throw new IllegalValueException(Loan.MESSAGE_CONSTRAINTS);
        }
        if (!Reason.isValidReason(reason)) {
            throw new IllegalValueException(Reason.MESSAGE_CONSTRAINTS);
        }

        return new LoanHistory(new Loan(loan), new Reason(reason));
    }
}
