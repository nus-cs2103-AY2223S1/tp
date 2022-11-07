package paymelah.model.person;

import static paymelah.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.model.debt.DebtList;
import paymelah.model.debt.Money;
import paymelah.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Telegram telegram;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final DebtList debts;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Telegram telegram, Address address, Set<Tag> tags, DebtList debts) {
        requireAllNonNull(name, phone, telegram, address, tags, debts);
        this.name = name;
        this.phone = phone;
        this.telegram = telegram;
        this.address = address;
        this.tags.addAll(tags);
        this.debts = debts;
    }

    /**
     * Constructs a new {@code Person} based off a given {@code PersonDescriptor}.
     * @param descriptor the {@code PersonDescriptor} to base off of
     */
    public Person(PersonDescriptor descriptor) {
        assert descriptor.getName().isPresent();
        this.name = descriptor.getName().get();
        this.phone = descriptor.getPhone().orElse(Phone.EMPTY_PHONE);
        this.telegram = descriptor.getTelegram().orElse(Telegram.EMPTY_TELEGRAM);
        this.address = descriptor.getAddress().orElse(Address.EMPTY_ADDRESS);
        descriptor.getTags().ifPresent(this.tags::addAll);
        this.debts = new DebtList();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public DebtList getDebts() {
        return debts;
    }

    public Money getDebtsAmountAsMoney() {
        return new Money(debts.getTotalDebt().toString());
    }

    public int compareNameWith(Person o) {
        return name.compareTo(o.name);
    }

    public int compareAmountOwedWith(Person o) {
        return getDebtsAmountAsMoney().compareTo(o.getDebtsAmountAsMoney());
    }

    public int compareEarliestDebtDateTimeWith(Person o) {
        return debts.compareEarliestDebtDateTimeWith(o.debts);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getTelegram().equals(getTelegram())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getDebts().equals(getDebts());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, telegram, address, tags, debts);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Telegram: ")
                .append(getTelegram())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("\nDebts:");
        builder.append(debts);

        return builder.toString();
    }

}
