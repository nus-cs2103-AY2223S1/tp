package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;

/**
 * A class that manage all date slot related task.
 */
public class DateSlotManager {

    public static final String MESSAGE_OUTOFBOUND_DATESLOT_INDEX = "The date slot index given is out of bounds.";
    public final List<DateSlot> dateSlotList;
    public final List<Index> dateSlotIndexList;

    /**
     * Construct a DateSlotManager.
     * @param dateSlotList
     * @param dateSlotIndex
     */
    public DateSlotManager(List<DateSlot> dateSlotList, List<Index> dateSlotIndex) {
        this.dateSlotList = new ArrayList<>();
        for (DateSlot dateSlot : dateSlotList) {
            this.dateSlotList.add(dateSlot.clone());
        }
        this.dateSlotIndexList = new ArrayList<>(dateSlotIndex);
    }

    /**
     * Construct a DateSlotManger with empty date slot index list.
     * @param dateSlotList
     */
    public DateSlotManager(List<DateSlot> dateSlotList) {
        this.dateSlotList = new ArrayList<>();
        for (DateSlot dateSlot : dateSlotList) {
            this.dateSlotList.add(dateSlot.clone());
        }
        this.dateSlotIndexList = new ArrayList<>();
    }

    /**
     * Construct a DateSlotManger with only one index.
     * @param dateSlotList
     * @param index
     */
    public DateSlotManager(List<DateSlot> dateSlotList, Index index) {
        this.dateSlotList = new ArrayList<>();
        for (DateSlot dateSlot : dateSlotList) {
            this.dateSlotList.add(dateSlot.clone());
        }
        this.dateSlotIndexList = new ArrayList<>();
        this.dateSlotIndexList.add(index);
    }

    /**
     * Mark the respective date slot as assigned if there is no time clashes
     * @param homeVisitList
     * @param unavailableDateList
     * @param nurseUidNo
     * @return updated dateSlot list
     * @throws CommandException
     */
    public List<DateSlot> markAssigned(List<HomeVisit> homeVisitList, List<Date> unavailableDateList,
                                       Long nurseUidNo) throws CommandException {
        if (dateSlotIndexList.isEmpty()) {
            markAllAssigned(homeVisitList, unavailableDateList, nurseUidNo);
        } else {
            markSpecificAssigned(homeVisitList, unavailableDateList, nurseUidNo);
        }
        return dateSlotList;
    }

    private void markAllAssigned(List<HomeVisit> homeVisitList, List<Date> unavailableDateList,
                              Long nurseUidNo) throws CommandException {
        for (DateSlot dateSlot : dateSlotList) {
            markAssignedCheck(dateSlot, homeVisitList, unavailableDateList);
            dateSlot.mark(nurseUidNo);
        }
    }

    private void markSpecificAssigned(List<HomeVisit> homeVisitList, List<Date> unavailableDateList,
                                 Long nurseUidNo) throws CommandException {
        sortIndex();
        checkIndexOutOfBound();
        for (Index index : dateSlotIndexList) {
            DateSlot dateSlot = dateSlotList.get(index.getZeroBased());
            markAssignedCheck(dateSlot, homeVisitList, unavailableDateList);
            dateSlot.mark(nurseUidNo);
        }
    }

    private void markAssignedCheck(DateSlot dateSlot, List<HomeVisit> homeVisitList, List<Date> unavailableDateList)
            throws CommandException {
        DateSlotChecker checker = new DateSlotChecker(dateSlot);
        checker.checkVisited();
        checker.checkAssigned();
        checker.checkCrashes(homeVisitList);
        checker.checkUnavailability(unavailableDateList);
    }

    private void sortIndex() {
        ReverseIndexComparator comp = new ReverseIndexComparator();
        this.dateSlotIndexList.sort(comp);
    }

    /**
     * Check whether the index given is out of bound.
     */
    public void checkIndexOutOfBound() throws CommandException {
        if (dateSlotIndexList.get(0).getZeroBased() >= dateSlotList.size()) {
            throw new CommandException(MESSAGE_OUTOFBOUND_DATESLOT_INDEX);
        }
    }

