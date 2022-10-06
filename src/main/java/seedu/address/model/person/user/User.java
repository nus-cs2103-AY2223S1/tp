package seedu.address.model.person.user;

import seedu.address.model.module.CurrentModule;
import seedu.address.model.module.PlannedModule;
import seedu.address.model.module.PreviousModule;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the User of the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class User {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<CurrentModule> currModules = new HashSet<>();
    private final Set<PreviousModule> prevModules = new HashSet<>();
    private final Set<PlannedModule> planModules = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public User(Name name, Phone phone, Email email, Address address, Set<CurrentModule> currModules,
                Set<PreviousModule> prevModules, Set<PlannedModule> planModules) {
        requireAllNonNull(name, phone, email, address);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.currModules.addAll(currModules);
        this.prevModules.addAll(prevModules);
        this.planModules.addAll(planModules);
    }

    public User() {
        this.name = new Name("blank");
        this.phone = new Phone("000");
        this.email = new Email("x@y.co");
        this.address = new Address("blank");
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable current module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<CurrentModule> getCurrModules() {
        return Collections.unmodifiableSet(currModules);
    }

    /**
     * Returns an immutable previous module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<PreviousModule> getPrevModules() {
        return Collections.unmodifiableSet(prevModules);
    }

    /**
     * Returns an immutable planned module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<PlannedModule> getPlanModules() {
        return Collections.unmodifiableSet(planModules);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        return builder.toString();
    }

}
