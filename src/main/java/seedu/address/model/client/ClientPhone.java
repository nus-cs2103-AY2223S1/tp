package seedu.address.model.client;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the phone of the client. This class is modelled after the Phone class in the Person package of AB3
 */
public class ClientPhone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be 8-10 digits";
    public static final String VALIDATION_REGEX = "\\d{3,}";

    private String phone;

    /**
     * Constructs a Client Phone.
     *
     * @param phone A valid phone number.
     */
    public ClientPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidClientPhone(phone), MESSAGE_CONSTRAINTS);
        this.phone = phone;
    }

    /**
     * Represents an Empty Client Phone.
     */
    public static class EmptyClientPhone extends ClientPhone {
        public static final ClientPhone EMPTY_PHONE = new EmptyClientPhone();
        public EmptyClientPhone() {
            super("000");
        }

        /**
         * Checks if this Client Email is empty.
         * @return true if the Client Email is empty.
         */
        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidClientPhone(String test) {
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
     * Returns the phone of the client.
     * @return String representing phone
     */
    public String getPhoneRepresentation() {
        return "Client Phone: " + this.phone;
    }

    /**
     * Returns the String representation of the Client Phone.
     * @return String representing the Client Phone
     */
    @Override
    public String toString() {
        return this.phone;
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
        } else if (other instanceof ClientPhone) {
            ClientPhone otherPhone = (ClientPhone) other;
            return this.phone.equals(otherPhone.phone);
        } else {
            return false;
        }
    }
}
