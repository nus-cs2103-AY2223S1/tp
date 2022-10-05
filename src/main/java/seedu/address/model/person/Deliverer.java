package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

public class Deliverer extends Person {


    /**
     * Every field must be present and not null.
     *
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Deliverer(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
    }
}
