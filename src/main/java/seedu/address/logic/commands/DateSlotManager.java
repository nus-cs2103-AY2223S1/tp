package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.ReverseIndexComparator;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Date;
import seedu.address.model.person.DateSlot;
import seedu.address.model.person.HomeVisit;

public class DateSlotManager {

    public static final String MESSAGE_OUTOFBOUND_DATESLOT_INDEX = "The date slot index given is out of bounds.";
    public final List<DateSlot> dateSlotList;
    public final List<Index> dateSlotIndexList;

    public DateSlotManager(List<DateSlot> dateSlotList, List<Index> dateSlotIndex) {
        this.dateSlotList = new ArrayList<>();
        for (DateSlot dateSlot : dateSlotList) {
            this.dateSlotList.add(dateSlot.clone());
        }
        this.dateSlotIndexList = new ArrayList<>(dateSlotIndex);
    }

    public DateSlotManager(List<DateSlot> dateSlotList) {
        this.dateSlotList = new ArrayList<>();
        for (DateSlot dateSlot : dateSlotList) {
            this.dateSlotList.add(dateSlot.clone());
        }
        this.dateSlotIndexList = new ArrayList<>();
    }

    public DateSlotManager(List<DateSlot> dateSlotList, Index index) {
        this.dateSlotList = new ArrayList<>();
        for (DateSlot dateSlot : dateSlotList) {
            this.dateSlotList.add(dateSlot.clone());
        }
        this.dateSlotIndexList = new ArrayList<>();
        this.dateSlotIndexList.add(index);
    }

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

    public void checkIndexOutOfBound() throws CommandException {
        if (dateSlotIndexList.get(0).getZeroBased() >= dateSlotList.size()) {
            throw new CommandException(MESSAGE_OUTOFBOUND_DATESLOT_INDEX);
        }
    }

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

    public List<DateSlot> addDateSlot(List<DateSlot> dateSlotToBeAdded) {
        dateSlotList.addAll(dateSlotToBeAdded);
        return dateSlotList;
    }

    public List<DateSlot> getDateSlot() {
        return this.dateSlotList;
    }


}



