package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

/**
 * Represents a patient that requires home-visit in Healthcare Xpress book.
 */
public class Patient extends Person {

    private static final String MESSAGE_FOR_EMPTY_DATETIME = "Home Visit date and time has not been set yet.";
    private static final String NO_NEXTOFKIN_SET = "No next of kin info was added for this patient.";
    private static final String NO_PHYSICIAN_SET = "There is currently no attending physician for this patient.";
    private final List<DateTime> dateTimes = new ArrayList<>();
    private final VisitStatus visitStatus;
    private final Optional<Physician> attendingPhysician;
    private final Optional<NextOfKin> nextOfKin;

    /**
     * Initialise patient with no attending physician and no next of kin.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
                   Set<Tag> tags, List<DateTime> dateTime, VisitStatus visitStatus) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTime);
        this.dateTimes.addAll(dateTime);
        this.visitStatus = visitStatus;
        attendingPhysician = Optional.empty();
        nextOfKin = Optional.empty();
    }

    /**
     * Every field, except attending physician and next of kin, must be present and not null.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
                   Set<Tag> tags, List<DateTime> dateTime, VisitStatus visitStatus, Physician p, NextOfKin n) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTime);
        this.dateTimes.addAll(dateTime);
        this.visitStatus = visitStatus;
        attendingPhysician = Optional.ofNullable(p);
        nextOfKin = Optional.ofNullable(n);
    }

    /**
     * Initialise patient with given attending physician and next of kin.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
                   Set<Tag> tags, List<DateTime> dateTime, VisitStatus visitStatus,
                   Optional<Physician> p, Optional<NextOfKin> n) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTime);
        this.dateTimes.addAll(dateTime);
        this.visitStatus = visitStatus;
        attendingPhysician = p;
        nextOfKin = n;
    }

    public Optional<Physician> getAttendingPhysician() {
        return attendingPhysician;
    }

    public Optional<NextOfKin> getNextOfKin() {
        return nextOfKin;
    }

    public String getNextOfKinDetails() {
        String[] output = new String[]{NO_NEXTOFKIN_SET};
        nextOfKin.ifPresent(x -> output[0] = x.toString());
        return output[0];
    }

    public String getPhysicianDetails() {
        String[] output = new String[]{NO_PHYSICIAN_SET};
        attendingPhysician.ifPresent(x -> output[0] = x.toString());
        return output[0];
    }

    public Category getCategory() {
        return new Category(Category.PATIENT_SYMBOL);
    }

    public String getCategoryIndicator() {
        return PersonType.PATIENT.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode() + Objects.hash(dateTimes);
    }

    /**
     * Returns a sorted date and time list
     */
    public List<DateTime> getDatesTimes() {
        DateTimeComparator comp = new DateTimeComparator();
        this.dateTimes.sort(comp);
        return this.dateTimes;
    }

    public String getDatesTimesInString() {
        StringBuilder dateTimeListSB = new StringBuilder();

        if (this.dateTimes.isEmpty()) {
            dateTimeListSB = new StringBuilder(MESSAGE_FOR_EMPTY_DATETIME);
            return dateTimeListSB.toString();
        }

        DateTimeComparator comp = new DateTimeComparator();
        this.dateTimes.sort(comp);

        for (DateTime datetime : this.dateTimes) {
            dateTimeListSB.append(datetime.toString()).append(" , ");
        }

        return dateTimeListSB.toString();
    }

    public VisitStatus getVisitStatus() {
        return this.visitStatus;
    }

    @Override
    public String toString() {
        String dateTimeList = getDatesTimesInString();
        return "Category: P " + super.toString()
                + "; Home Visits Date and Time:" + dateTimeList
                + "; Visit Status: " + getVisitStatus();
    }
}
