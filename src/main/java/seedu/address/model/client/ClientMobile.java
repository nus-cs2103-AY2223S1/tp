package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the mobile of the client. This class is modelled after the Phone class in the Person package of AB3
 */
public class ClientMobile {

    public static final String MESSAGE_CONSTRAINTS =
            "Mobile numbers should only contain numbers, and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";

    private String mobile;

    /**
     * Constructs a Client Mobile.
     *
     * @param mobile A valid mobile number.
     */
    public ClientMobile(String mobile) {
        requireNonNull(mobile);
        checkArgument(isValidClientMobile(mobile), MESSAGE_CONSTRAINTS);
        this.mobile = mobile;
    }

    /**
     * Represents an Empty Client Mobile.
     */
    public static class EmptyClientMobile extends ClientMobile {
        public static final ClientMobile EMPTY_MOBILE = new EmptyClientMobile();
        public EmptyClientMobile() {
            super("90000000");
        }

        /**
         * Checks if this Client Email is empty.
         * @return true if the Client Email is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String uiRepresentation() {
            return "No contact number set";
        }

        @Override
        public String toString() {
            return "";
        }
    }

    /**
     * Returns true if a given string is a valid mobile number.
     */
    public static boolean isValidClientMobile(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Checks if this Client Email is empty.
     * @return true if the Client Email is empty.
     */
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the mobile of the client.
     * @return String representing mobile
     */
    public String uiRepresentation() {
        return "Contact Number: " + this.mobile;
    }

    /**
     * Returns the String representation of the Client Mobile.
     * @return String representing the Client Mobile
     */
    @Override
    public String toString() {
        return this.mobile;
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
        } else if (other instanceof ClientMobile) {
            ClientMobile otherMobile = (ClientMobile) other;
            return this.mobile.equals(otherMobile.mobile);
        } else {
            return false;
        }
    }
}
