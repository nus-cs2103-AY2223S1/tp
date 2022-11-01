package seedu.address.model.pet;

/**
 * Represents the species (kind) of a pet.
 */
public class Species implements Comparable<Species> {
    public static final String MESSAGE_CONSTRAINTS =
            "Species should only contain alphanumeric characters and spaces, and it should not be blank";
    private final String value;

    /**
     * Constructs the Species object.
     *
     * @param value The string representation of the species.
     */
    public Species(String value) {
        if (value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Species // instanceof handles nulls
                && value.equals(((Species) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Species: " + value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(Species o) {
        return this.value.compareTo(o.value);
    }
}
