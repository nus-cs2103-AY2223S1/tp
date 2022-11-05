package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Empty field value
    public static final String EMPTY_FIELD_VALUE = "";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Description description;
    private NetWorth netWorth;
    private final Set<MeetingTime> meetingTimes = new HashSet<>();
    private final FilePath filePath;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Description description,
                  NetWorth netWorth, Set<MeetingTime> meetingTimes, FilePath filePath, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, netWorth, meetingTimes, filePath, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
        this.netWorth = netWorth;
        this.meetingTimes.addAll(meetingTimes);
        this.filePath = filePath;
        this.tags.addAll(tags);
    }

    /**
     * Constructor with no filepath, for CreateCommand only.
     */
    public Person(Name name, Phone phone, Email email, Address address, Description description,
                  NetWorth netWorth, Set<MeetingTime> meetingTimes, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, description, netWorth, meetingTimes, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.description = description;
        this.netWorth = netWorth;
        this.meetingTimes.addAll(meetingTimes);
        this.filePath = new FilePath(Person.EMPTY_FIELD_VALUE);
        this.tags.addAll(tags);
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

    public Description getDescription() {
        return description;
    }

    public NetWorth getNetWorth() {
        return netWorth;
    }

    public Set<MeetingTime> getMeetingTimes() {
        return Collections.unmodifiableSet(meetingTimes);
    }

    public FilePath getFilePath() {
        return filePath;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone());
    }

    /**
     * Returns true if file path of person has been initialized
     */
    public boolean hasFilePath() {
        return !filePath.isEmpty();
    }

    /**
     * Deletes meetings with times that are before the local time on machine.
     */
    public static void syncMeetingTimes(Person toSync) {
        Predicate<MeetingTime> predicate = new MeetingTimePastPredicate();
        toSync.meetingTimes.removeIf(predicate);
    }

    /**
     * Returns the earliest meeting time from the person's meeting times.
     * @return The earliest Meeting Time object
     */
    public MeetingTime getEarliestMeeting() {
        Set<MeetingTime> copyMeetingTimes = new HashSet<>();
        copyMeetingTimes.addAll(meetingTimes);
        MeetingTime earliestMeeting = removeFirst(copyMeetingTimes);
        for (int i = 0; copyMeetingTimes.size() > 0; i++) {
            MeetingTime temp = removeFirst(copyMeetingTimes);
            if (temp.getDate().isBefore(earliestMeeting.getDate())) {
                earliestMeeting = temp;
            }
        }
        return earliestMeeting;
    }

    /**
     * Removes and returns the first item from the collection of type T.
     * @param c Collection of items to remove the first item from.
     * @param <T> Generic type of the collection.
     * @return The first item from the collection of type T.
     */
    // @author ReaganTan00-reused
    // Reused from https://stackoverflow.com/a/5793039
    @Nullable
    public static <T> T removeFirst(Collection<? extends T> c) {
        Iterator<? extends T> it = c.iterator();
        if (!it.hasNext()) {
            return null;
        }
        T removed = it.next();
        it.remove();
        return removed;
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getDescription().equals(getDescription())
                && otherPerson.getNetWorth().equals(getNetWorth())
                && otherPerson.getMeetingTimes().equals(getMeetingTimes())
                && otherPerson.getFilePath().equals(getFilePath());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, description, netWorth, meetingTimes, filePath);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Address: ")
                .append(getAddress())
                .append(" Description: ")
                .append(getDescription())
                .append(" Net Worth: ")
                .append(getNetWorth())
                .append(" Meeting time: ");
        getMeetingTimes().forEach(builder::append);
        builder.append(" File Path: ")
                .append(getFilePath())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
