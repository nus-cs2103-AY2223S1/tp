package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Nurse's home-visit.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidHomeVisit(String)}
 */
public class HomeVisit {

    public static final String MESSAGE_CONSTRAINTS = "Home Visit should be in YYYY-MM-DD,SLOT_NUMBER:"
            + "PATIENT_UID_NUMBER.\n" + "For example, 2022-11-11,1:2";

    /**
     * The DateSlot can only be in YYYY-MM-DD,SLOT_NUMBER:PATIENT NUMBER format
     * without any space.
     */
    // @@author xhphoong-reused
    // Reused from
    // https://mkyong.com/regular-expressions/how-to-validate-date-with-regular-expression/
    public static final String VALIDATION_REGEX = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"
            + "," + "([1-4])" + ":" + "\\d{1,18}";
    // @@author

    public final DateSlot homeVisitDateSlot;
    public final Long homeVisitPatientUidNo;

    /**
     * Constructs a {@code HomeVisit}.
     *
     * @param dateSlot A valid dateSlot, uid A valid uidNo.
     */
    public HomeVisit(DateSlot dateSlot, Long uid) {
        requireNonNull(dateSlot);
        requireNonNull(uid);
        this.homeVisitDateSlot = dateSlot;
        this.homeVisitPatientUidNo = uid;
    }

    /**
     * Constructs a {@code HomeVisit}.
     */
    public HomeVisit(String dateSlotAndUid) {
        requireNonNull(dateSlotAndUid);
        String[] s = dateSlotAndUid.split(":");
        this.homeVisitDateSlot = new DateSlot(s[0]);
        this.homeVisitPatientUidNo = Long.parseLong(s[1]);
    }

    /**
     * Returns true if a given string is a valid home visit.
     */
    public static boolean isValidHomeVisit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getString() {
        return this.homeVisitDateSlot.getDateSlotInString() + ":" + this.homeVisitPatientUidNo;
    }

    @Override
    public String toString() {
        return homeVisitDateSlot.getDateSlotFormatted() + " : [UID] " + this.homeVisitPatientUidNo;
    }

    public DateSlot getDateSlot() {
        return this.homeVisitDateSlot;
    }

    public Long getHomeVisitPatientUidNo() {
        return this.homeVisitPatientUidNo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof HomeVisit // instanceof handles nulls
                        && homeVisitDateSlot.equals(((HomeVisit) other).homeVisitDateSlot)
                        && homeVisitPatientUidNo.equals(((HomeVisit) other).homeVisitPatientUidNo)); // state check
    }

    public HomeVisit clone() {
        DateSlot dateSlot = this.getDateSlot();
        Long patientUidNo = this.getHomeVisitPatientUidNo();
        return new HomeVisit(dateSlot, patientUidNo);
    }

    @Override
    public int hashCode() {
        return homeVisitDateSlot.hashCode() + homeVisitPatientUidNo.hashCode();
    }

}
