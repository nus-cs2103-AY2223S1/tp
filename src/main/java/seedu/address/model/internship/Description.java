package seedu.address.model.internship;

/**
 * Represents an Internship's description in the address book.
 */
public class Description {

    private String description;

    public Description(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
