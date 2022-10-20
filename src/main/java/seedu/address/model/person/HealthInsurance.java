package seedu.address.model.person;

/**
 * Represents a Person's Health Insurance in the address book.
 * Guarantees: immutable; is always valid
 */
public class HealthInsurance extends Insurance {

    /**
     * Constructor for HealthInsurance object.
     *
     * @param hasInsurance
     */
    public HealthInsurance(boolean hasInsurance) {
        super(hasInsurance);
    }
}
