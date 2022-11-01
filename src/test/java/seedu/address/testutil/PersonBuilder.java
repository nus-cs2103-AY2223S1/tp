package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attribute.Address;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Name;
import seedu.address.model.attribute.Phone;
import seedu.address.model.item.AbstractSingleItem;
import seedu.address.model.item.DisplayItem;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder extends AbstractDisplayItemBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";

    private final Set<DisplayItem> parents;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        super(new Name(DEFAULT_NAME), new ArrayList<>(), new HashSet<>());
        parents = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        super(personToCopy.getName(), new ArrayList<>(personToCopy.getAttributes()),
                new HashSet<>(personToCopy.getTags()));
        parents = new HashSet<>(personToCopy.getParents());
    }

    @Override
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    @Override
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Adds address attribute to person.
     *
     * @param string address
     * @return a {@code PersonBuilder} with the additional address attribute.
     */
    public PersonBuilder withAddress(String string) {
        withAttribute(new Address(string));
        return this;
    }

    /**
     * Adds email attribute to person.
     *
     * @param string email
     * @return a {@code PersonBuilder} with the additional email attribute.
     */
    public PersonBuilder withEmail(String string) {
        withAttribute(new Email(string));
        return this;
    }

    /**
     * Adds phone attribute to person.
     *
     * @param string phone number.
     * @return a {@code PersonBuilder} with the additional phone attribute.
     */
    public PersonBuilder withPhone(String string) {
        withAttribute(new Phone(string));
        return this;
    }

    public PersonBuilder withParent(AbstractSingleItem item) {
        this.parents.add(item);
        return this;
    }

    @Override
    public Person build() {
        Person p = new Person(name.fullName);
        p.setTags(tags);

        for (Attribute<?> attr : attributes) {
            p.addAttribute(attr);
        }

        for (DisplayItem parent : parents) {
            p.setParent(parent);
        }

        return p;
    }
}
