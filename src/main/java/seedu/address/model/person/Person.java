package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.MeetingDate;
import seedu.address.model.meeting.MeetingLocation;
import seedu.address.model.portfolio.Note;
import seedu.address.model.portfolio.Plan;
import seedu.address.model.portfolio.Portfolio;
import seedu.address.model.portfolio.Risk;
import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the FinBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Income income;
    private final Meeting meeting;
    private final Set<Tag> tags = new HashSet<>();
    private final Portfolio portfolio;

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Phone phone, Email email, Address address, Income income, MeetingDate meetingDate,
                  MeetingLocation meetingLocation, Set<Tag> tags, Risk risk, Set<Plan> plan, Set<Note> note) {
        requireAllNonNull(name, phone, email, address, income, tags, plan, note);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.income = income;
        this.meeting = new Meeting(meetingDate, meetingLocation);
        this.tags.addAll(tags);
        this.portfolio = new Portfolio(risk, plan, note);
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

    public Income getIncome() {
        return income;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
            && otherPerson.getIncome().equals(getIncome())
            && otherPerson.getMeeting().equals(getMeeting())
            && otherPerson.getTags().equals(getTags())
            && otherPerson.getPortfolio().equals(getPortfolio());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, income, meeting, tags, portfolio);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        Meeting meeting = getMeeting();
        Portfolio portfolio = getPortfolio();
        builder.append(getName())
            .append("; Phone: ")
            .append(getPhone())
            .append("; Email: ")
            .append(getEmail())
            .append("; Address: ")
            .append(getAddress())
            .append("; Income: ")
            .append(getIncome())
            .append("; Meeting Date: ")
            .append(meeting.getMeetingDate())
            .append("; Meeting Location: ")
            .append(meeting.getMeetingLocation());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        builder.append("; Risk: ")
            .append(portfolio.getRisk());

        Set<Plan> plans = portfolio.getPlans();
        if (!plans.isEmpty()) {
            builder.append("; Plans: ");
            plans.forEach(builder::append);
        }

        Set<Note> notes = portfolio.getNotes();
        if (!notes.isEmpty()) {
            builder.append("; Note: ");
            notes.forEach(builder::append);
        }

        return builder.toString();
    }


    /**
     * Returns client details that is formatted to an organized and readable string
     *
     * @return Readable string of client details.
     */
    public String toClipboardString() {
        final StringBuilder builder = new StringBuilder();
        Portfolio portfolio = getPortfolio();
        builder.append("Name: ")
            .append(getName())
            .append("\nPhone: ")
            .append(getPhone())
            .append("\nEmail: ")
            .append(getEmail())
            .append("\nAddress: ")
            .append(getAddress())
            .append("\nIncome: ")
            .append(getIncome())
            .append("\nMeeting date: ")
            .append(meeting.getMeetingDate())
            .append("\nMeeting Location: ")
            .append(meeting.getMeetingLocation());


        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            String outputTag = tags.toString().replace("[", "");
            outputTag = outputTag.replace("]", "");
            builder.append(outputTag);
        }

        builder.append("\nRisk: ")
            .append(portfolio.getRisk());

        Set<Plan> plans = portfolio.getPlans();
        if (!plans.isEmpty()) {
            builder.append("\nPlans: ");
            String outputPlans = plans.toString().replace("[", "");
            outputPlans = outputPlans.replace("]", "");
            builder.append(outputPlans);
        }

        Set<Note> notes = portfolio.getNotes();
        if (!notes.isEmpty()) {
            builder.append("\nNote: ");
            String outputNotes = notes.toString().replace("[", "");
            outputNotes = outputNotes.replace("]", "");
            builder.append(outputNotes);
        }

        return builder.toString();
    }

}
