package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

/**
 * Represents a patient that requires home-visit in Healthcare Xpress book.
 */
public class Patient extends Person {

    private static final String MESSAGE_FOR_EMPTY_DATESLOT = "Home Visit date and slot has not been set yet.";
    private static final String NO_NEXTOFKIN_SET = "No next of kin info was added for this patient.";
    private static final String NO_PHYSICIAN_SET = "There is currently no attending physician for this patient.";
    public final List<DateSlot> dateSlots = new ArrayList<>();
    private final Optional<Physician> attendingPhysician;
    private final Optional<NextOfKin> nextOfKin;

    /**
     * Initialise patient with no attending physician and no next of kin.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
            Set<Tag> tags, List<DateSlot> dateTimeSlot) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTimeSlot);
        this.dateSlots.addAll(dateTimeSlot);
        attendingPhysician = Optional.empty();
        nextOfKin = Optional.empty();

    }

    /**
     * Every field, except attending physician and next of kin, must be present and
     * not null.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
            Set<Tag> tags, List<DateSlot> dateTime, Physician p, NextOfKin n) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTime);
        this.dateSlots.addAll(dateTime);
        attendingPhysician = Optional.ofNullable(p);
        nextOfKin = Optional.ofNullable(n);
    }

    /**
     * Initialise patient with given attending physician and next of kin.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
            Set<Tag> tags, List<DateSlot> dateSlot,
            Optional<Physician> p, Optional<NextOfKin> n) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateSlot);
        this.dateSlots.addAll(dateSlot);
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
        String[] output = new String[] { NO_NEXTOFKIN_SET };
        nextOfKin.ifPresent(x -> output[0] = "NOK: " + x.toString());
        return output[0];
    }

    public String getPhysicianDetails() {
        String[] output = new String[] { NO_PHYSICIAN_SET };
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
        return super.hashCode() + Objects.hash(dateSlots);
    }

    /**
     * Returns a sorted date and slot list
     */
    public List<DateSlot> getDatesSlots() {
        DateSlotComparator comp = new DateSlotComparator();
        this.dateSlots.sort(comp);
        return this.dateSlots;
    }

    public String getDatesSlotsInString() {
        String dateSlotsString = getDatesSlots().stream().map(x -> x.toString()).collect(Collectors.joining(
                ", "));
        if (dateSlotsString.length() == 0) {
            return String.format("Home Visits Date and Time: %s;", MESSAGE_FOR_EMPTY_DATESLOT);
        }
        return String.format("Home Visits Date and Time: %s;", dateSlotsString);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getCategory().toFormattedString())
                .append(" ")
                .append(super.toString())
                .append(" ")
                .append(getDatesSlotsInString());

        return builder.toString();
    }

    public boolean isPatient() {
        return true;
    }
}
