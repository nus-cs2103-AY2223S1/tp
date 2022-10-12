package seedu.address.model.person.user;

import java.util.Set;

import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

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

    @Override
    public Name getName() {
        return null;
    }

    @Override
    public Phone getPhone() {
        return null;
    }

    @Override
    public Email getEmail() {
        return null;
    }

    @Override
    public Address getAddress() {
        return null;
    }

    /**
     * Returns an immutable current module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<CurrentModule> getCurrModules() {
        return null;
    }

    /**
     * Returns an immutable previous module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<PreviousModule> getPrevModules() {
        return null;
    }

    /**
     * Returns an immutable planned module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<PlannedModule> getPlanModules() {
        return null;
    }
}
