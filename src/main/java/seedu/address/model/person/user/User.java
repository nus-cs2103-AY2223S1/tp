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
 * Represents the User of the address book. User can never be null.
 */
public abstract class User {

    public abstract Name getName();

    public abstract Phone getPhone();

    public abstract Email getEmail();

    public abstract Address getAddress();

    /**
     * Returns an immutable current module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public abstract Set<CurrentModule> getCurrModules();

    /**
     * Returns an immutable previous module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public abstract Set<PreviousModule> getPrevModules();

    /**
     * Returns an immutable planned module set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public abstract Set<PlannedModule> getPlanModules();

}
