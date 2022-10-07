package seedu.address.model.person.user;

/**
 * Represents an Empty User field, which is present in the address book when ConnectNUS is first opened or when the
 * User has been deleted.
 */
public class EmptyUser extends User {

    /**
     * EmptyUser does not contain any fields as it is empty
     */
    public EmptyUser() {
        super();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (other instanceof EmptyUser) {
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "no user exists";
    }

}
