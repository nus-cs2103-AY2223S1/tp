package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.category.Category;
import seedu.address.model.tag.Tag;

/**
 * Represents a nurse that home-visit patients in Healthcare Xpress book.
 */

public class Nurse extends Person {

    private static final String MESSAGE_FOR_EMPTY_HOME_VISIT_LIST = "No home visit assigned yet.";
    private static final String MESSAGE_FOR_EMPTY_UNAVAILABLE_DATE = "No unavailable date.";
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

    /**
     * Nurse Constructor used when first creating the nurse object
     *
     * @param uid              The nurse's uid
     * @param name             The nurse's name
     * @param gender           The nurse's gender
     * @param phone            The nurse's phone number
     * @param email            The nurse's email
     * @param address          The nurse's address
     * @param tags             The nurse's tags
     * @param unavailableDates The nurse's unavailable dates
     */
    public Nurse(Uid uid, Name name, Gender gender, Phone phone, Email email, Address address, Set<Tag> tags,
            List<Date> unavailableDates) {
        super(uid, name, gender, phone, email, address, tags);
        this.unavailableDateList.addAll(unavailableDates);
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
        Collections.sort(homeVisitList);
        return homeVisitList;
    }

    /**
     * Returns a sorted unavailable dates
     */
    public List<Date> getUnavailableDates() {
        Collections.sort(unavailableDateList);
        return unavailableDateList;
    }

    /**
     * Returns a sorted fully scheduled dates
     */
    public List<Date> getFullyScheduledDates() {
        Collections.sort(fullyScheduledDateList);
        return fullyScheduledDateList;
    }

    public String getHomesVisitsInString() {
        String homeVisitsString = homeVisitList.stream()
                .map(x -> x.toString()).collect(Collectors.joining(","));
        if (homeVisitsString.length() == 0) {
            return MESSAGE_FOR_EMPTY_HOME_VISIT_LIST;
        }
        return String.format("Home Visits: %s;", homeVisitsString);
    }

    public String getUnavailableDatesInString() {
        String unavailableString = unavailableDateList.stream()
                .map(x -> x.toString()).collect(Collectors.joining(","));
        if (unavailableString.length() == 0) {
            return MESSAGE_FOR_EMPTY_UNAVAILABLE_DATE;
        }
        return String.format("Unavailable Dates: %s;", unavailableString);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s %s %s",
                getCategory().toFormattedString(),
                super.toString(),
                getUnavailableDatesInString(),
                getHomesVisitsInString());
    }

    public boolean isNurse() {
        return true;
    }

    public String toLiteString() {
        return String.format("Nurse [Uid:%s]", getUid());
    }

}
