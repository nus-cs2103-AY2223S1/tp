package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

/**
 * Represents a nurse that home-visit patients in Healthcare Xpress book.
 */

public class Nurse extends Person {

    private static final String MESSAGE_FOR_EMPTY_HOMEVISITLIST = "No home visit assigned yet.";
    private static final String MESSAGE_FOR_EMPTY_UNAVAILABLEDATE = "No unavailable date.";
    private final List<HomeVisit> homeVisitList = new ArrayList<>();
    private final List<Date> unavailableDateList = new ArrayList<>();
    private final List<Date> fullyScheduledDateList = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public Nurse(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags,
            List<Date> unavailableDates, List<HomeVisit> homeVisits, List<Date> fullyScheduledDates) {
        super(uid, name, gender, phone, email, address, tags);
        this.unavailableDateList.addAll(unavailableDates);
        this.homeVisitList.addAll(homeVisits);
        this.fullyScheduledDateList.addAll(fullyScheduledDates);
    }

    public Category getCategory() {
        return new Category("N");
    }

    public String getCategoryIndicator() {
        return PersonType.NURSE.toString();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return super.hashCode() + Objects.hash(unavailableDateList) + Objects.hash(homeVisitList)
                + Objects.hash(fullyScheduledDateList);
    }

    /**
     * Returns a sorted home visit list
     */
    public List<HomeVisit> getHomeVisits() {
        HomeVisitComparator comp = new HomeVisitComparator();
        this.homeVisitList.sort(comp);
        return this.homeVisitList;
    }

    /**
     * Returns a sorted unavailable dates
     */
    public List<Date> getUnavailableDates() {
        DateComparator comp = new DateComparator();
        this.unavailableDateList.sort(comp);
        return this.unavailableDateList;
    }

    /**
     * Returns a sorted fully scheduled dates
     */
    public List<Date> getFullyScheduledDates() {
        DateComparator comp = new DateComparator();
        this.fullyScheduledDateList.sort(comp);
        return this.fullyScheduledDateList;
    }

    public String getHomesVisitsInString() {
        StringBuilder dateSlotListSB = new StringBuilder();

        if (this.homeVisitList.isEmpty()) {
            dateSlotListSB = new StringBuilder(MESSAGE_FOR_EMPTY_HOMEVISITLIST);
            return dateSlotListSB.toString();
        }

        for (HomeVisit homeVisit : getHomeVisits()) {
            dateSlotListSB.append(homeVisit.toString()).append(" , ");
        }

        return dateSlotListSB.toString();
    }

    public String getUnavailableDatesInString() {
        StringBuilder dateListSB = new StringBuilder();

        if (this.unavailableDateList.isEmpty()) {
            dateListSB = new StringBuilder(MESSAGE_FOR_EMPTY_UNAVAILABLEDATE);
            return dateListSB.toString();
        }

        for (Date date : getUnavailableDates()) {
            dateListSB.append(date.toString()).append(" , ");
        }

        return dateListSB.toString();
    }

    @Override
    public String toString() {
        String homeVisitList = getHomesVisitsInString();
        String unavailableDateList = getUnavailableDatesInString();
        return "Category: " + getCategory() + " " + super.toString() + "; Unavailable Dates:" + unavailableDateList
                + "; Home Visits:" + homeVisitList;
    }

    public boolean isNurse() {
        return true;
    }
}
