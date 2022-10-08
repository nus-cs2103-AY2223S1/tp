package seedu.address.model.client;

import seedu.address.model.person.Phone;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ClientPhone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should be 8-10 digits";
    public static final String VALIDATION_REGEX = "\\d{8,10}";

    public String phone;

    /**
     * Constructs a Client Phone.
     *
     * @param phone A valid phone number.
     */
    public ClientPhone(String phone) {
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        this.phone = phone;
    }

    /**
     * Represents an Empty Client Phone.
     */
    private static class EmptyClientPhone extends ClientPhone {
        private static final ClientPhone EMPTY_PHONE = new EmptyClientPhone();
        private EmptyClientPhone() {
            super("");
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
    public static boolean isValidPhone(String test) {
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
    public String getPhone() {
        return this.phone;
    }

    /**
     * Returns the String representation of the Client Phone.
     * @return String representing the Client Phone
     */
    @Override
    public String toString() {
        return "Client Phone: " + this.phone;
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
        } else if (other instanceof ClientPhone){
            ClientPhone otherPhone = (ClientPhone) other;
            return this.phone.equals(otherPhone.phone);
        } else {
            return false;
        }
    }
}
