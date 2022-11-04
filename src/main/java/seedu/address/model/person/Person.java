package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.util.Pair;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements seedu.address.model.DeepCopyable {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Birthday birthday;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Loan loan;
    private final List<LoanHistory> history;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Birthday birthday, Set<Tag> tags,
                  Loan loan, List<LoanHistory> history) {
        requireAllNonNull(name, phone, email, address, tags, loan, birthday, history);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.tags.addAll(tags);
        this.loan = loan;
        this.history = history;
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

    public Birthday getBirthday() {
        return birthday;
    }
    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Loan getLoan() {
        return loan;
    }

    public List<LoanHistory> getHistory() {
        return history;
    }

    /**
     * Combines the {@code LoanHistory} with {@code Loan} to produce a tracked history of loans together
     * with the newly updated loan value at that point
     * @return an {@code ArrayList} contains a {@code Pair} with key of type {@code Loan} and
     *          value of type {@code LoanHistory}
     */
    public List<Pair<Loan, LoanHistory>> getHistoryWithTotal() {
        Loan previousAmount = loan;
        ArrayList<Pair<Loan, LoanHistory>> totalHistoryPair = new ArrayList<>();

        ListIterator<LoanHistory> historyIterator = history.listIterator(history.size());

        while (historyIterator.hasPrevious()) {
            LoanHistory loanHistory = historyIterator.previous();
            totalHistoryPair.add(new Pair<>(previousAmount, loanHistory));

            double nextPreviousLoan = previousAmount.getAmount() - loanHistory.getLoanChange().getAmount();
            nextPreviousLoan = Math.round(nextPreviousLoan * 100.0) / 100.0;
            previousAmount = new Loan(nextPreviousLoan);
        }

        return totalHistoryPair;
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getBirthday().equals(getBirthday())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLoan().equals(getLoan());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, tags, loan);
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
                .append(getAddress())
                .append("; Birthday: ")
                .append((getBirthday()))
                .append("; Owed amount: ")
                .append(getLoan());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Creates a new copy of this person object
     * All fields are deep copied apart from Tag due to cyclical dependency.
     * Tag clones contain shallow copies pointing to the Persons that the original
     * tag contained.
     * @return a new deeper-than-shallow copy of the Person's object
     */
    @Override
    public Person deepCopy() {
        Person clonedPerson = new Person(
                getName().deepCopy(),
                getPhone().deepCopy(),
                getEmail().deepCopy(),
                getAddress().deepCopy(),
                getBirthday().deepCopy(),
                getTags().stream().map(Tag::shallowCopy).collect(Collectors.toSet()),
                getLoan().deepCopy(),
                getHistory().stream().map(LoanHistory::shallowCopy).collect(Collectors.toList()));

        clonedPerson.getTags().forEach(tag -> {
            tag.removePerson(this);
            tag.addPerson(clonedPerson);
        });

        return clonedPerson;
    }

}
