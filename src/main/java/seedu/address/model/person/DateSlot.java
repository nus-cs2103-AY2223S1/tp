package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Patient's home-visit's date and time slot.
 * Guarantees: immutable; is valid as declared in
 * {@link #isValidDateSlot(String)}
 */
public class DateSlot {

    public static final String MESSAGE_CONSTRAINTS = "Date and slot should be in YYYY-MM-DD,SLOT_NUMBER.\n"
            + "The slot number can only be from 1 to 4. Slot 1 is 10am, slot 2 is 12pm, "
            + "slot 3 is 2pm and slot 4 is 4pm.\n" + "For example, 2022-11-11,1";

    /**
     * The DateSlot can only be in YYYY-MM-DD,SLOT_NUMBER format without any space.
     */
    // @@author xhphoong-reused
    // Reused from
    // https://mkyong.com/regular-expressions/how-to-validate-date-with-regular-expression/
    public static final String VALIDATION_REGEX = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"
            + "," + "([1-4])";
    // @@author

    public static final String SUCCESS_VISIT_CHECK = "V";
    public static final String FAIL_VISIT_CHECK = "FV";
    public static final String SUCCESS_ASSIGNED_CHECK = "A";
    private static final String SLOT_ONE = "10:00:00";
    private static final String SLOT_TWO = "12:00:00";
    private static final String SLOT_THREE = "14:00:00";
    private static final String SLOT_FOUR = "16:00:00";
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Long DEFAULT_EMPTY_ASSIGNED_NURSE = Long.valueOf(-1); // No nurse assigned
    private static final String DEFAULT_CHECK = " ";
    public final LocalDateTime dateSlotTime;
    private final String dateSlotInString;
    private Boolean hasVisited;
    private Boolean hasAssigned;
    private Boolean isSuccessVisit;
    private Long nurseUidNo;

    /**
     * Constructs a {@code DateSlot}.
     *
     * @param dateSlot A valid dateSlot.
     */
    public DateSlot(String dateSlot) {
        requireNonNull(dateSlot);
        checkArgument(isValidDateSlot(dateSlot), MESSAGE_CONSTRAINTS);
        this.dateSlotTime = parseDateSlot(dateSlot);
        this.dateSlotInString = dateSlot;
        this.hasVisited = DEFAULT_BOOLEAN;
        this.hasAssigned = DEFAULT_BOOLEAN;
        this.isSuccessVisit = DEFAULT_BOOLEAN;
        this.nurseUidNo = DEFAULT_EMPTY_ASSIGNED_NURSE;
        checkDateTime();
    }

    /**
     * Constructs a {@code DateSlot}.
     */
    public DateSlot(String dateSlot, Boolean isAssigned, Boolean isVisited, Boolean isSucessVisit, Long nurseUidNo) {
        requireNonNull(dateSlot);
        requireNonNull(isAssigned);
        requireNonNull(isVisited);
        requireNonNull(isSucessVisit);
        requireNonNull(nurseUidNo);
        this.dateSlotTime = parseDateSlot(dateSlot);
        this.dateSlotInString = dateSlot;
        this.hasVisited = isVisited;
        this.hasAssigned = isAssigned;
        this.isSuccessVisit = isSucessVisit;
        this.nurseUidNo = nurseUidNo;
        checkDateTime();
    }

    private static LocalDateTime parseDateSlot(String dateSlot) {
        String[] s = dateSlot.split(",");
        String date = s[0];
        int slotNumber = Integer.parseInt(s[1]);
        String time = "T";

        if (slotNumber == 1) {
            time = time + SLOT_ONE;
        } else if (slotNumber == 2) {
            time = time + SLOT_TWO;
        } else if (slotNumber == 3) {
            time = time + SLOT_THREE;
        } else if (slotNumber == 4) {
            time = time + SLOT_FOUR;
        }

        String dateTime = date + time;
        return LocalDateTime.parse(dateTime);
    }

    /**
     * Returns true if a given string is a valid date and slot.
     */
    public static boolean isValidDateSlot(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getString() {
        return getAssignCheck() + ":" + getVisitCheck() + ":"
                + this.dateSlotInString + ":" + nurseUidNo;
    }

    /**
     * Check the datetime of the DateSlot with the current datetime from the system
     * clock.
     * Mark isVisited true if the datetime is before the current datetime.
     */
    public void checkDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (this.dateSlotTime.isBefore(currentDateTime)) {
            this.hasVisited = true;
            this.isSuccessVisit = true;
        }
    }

    /**
     * Mark DateSlot as assigned with the assigned nurse uid.
     */
    public void mark(Long nurseUidNo) {
        this.hasAssigned = true;
        this.nurseUidNo = nurseUidNo;
    }

    /**
     * Unmark DateSlot (not assigned) and remove assigned nurse uid.
     */
    public void unmark() {
        this.hasAssigned = false;
        this.nurseUidNo = DEFAULT_EMPTY_ASSIGNED_NURSE;
    }

    /**
     * Mark DateSlot as fail to visit.
     */
    public void markFail() {
        this.isSuccessVisit = DEFAULT_BOOLEAN;
    }

    /**
     * Mark DateSlot as success to visit.
     */
    public void markSuccess() {
        this.isSuccessVisit = true;
    }

    private String getAssignCheck() {
        String assignCheck = DEFAULT_CHECK;

        if (this.hasAssigned) {
            assignCheck = SUCCESS_ASSIGNED_CHECK;
        }

        return assignCheck;
    }

    private String getVisitCheck() {
        String visitCheck = DEFAULT_CHECK;

        if (this.hasVisited && this.isSuccessVisit) {
            visitCheck = SUCCESS_VISIT_CHECK;

        } else if (this.hasVisited && !this.isSuccessVisit) {
            visitCheck = FAIL_VISIT_CHECK;

        }

        return visitCheck;
    }

    public String getDateSlotInString() {
        return this.dateSlotInString;
    }

    public String getDateSlotFormatted() {
        return this.dateSlotTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public Boolean getHasVisited() {
        return this.hasVisited;
    }

    public Boolean getHasAssigned() {
        return this.hasAssigned;
    }

    public Boolean getIsSuccessVisit() {
        return this.isSuccessVisit;
    }

    public Long getNurseUidNo() {
        return this.nurseUidNo;
    }

    public LocalDateTime getDateTime() {
        return this.dateSlotTime;
    }

    public LocalDate getDate() {
        String[] s = dateSlotInString.split(",");
        String date = s[0];
        return LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return "[" + getAssignCheck() + "] " + "[" + getVisitCheck() + "] "
                + dateSlotTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateSlot // instanceof handles nulls
                        && dateSlotTime.equals(((DateSlot) other).dateSlotTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateSlotTime.hashCode();
    }

}
