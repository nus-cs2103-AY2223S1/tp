package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a patient that requires home-visit in Healthcare Xpress book.
 */
public class Patient extends Person {

    private static final String MESSAGE_FOR_EMPTY_DATETIME = "Home Visit date and time has not been set yet.";
    private final List<DateTime> dateTimes = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Patient(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address,
            Set<Tag> tags, List<DateTime> dateTime) {
        super(uid, name, gender, phone, email, address, tags);
        requireAllNonNull(dateTime);
        this.dateTimes.addAll(dateTime);
    }

    public String getCategory() {
        return "P";
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
        Collections.sort(this.dateTimes, comp);
        return this.dateTimes;
    }

    public String getDatesTimesInString() {
        String dateTimeList = "";

        if (this.dateTimes.isEmpty()) {
            dateTimeList = MESSAGE_FOR_EMPTY_DATETIME;
            return dateTimeList;
        }

        DateTimeComparator comp = new DateTimeComparator();
        Collections.sort(this.dateTimes, comp);

        for (DateTime datetime : this.dateTimes) {
            dateTimeList = dateTimeList + datetime.toString() + " , ";
        }

        return dateTimeList;
    }

    @Override
    public String toString() {
        String dateTimeList = getDatesTimesInString();
        return "Category: P " + super.toString()
                + "; Home Visits Date and Time :" + dateTimeList;
    }

}