    /**
     * Unmark the respective date slot as unassigned.
     * @return updated dateSlot list
     * @throws CommandException
     */
    public List<DateSlot> unmarkAssigned() throws CommandException {
        if (dateSlotIndexList.isEmpty()) {
            unmarkAllAssigned();
        } else {
            unmarkSpecificAssigned();
        }
        return dateSlotList;
    }

    private void unmarkAllAssigned() throws CommandException {
        for (DateSlot dateSlot : dateSlotList) {
            unmarkAssignedCheck(dateSlot);
            dateSlot.unmark();
        }
    }

    private void unmarkSpecificAssigned() throws CommandException {
        sortIndex();
        checkIndexOutOfBound();
        for (Index index : dateSlotIndexList) {
            DateSlot dateSlot = dateSlotList.get(index.getZeroBased());
            unmarkAssignedCheck(dateSlot);
            dateSlot.unmark();
        }
    }

    /**
     * Unmark the specific date slot that has the same dateTime with the dateSlot given.
     * @param dateSlot
     * @return updated dateSlot list
     */
    public List<DateSlot> unmarkSpecificAssignedFromHomeVisit(DateSlot dateSlot) {
        DateSlot dateSlotToBeUnmarked = dateSlotList.stream().filter(
                d -> d.getDateTime().equals(dateSlot.getDateTime())).findFirst().get();
        dateSlotToBeUnmarked.unmark();
        return dateSlotList;
    }

    private void unmarkAssignedCheck(DateSlot dateSlot) throws CommandException {
        DateSlotChecker checker = new DateSlotChecker(dateSlot);
        checker.checkVisited();
        checker.checkNotAssigned();
    }

    /**
     * Unmark the specified dateslot as fail visited.
     * @return updated dateSlot list
     * @throws CommandException
     */
    public List<DateSlot> unmarkSuccessVisited() throws CommandException {
        checkIndexOutOfBound();
        DateSlot dateToBeUnmark = dateSlotList.get(dateSlotIndexList.get(0).getZeroBased());
        unmarkSuccessVisitedCheck(dateToBeUnmark);
        dateToBeUnmark.markFail();
        return dateSlotList;
    }

    private void unmarkSuccessVisitedCheck(DateSlot dateSlot) throws CommandException {
        DateSlotChecker checker = new DateSlotChecker(dateSlot);
        checker.checkNotVisited();
    }

    /**
     * Undo the unmark of specified date slot as success visit.
     * @return updated dateSlot list
     * @throws CommandException
     */
    public List<DateSlot> undoUnmarkFailVisited() throws CommandException {
        checkIndexOutOfBound();
        DateSlot dateToBeUndoUnmark = dateSlotList.get(dateSlotIndexList.get(0).getZeroBased());
        undoUnmarkFailVisitedCheck(dateToBeUndoUnmark);
        dateToBeUndoUnmark.markSuccess();
        return dateSlotList;
    }

    private void undoUnmarkFailVisitedCheck(DateSlot dateSlot) throws CommandException {
        DateSlotChecker checker = new DateSlotChecker(dateSlot);
        checker.checkNotVisitedForUndoUnmark();
        checker.checkSuccessVisited();
    }

    /**
     * Remove the respective date slot from the date slot list.
     * @return updated dateSlot list
     * @throws CommandException
     */
    public List<DateSlot> removeDateSlot() throws CommandException {
        if (dateSlotIndexList.isEmpty()) {
            removeAllDateSlot();
        } else {
            removeSpecificDateSlot();
        }
        return dateSlotList;
    }

    private void removeAllDateSlot() {
        dateSlotList.clear();
    }

    private void removeSpecificDateSlot() throws CommandException {
        sortIndex();
        checkIndexOutOfBound();
        for (Index index : dateSlotIndexList) {
            DateSlot dateSlot = dateSlotList.get(index.getZeroBased());
            dateSlotList.remove(dateSlot);
        }
    }

    /**
     * Add the dateSlotToBeAdded given by user to the existing date slot list.
     * @param dateSlotToBeAdded
     * @return updated dateSlot list
     */
    public List<DateSlot> addDateSlot(List<DateSlot> dateSlotToBeAdded) {
        dateSlotList.addAll(dateSlotToBeAdded);
        return dateSlotList;
    }

    /**
     * Get the date slot list.
     */
    public List<DateSlot> getDateSlot() {
        return this.dateSlotList;
    }


}



