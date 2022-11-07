package seedu.address.model.person;

/**
 * Represents a Person's Life Insurance in the address book.
 * Guarantees: immutable; is always valid
 */
public class LifeInsurance extends Insurance {

    /**
     * Constructor for LifeInsurance object.
     *
     * @param hasInsurance
     */
    public LifeInsurance(boolean hasInsurance) {
        super(hasInsurance);
    }
}
