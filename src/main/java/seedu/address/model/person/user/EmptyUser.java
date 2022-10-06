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
    public String toString() {
        return "no user exists";
    }

}
