package seedu.address.model.person.user;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import java.util.Objects;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an Empty User field, which is present in the address book when ConnectNUS is first opened or when the
 * User has been deleted.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class EmptyUser extends User {

    /**
     * Every field must be present and not null.
     */
    public EmptyUser() {
        super();
    }

    @Override
    public String toString() {
        return "no user exists";
    }

}
