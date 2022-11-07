package seedu.address.model.person;

/**
 * Represents a Person's Insurance in the address book.
 * Guarantees: immutable; is always valid
 */
public abstract class Insurance {
    private final boolean hasInsurance;

    /**
     * Constructor for Insurance object.
     *
     * @param hasInsurance
     */
    public Insurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Insurance // instanceof handles nulls
                && hasInsurance == ((Insurance) other).hasInsurance); // state check
    }

    @Override
    public int hashCode() {
        return hasInsurance ? 1 : 0;
    }

    @Override
    public String toString() {
        return Boolean.toString(hasInsurance);
    }

    /**
     * Getter method to return hasInsurance field of Insurance instance
     *
     * @return hasInsurance
     */
    public boolean getHasInsurance() {
        return this.hasInsurance;
    }
}
