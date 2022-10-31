package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.DateSlot;

/**
 * Jackson-friendly version of {@link DateSlot}.
 */
class JsonAdaptedDateSlot {
    private final String dateSlot;
    private final Boolean isAssigned;
    private final Boolean isVisited;
    private final Boolean isSuccessVisit;
    private final Long nurseUidNo;
    private final String dateSlotInString;

    /**
     * Constructs a {@code JsonAdaptedDateSlot} with the given {@code DateSlot}.
     */
    @JsonCreator
    public JsonAdaptedDateSlot(String dateSlot) {
        this.dateSlot = dateSlot;
        String[] s = dateSlot.split(":");
        this.isAssigned = s[0].equals(DateSlot.SUCCESS_ASSIGNED_CHECK);
        if (s[1].equals(DateSlot.SUCCESS_VISIT_CHECK)) {
            this.isVisited = true;
            this.isSuccessVisit = true;
        } else if (s[1].equals(DateSlot.FAIL_VISIT_CHECK)) {
            this.isVisited = true;
            this.isSuccessVisit = false;
        } else {
            this.isVisited = false;
            this.isSuccessVisit = false;
        }
        this.dateSlotInString = s[2];
        this.nurseUidNo = Long.parseLong(s[3]);
    }

    /**
     * Converts a given {@code DateSlot} into this class for Jackson use.
     */
    public JsonAdaptedDateSlot(DateSlot source) {
        dateSlot = source.getString();
        dateSlotInString = source.getDateSlotInString();
        isAssigned = source.getHasAssigned();
        isVisited = source.getHasVisited();
        isSuccessVisit = source.getIsSuccessVisit();
        nurseUidNo = source.getNurseUidNo();
    }

    @JsonValue
    public String getDateSlot() {
        return dateSlot;
    }

    /**
     * Converts this Jackson-friendly adapted date slot object into the model's
     * {@code DateSlot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted date slot.
     */
    public DateSlot toModelType() throws IllegalValueException {
        if (!DateSlot.isValidDateSlot(dateSlotInString)) {
            throw new IllegalValueException(DateSlot.MESSAGE_CONSTRAINTS);
        }
        return new DateSlot(dateSlotInString, isAssigned, isVisited, isSuccessVisit, nurseUidNo);
    }

}
