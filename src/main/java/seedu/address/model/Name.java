package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the name of the client. This class is modelled after the Name class in the Person package of AB3
 */
public class Name {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should not be blank and can contain only contain alphanumeric characters and spaces.";


    /**
     * The name can contain only letters and spaces.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private String fullName;

    /**
     * Constructs a Name.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Represents an Empty Name.
     */
    public static class EmptyName extends Name {
        public static final Name EMPTY_NAME = new EmptyName();
        public EmptyName() {
            super("empty");
        }

        /**
         * Checks if this Client Name is empty.
         * @return true if the Client Name is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "";
        }
    }


    /**
     * Returns true if a given string is a valid name. A name is valid only if it contains only letters and spaces and
     * had a maximum of four words, each of length less than 10 characters.
     * @param test String representing name to be tested
     * @return boolean true if a given string is a valid name
     */
    public static boolean isValidName(String test) {
        return !test.isEmpty() && test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if this Client Name is empty.
     * @return true if the Client Name is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the full name of the client.
     * @return String representing full name
     */
    public String getFullNameRepresentation() {
        return this.fullName;
    }

    /**
     * Returns the UI string representation of the name.
     */
    public String uiRepresentation(boolean isPinned, String id) {
        return this.fullName + " " + id
                + (isPinned ? " \uD83D\uDCCC" : "");
    }

    /**
     * Returns the String representation of the Client Name.
     * @return String representing the Client Name
     */
    @Override
    public String toString() {
        return this.fullName;
    }

    /**
     * Checks if an object equals this.
     * @param other Object to be checked
     * @return boolean true if this is equal to other and false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;

        } else if (other instanceof Name) {
            Name otherName = (Name) other;
            return this.fullName.equals(otherName.fullName);

        } else {
            return false;
        }
    }

}
