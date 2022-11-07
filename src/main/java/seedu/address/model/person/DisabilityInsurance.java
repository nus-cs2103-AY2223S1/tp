package seedu.address.model.person;

/**
 * Represents a Person's Disability Insurance in the address book.
 * Guarantees: immutable; is always valid
 */
public class DisabilityInsurance extends Insurance {

    /**
     * Constructor for DisabilityInsurance object.
     *
     * @param hasInsurance
     */
    public DisabilityInsurance(boolean hasInsurance) {
        super(hasInsurance);
    }
}
