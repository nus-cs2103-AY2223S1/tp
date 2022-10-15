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

    private static final String MESSAGE_FOR_EMPTY_DATETIME = "Home Visit date and time has not been set yet.";
    private final List<DateTime> dateTimes = new ArrayList<>();
    private final VisitStatus visitStatus;

    /**
     * Every field must be present and not null.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
                   Set<Tag> tags, List<DateTime> dateTime, VisitStatus visitStatus) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTime);
        this.dateTimes.addAll(dateTime);
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
                + "; Home Visits Date and Time :" + dateTimeList;
    }
}
