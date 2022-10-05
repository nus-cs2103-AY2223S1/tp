package seedu.address.model.person.position;

/**
 * Represents the Teaching Assistant position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class TeachingAssistant extends Position {

    public TeachingAssistant() {
        super("TA");
    }

    @Override
    public String toString() {
        return "Teaching Assistant";
    }

    @Override
    public boolean equals(Object other) {
        return true;
    }

    @Override
    public int hashcode() {
        return 0;
    }
}
