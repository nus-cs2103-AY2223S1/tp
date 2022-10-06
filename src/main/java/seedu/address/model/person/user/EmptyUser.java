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

    // Identity fields
    private final Name name = new Name("blank");
    private final Phone phone = new Phone("000");
    private final Email email = new Email("x@y.co");

    // Data fields
    private final Address address = new Address("blank");

    /**
     * Every field must be present and not null.
     */
    public EmptyUser() {
        super();
        requireAllNonNull(name, phone, email, address);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address);
    }

    @Override
    public String toString() {
        return "no user exists";
    }

}
