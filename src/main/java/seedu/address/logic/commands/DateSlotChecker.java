package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;

/**
 * To check date slot related stuff.
 */
public class DateSlotChecker {

    public static final String MESSAGE_VISITED_DATESLOT = "The date slot %1$s has already passed.";
    public static final String MESSAGE_ASSIGNED_DATESLOT = "The date slot %1$s has been assigned already.";
    public static final String MESSAGE_TIME_CRASHES = "There is already an exisiting homevisit in this dateslot %1$s."
            + "Please assign another nurse";
    public static final String MESSAGE_UNAVAILABLE_DATE = "The nurse is unavailable on this day %1$s. "
            + "Please assign another nurse";
    public static final String MESSAGE_NOT_ASSIGNED_DATESLOT = "The dateslot %1$s has not been assigned.";
    public static final String MESSAGE_NOT_VISITED_DATESLOT = "The visit dates has not reached."
            + "Cannot unmark it as fail to visit .";
    public static final String MESSAGE_NOT_VISITED_DATESLOT_TWO = "The visit dates has not reached."
            + "Cannot undo unmark it as success visit.";
    public static final String MESSAGE_SUCCESS_VISIT_DATESLOT = "This dates has already been marked as "
            + "success visited. " + "Cannot undo unmark it as success visit.";

    private final DateSlot dateSlot;

    /**
     * Create DateSlotChecker.
     * @param dateSlot
     */
    public DateSlotChecker(DateSlot dateSlot) {
        this.dateSlot = dateSlot;
    }

    /**
     * Check whether the date slot has been visited.
     * @throws CommandException if visited
     */
    public void checkVisited() throws CommandException {
        if (dateSlot.getHasVisited()) {
            throw new CommandException(String.format(MESSAGE_VISITED_DATESLOT,
                    dateSlot.getDateSlotFormatted()));
        }
    }

    /**
     * Check whether the date slot has not been visited.
     * @throws CommandException if not visited
     */
    public void checkNotVisited() throws CommandException {
        if (!dateSlot.getHasVisited()) {
            throw new CommandException(MESSAGE_NOT_VISITED_DATESLOT);
        }
    }

    /**
     * Check whether the date slot has not been visited.
     * @throws CommandException if not visited
     */
    public void checkNotVisitedForUndoUnmark() throws CommandException {
        if (!dateSlot.getHasVisited()) {
            throw new CommandException(MESSAGE_NOT_VISITED_DATESLOT_TWO);
        }
    }

    /**
     * Check whether the date slot is a success visit
     * @throws CommandException if it is a success visit
     */
    public void checkSuccessVisited() throws CommandException {
        if (dateSlot.getIsSuccessVisit()) {
            throw new CommandException(MESSAGE_SUCCESS_VISIT_DATESLOT);
        }
    }

    /**
     * Check whether the date slot has not been assigned
     * @throws CommandException if date slot not assigned
     */
    public void checkNotAssigned() throws CommandException {
        if (!dateSlot.getHasAssigned()) {
            throw new CommandException(String.format(MESSAGE_NOT_ASSIGNED_DATESLOT,
                    dateSlot.getDateSlotFormatted()));
        }
    }

    /**
     * Check whether the date slot has been assigned
     * @throws CommandException if date slot is assigned
     */
    public void checkAssigned() throws CommandException {
        if (dateSlot.getHasAssigned()) {
            throw new CommandException(String.format(MESSAGE_ASSIGNED_DATESLOT, dateSlot.getDateSlotFormatted()));
        }
    }

    /**
     * Check whether the date slot has time clash with the homeVisitList.
     * @param homeVisitList
     * @throws CommandException if there is time clash
     */
    public void checkCrashes(List<HomeVisit> homeVisitList) throws CommandException {
        Optional<HomeVisit> homeVisit = homeVisitList.stream().filter(
                h -> h.getDateSlot().getDateTime().equals(dateSlot.getDateTime())).findFirst();
        if (!homeVisit.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TIME_CRASHES, dateSlot.getDateSlotFormatted()));
        }
    }

    /**
     * Check whether the date slot has time clash with the unavailabilityDateList.
     * @param unavailabilityDateList
     * @throws CommandException if there is time clash
     */
    public void checkUnavailability(List<Date> unavailabilityDateList) throws CommandException {
        Optional<Date> date = unavailabilityDateList.stream().filter(
                d -> d.getDate().equals(dateSlot.getDate())).findFirst();
        if (!date.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_UNAVAILABLE_DATE, dateSlot.getDateSlotFormatted()));
        }
    }


}


