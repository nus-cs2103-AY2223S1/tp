package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a default Person in the address book.
 * Used to represent a placeholder in model, that is interchanged with an actual Person object when ListRecordCommand
 * is executed.
 */
public class PlaceholderPerson extends Person{

    // Default Field Statements
    private static final String DEFAULT_NAME = "Amy Bee";
    private static final String DEFAULT_PHONE = "85355255";
    private static final String DEFAULT_EMAIL = "amy@gmail.com";
    private static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    /**
     * Every field must be present and not null.
     */
    public PlaceholderPerson(Name name, Phone phone, Email email, Address address, Set<Tag> tags, RecordList records) {
        super(new Name(DEFAULT_NAME),
                new Phone(DEFAULT_PHONE),
                new Email(DEFAULT_EMAIL),
                new Address(DEFAULT_ADDRESS),
                new HashSet<Tag>(),
                new RecordList());
    }

    @Override
    public Name getName() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Phone getPhone() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Email getEmail() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Address getAddress() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public RecordList getRecordList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Set<Tag> getTags() {
        throw new AssertionError("This method should not be called.");
    }

    /**
     * Returns true if this is the same instance of PlaceholderPerson
     */
    public boolean isSamePerson(PlaceholderPerson otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return false;
    }

    //======= Record List ========================================

    @Override
    public boolean hasRecord(Record record) { throw new AssertionError("This method should not be called."); }

    @Override
    public void addRecord(Record r) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRecord(Record record) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRecords(RecordList records) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void clearRecords() {
        throw new AssertionError("This method should not be called.");
    }

    //======================================================================================

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PlaceholderPerson)) {
            return false;
        }

        PlaceholderPerson otherPerson = (PlaceholderPerson) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
