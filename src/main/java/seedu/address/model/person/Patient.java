package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

/**
 * Represents a patient that requires home-visit in Healthcare Xpress book.
 */
public class Patient extends Person {

    private static final String MESSAGE_FOR_EMPTY_DATESLOT = "Home Visit date and slot has not been set yet.";
    private final List<DateSlot> dateSlots = new ArrayList<>();
    private final VisitStatus visitStatus;

    /**
     * Every field must be present and not null.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
                   Set<Tag> tags, List<DateSlot> dateTimeSlot, VisitStatus visitStatus) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTimeSlot);
        this.dateSlots.addAll(dateTimeSlot);
        this.visitStatus = visitStatus;
    }

    public Category getCategory() {
        return new Category("P");
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
        StringBuilder dateSlotListSB = new StringBuilder();

        if (this.dateSlots.isEmpty()) {
            dateSlotListSB = new StringBuilder(MESSAGE_FOR_EMPTY_DATESLOT);
            return dateSlotListSB.toString();
        }

        for (DateSlot dateslot : getDatesSlots()) {
            dateSlotListSB.append(dateslot.toString()).append(" , ");
        }

        return dateSlotListSB.toString();
    }

    public VisitStatus getVisitStatus() {
        return this.visitStatus;
    }

    @Override
    public String toString() {
        String dateSlotList = getDatesSlotsInString();
        return "Category: P " + super.toString()
                + "; Home Visits Date and Time:" + dateSlotList
                + "; Visit Status: " + getVisitStatus();
    }
}
