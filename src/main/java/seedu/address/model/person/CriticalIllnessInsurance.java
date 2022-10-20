package seedu.address.model.person;

/**
 * Represents a Person's Critical Illness Insurance in the address book.
 * Guarantees: immutable; is always valid
 */
public class CriticalIllnessInsurance extends Insurance {

    /**
     * Constructor for CriticalIllnessInsurance object.
     *
     * @param hasInsurance
     */
    public CriticalIllnessInsurance(boolean hasInsurance) {
        super(hasInsurance);
    }
}
